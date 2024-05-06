package soren.rahimi.Capstone.Project.service.user;

import soren.rahimi.Capstone.Project.dto.SignupDTO;
import soren.rahimi.Capstone.Project.dto.UserDTO;

public interface UserService {
    UserDTO createUser(SignupDTO signupDTO);

    boolean hasUserWithEmail(String email);
}
