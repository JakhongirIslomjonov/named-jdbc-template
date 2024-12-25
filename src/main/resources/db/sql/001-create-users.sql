CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       full_name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(16) NOT NULL
);
CREATE TABLE roles (
                       id BIGSERIAL PRIMARY KEY,
                       role_name VARCHAR(50) NOT NULL
);
CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,
                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES users (id),
                            FOREIGN KEY (role_id) REFERENCES roles (id)
);
INSERT INTO roles (role_name) VALUES ('ROLE_CLIENT'), ('ROLE_ADMIN');
