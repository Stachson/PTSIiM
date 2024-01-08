CREATE OR REPLACE FUNCTION copy_visit_to_Visit_history(visit_id_to_copy INT)
RETURNS VOID AS $$
BEGIN
    INSERT INTO visitshistory (visitid, userid, doctorid, day, starttime, endtime, roomid, price)
    SELECT visitid, userid, doctorid, day, starttime, endtime, roomid, price
    FROM visits
    WHERE visitid = visit_id_to_copy;
END;
$$ LANGUAGE plpgsql;