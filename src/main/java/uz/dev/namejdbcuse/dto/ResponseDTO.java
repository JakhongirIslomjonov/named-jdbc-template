package uz.dev.namejdbcuse.dto;

import lombok.*;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ResponseDTO{
    private Integer code;
    private String message;
    private Long returnId;
}
