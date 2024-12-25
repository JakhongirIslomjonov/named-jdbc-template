package uz.dev.namejdbcuse.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import uz.dev.namejdbcuse.entity.Role;
import uz.dev.namejdbcuse.enums.RoleName;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserDTO {
    @JsonProperty("full_name")
    private String fullName;
    private String email;
    private String password;
}
