package uz.dev.namejdbcuse.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Auditable extends BaseAuditable {


    private Long createdBy;


    private LocalDateTime createdAt;


    private LocalDateTime updateAt;

    private Long updatedBy;
}
