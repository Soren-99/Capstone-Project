package soren.rahimi.Capstone.Project.entities;

import jakarta.persistence.*;
import lombok.Data;
import soren.rahimi.Capstone.Project.dto.UserDTO;
import soren.rahimi.Capstone.Project.enums.UserRole;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private UserRole userRole;
    private byte[] img;




}
