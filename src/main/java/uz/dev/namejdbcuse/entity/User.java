package uz.dev.namejdbcuse.entity;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class User {

    private Long id;

    private String fullName;

    private List<Role> roles;
    @Email
    private String email;

    @Size(min = 8)
    @NotBlank
    private String password;


}
