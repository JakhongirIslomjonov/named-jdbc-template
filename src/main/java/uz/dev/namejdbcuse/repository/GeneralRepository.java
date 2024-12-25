package uz.dev.namejdbcuse.repository;

import org.springframework.jdbc.core.RowMapper;
import uz.dev.namejdbcuse.dto.ResponseDTO;

public abstract class GeneralRepository {

    public final RowMapper<ResponseDTO> rowMapper = (rs, rowNum) -> ResponseDTO.builder()
            .code(rs.getInt("code"))
            .message(rs.getString("message"))
            .returnId(rs.getLong("return_id"))
            .build();

}
