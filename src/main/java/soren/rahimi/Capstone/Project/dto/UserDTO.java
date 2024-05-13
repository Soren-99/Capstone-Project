package soren.rahimi.Capstone.Project.dto;

import lombok.Data;

import soren.rahimi.Capstone.Project.enums.UserRole;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private UserRole userRole;


}
