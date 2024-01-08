CREATE OR REPLACE PROCEDURE add_free_visit(doctorid int, day date, starttime time, endtime time, roomid smallint, price decimal) AS $$
BEGIN
    INSERT INTO visits (userid, doctorid, day, starttime, endtime, roomid, price) VALUES (null, doctorid, day, starttime, endtime, roomid, price);
END;
$$ LANGUAGE plpgsql;