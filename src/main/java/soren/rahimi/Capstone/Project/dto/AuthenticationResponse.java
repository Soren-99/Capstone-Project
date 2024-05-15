package soren.rahimi.Capstone.Project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import soren.rahimi.Capstone.Project.entities.User;

@Data
@AllArgsConstructor
public class AuthenticationResponse {

    private User user;
    private String jwtToken;

}
