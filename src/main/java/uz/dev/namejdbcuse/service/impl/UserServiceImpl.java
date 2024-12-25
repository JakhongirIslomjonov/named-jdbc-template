package uz.dev.namejdbcuse.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dev.namejdbcuse.dto.ResponseDTO;
import uz.dev.namejdbcuse.dto.UserDTO;
import uz.dev.namejdbcuse.entity.User;
import uz.dev.namejdbcuse.repository.UserRepository;
import uz.dev.namejdbcuse.service.UserService;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;



    @SneakyThrows
    @Override
    public ResponseEntity<ResponseDTO> createUser(UserDTO userDTO) {
        User user = User.builder()
                .email(userDTO.getEmail())
                .roles(Collections.emptyList())
                .fullName(userDTO.getFullName())
                .password(userDTO.getPassword())
                .build();
        return ResponseEntity.ok(userRepository.saveUser(user));
    }
}
