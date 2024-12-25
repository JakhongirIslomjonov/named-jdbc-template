package uz.dev.namejdbcuse.repository;

import liquibase.exception.DatabaseException;
import uz.dev.namejdbcuse.dto.ResponseDTO;
import uz.dev.namejdbcuse.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    List<User> findAllUser();

    Optional<User> findByIdUser(UUID userId) throws DatabaseException;

    ResponseDTO saveUser(User user) throws DatabaseException;

    void deleteUser(UUID userId);


}
