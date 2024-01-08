CREATE OR REPLACE PROCEDURE add_doctor(firstname varchar, lastname varchar, speciality varchar, description varchar) AS $$
BEGIN
    INSERT INTO doctors (firstname, lastname, speciality, description) VALUES (firstname, lastname, speciality, description);
END;
$$ LANGUAGE plpgsql;