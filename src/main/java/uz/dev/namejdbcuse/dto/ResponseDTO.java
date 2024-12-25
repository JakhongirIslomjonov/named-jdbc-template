package uz.dev.namejdbcuse.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ResponseDTO {
    private Integer code;
    private String message;
    private Long returnId;
}
