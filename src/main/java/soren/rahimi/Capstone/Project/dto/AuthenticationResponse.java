package soren.rahimi.Capstone.Project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    
    private String jwtToken;

}
