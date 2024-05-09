package soren.rahimi.Capstone.Project.service.user;

import soren.rahimi.Capstone.Project.dto.SignupDTO;
import soren.rahimi.Capstone.Project.dto.UserDTO;

public interface AuthService {
    UserDTO createUser(SignupDTO signupDTO);

    Boolean hasUserWithEmail(String email);
}
