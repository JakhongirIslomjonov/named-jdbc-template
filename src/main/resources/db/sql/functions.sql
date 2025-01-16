
create type response_dto as
(
    code           integer,
    "message"      varchar(258),
    system_message text,
    return_id      bigint
);


/* user insert  function */

create or replace function insert_into_user(full_name character, "email" character, password character,
                                            roles varchar[]) returns response_dto
    language plpgsql
as
$$
declare
    user_id bigint;
    role_id bigint;
begin
    if exists(select 1 from users where users.email = "" email "") then
        return (400, 'already exist saved this email', SQLERRM, null);
    end if;
    if length(password) < 8 or length(password) > 16 then
        return (400, 'password size not right 8 or 16 between', SQLERRM, null);
    end if;
    if length(full_name) < 4 or length(full_name) > 255 then
        return (400, 'full Name size not right ', SQLERRM, null);
    end if;
    insert into users(full_name, email, password) VALUES (full_name, email, password) returning id into user_id;
    if array_length(roles, 1) is null then
        roles := Array ['ROLE_CLIENT'];
    end if;
    for i in array_lower(roles, 1)..array_upper(roles, 1)
        loop
            Select id into role_id from roles where role_name = roles[i];

            if not FOUND then
                return (400, 'Role' || roles[i] || 'not found', SQLERRM, null);
            end if;
            insert into user_roles(user_id, role_id) values (user_id, role_id);
        end loop;
    RETURN (201, 'User created successfully', SQLERRM, user_id);
EXCEPTION
    WHEN OTHERS THEN
        RETURN (500, 'An unexpected error occurred', SQLERRM, NULL);
end;
$$;

create or replace function find_by_mail_return_user(email varchar) returns boolean
    language plpgsql
as
$$
declare

begin

end;

$$

