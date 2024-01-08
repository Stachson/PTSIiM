CREATE OR REPLACE FUNCTION get_my_visits(target_userid int)
RETURNS TABLE (
    firstname varchar,
    lastname varchar,
    day date,
    starttime time,
    endtime time,
    price decimal,
    speciality varchar
) AS $$
BEGIN
    RETURN QUERY 
    SELECT d.firstname, d.lastname, vh.day, vh.starttime, vh.endtime, vh.price, d.speciality
    FROM visitshistory vh
    INNER JOIN doctors d ON vh.doctorid = d.doctorid
    WHERE vh.userid = target_userid and vh.day < current_date;
END;
$$ LANGUAGE plpgsql;