package uz.dev.namejdbcuse.repository.impl;

import io.sentry.Sentry;
import liquibase.exception.DatabaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.dev.namejdbcuse.dto.ResponseDTO;
import uz.dev.namejdbcuse.entity.Role;
import uz.dev.namejdbcuse.entity.User;
import uz.dev.namejdbcuse.enums.RoleName;
import uz.dev.namejdbcuse.repository.GeneralRepository;
import uz.dev.namejdbcuse.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl extends GeneralRepository implements UserRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public List<User> findAllUser() {
        return List.of();
    }

    @Override
    public Optional<User> findByIdUser(UUID userId) throws DatabaseException {
        String findUser = "Select * from users  where id  = :userId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("userId", userId);
        try {
            User user = namedParameterJdbcTemplate.queryForObject(findUser, parameterSource, (rs, rowNum) -> {
                User userNew = new User();
                userNew.setId(rs.getLong("id"));
                userNew.setEmail(rs.getString("email"));
                userNew.setFullName(rs.getString("full_name"));
                userNew.setPassword(rs.getString("password"));
                userNew.setRoles(findRolesByUserId(userNew.getId()));
                return userNew;
            });
            return Optional.ofNullable(user);
        } catch (Exception exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }

    private List<Role> findRolesByUserId(Long userId) {

        String sql = "Select r.id, r.role_name  from  roles r  inner join user_roles  ur  ON  r.id = ur.role_id where ur.user_id=:userId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("userId", userId);
        return namedParameterJdbcTemplate.query(sql, parameterSource, (rs, rowNum) -> {
            Role role = new Role();
            role.setId(rs.getLong("id"));
            role.setRoleName(RoleName.valueOf(rs.getString("role_name")));
            return role;
        });
    }

    @Override
    public ResponseDTO saveUser(User user) throws DatabaseException {

       /* try {
            String sqlCreateUser = "Insert into users (full_name,email,password ) values (:full_name, :email , :password)";
            MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                    .addValue("full_name", user.getFullName())
                    .addValue("email", user.getEmail())
                    .addValue("password", user.getPassword());
            namedParameterJdbcTemplate.update(sqlCreateUser, parameterSource);

            Long userId = namedParameterJdbcTemplate.queryForObject
                    ("Select currval(pg_get_serial_sequence('users','id'))", new MapSqlParameterSource(), Long.class);

            for (Role roles : user.getRoles()) {
                String sqlCheckRole = "Select  count(*) from  roles where  role_name =:roleName";
                MapSqlParameterSource parameterSourceRole = new MapSqlParameterSource()
                        .addValue("roleName", roles.getRoleName().name());
                Integer count = namedParameterJdbcTemplate.queryForObject(sqlCheckRole, parameterSourceRole, Integer.class);
                if (Objects.equals(count, 0)) {
                    String sqlCreateRole = "INSERT INTO roles ( role_name) values (:roleName)";
                    namedParameterJdbcTemplate.update(sqlCreateRole, parameterSourceRole);
                }
                Long roleId = namedParameterJdbcTemplate.queryForObject("SELECT  id  from roles  where  role_name = :roleName ", parameterSourceRole, Long.class);

                String sqlCreateUserRole = "Insert into user_roles(user_id,role_id) values(:userId,:roleId)";
                MapSqlParameterSource mapSqlParameterSourceUserRole = new MapSqlParameterSource()
                        .addValue("userId", userId)
                        .addValue("roleId", roleId);
                namedParameterJdbcTemplate.update(sqlCreateUserRole, mapSqlParameterSourceUserRole);
            }
        } catch (DataAccessException e) {
            throw new DatabaseException("database malumotla bilan xotolik ");
        }
*/

        String sql = "Select insert_into_user(:fullName,:email,:password,:roles)";
        return namedParameterJdbcTemplate.queryForObject(
                sql,
                new MapSqlParameterSource()
                        .addValue("fullName", user.getFullName())
                        .addValue("email", user.getEmail())
                        .addValue("password", user.getPassword())
                        .addValue("role s", user.getRoles().stream().map(role -> role.getRoleName().name()).toArray(String[]::new)),
                rowMapper);
    }



}
