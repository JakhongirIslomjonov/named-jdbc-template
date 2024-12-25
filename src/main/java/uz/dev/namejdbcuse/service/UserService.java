package uz.dev.namejdbcuse.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dev.namejdbcuse.dto.ResponseDTO;
import uz.dev.namejdbcuse.dto.UserDTO;
import uz.dev.namejdbcuse.entity.User;

@Service
public interface UserService {
    ResponseEntity<ResponseDTO> createUser(UserDTO userDTO);
}
