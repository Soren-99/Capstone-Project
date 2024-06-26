package soren.rahimi.Capstone.Project.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import soren.rahimi.Capstone.Project.dto.AuthenticationRequest;
//import soren.rahimi.Capstone.Project.dto.AuthenticationResponse;
import soren.rahimi.Capstone.Project.dto.AuthenticationResponse;
import soren.rahimi.Capstone.Project.dto.SignupDTO;
import soren.rahimi.Capstone.Project.dto.UserDTO;
import soren.rahimi.Capstone.Project.entities.User;
import soren.rahimi.Capstone.Project.repository.UserRepository;
import soren.rahimi.Capstone.Project.service.user.AuthService;
import soren.rahimi.Capstone.Project.utils.JwtUtil;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";

    @PostMapping("/authenticate")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException, JSONException, ServletException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password.");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
    AuthenticationResponse authenticationResponse =  new AuthenticationResponse(optionalUser.get(), jwt);
    return authenticationResponse;
    }



    @PostMapping("/sign-up")
    public ResponseEntity<?> signupUser(@RequestBody SignupDTO signupDTO){

        if (authService.hasUserWithEmail(signupDTO.getEmail())){
            return new ResponseEntity<>("User already exist", HttpStatus.NOT_ACCEPTABLE);
        }

        UserDTO userDTO = authService.createUser(signupDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);

    }

}
