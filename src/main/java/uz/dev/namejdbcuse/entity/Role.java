package uz.dev.namejdbcuse.entity;


import lombok.*;

import uz.dev.namejdbcuse.enums.RoleName;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {

    private Long id;

    private RoleName roleName;


}
