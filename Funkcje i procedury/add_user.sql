CREATE OR REPLACE PROCEDURE add_user(firstname varchar, lastname varchar, email varchar, password varchar) AS $$
BEGIN
    INSERT INTO users (firstname, lastname, email, password) VALUES (firstname, lastname, email, password);
END;
$$ LANGUAGE plpgsql;

select * from users;