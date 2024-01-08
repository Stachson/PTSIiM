CREATE OR REPLACE FUNCTION find_visit_id(firstname1 varchar, lastname1 varchar, day1 date, starttime1 time, endtime1 time)
RETURNS integer AS
$$
DECLARE
    doctor_id integer;
    visit_id integer;
BEGIN
    SELECT doctorid INTO doctor_id
    FROM doctors
    WHERE doctors.firstname = firstname1 AND doctors.lastname = lastname1;

    SELECT visitid INTO visit_id
    FROM visits
    WHERE visits.doctorid = doctor_id AND visits.day = day1 AND visits.starttime = starttime1 AND visits.endtime = endtime1;

    IF visit_id IS NULL THEN
        RAISE EXCEPTION 'Brak pasującego rekordu w tabeli visits';
    END IF;

    RETURN visit_id;
END;
$$
LANGUAGE plpgsql;