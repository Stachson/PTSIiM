CREATE OR REPLACE FUNCTION get_free_visits_on_date(target_date date)
RETURNS TABLE (
    firstname varchar,
    lastname varchar,
    speciality varchar,
    day date,
    starttime time,
    endtime time,
    roomnumber smallint
) AS $$
BEGIN
    RETURN QUERY 
    SELECT d.firstname, d.lastname, d.speciality, v.day, v.starttime, v.endtime, r.roomnumber
    FROM doctors d
    INNER JOIN visits v ON d.doctorid = v.doctorid
    INNER JOIN rooms r ON v.roomid = r.roomid
    WHERE v.userid IS NULL AND v.day = target_date;
END;
$$ LANGUAGE plpgsql;