package uz.dev.namejdbcuse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.dev.namejdbcuse.dto.UserDTO;
import uz.dev.namejdbcuse.entity.Role;
import uz.dev.namejdbcuse.entity.User;
import uz.dev.namejdbcuse.enums.RoleName;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {


    User toEntity(UserDTO userDTO);

    UserDTO toDTO(User user);

}
