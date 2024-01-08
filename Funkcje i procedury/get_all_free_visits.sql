CREATE OR REPLACE FUNCTION get_all_free_visits()
RETURNS TABLE (
    firstname varchar,
    lastname varchar,
    speciality varchar,
    day date,
    starttime time,
    endtime time,
    roomnumber smallint,
    price decimal
) AS $$
BEGIN
    RETURN QUERY 
    SELECT d.firstname, d.lastname, d.speciality, v.day, v.starttime, v.endtime, r.roomnumber, v.price 
    FROM doctors d
    INNER JOIN visits v ON d.doctorid = v.doctorid
    INNER JOIN rooms r ON v.roomid = r.roomid
    WHERE v.userid IS NULL;
END;
$$ LANGUAGE plpgsql;