CREATE OR REPLACE PROCEDURE add_visit(userid int, doctorid int, day date, starttime time, endtime time, roomid smallint, price decimal) AS $$
DECLARE
   new_visit_id int;
BEGIN
   INSERT INTO visits (userid, doctorid, day, starttime, endtime, roomid, price) 
   VALUES (userid, doctorid, day, starttime, endtime, roomid, price)
   RETURNING visitid INTO new_visit_id;

   INSERT INTO visitshistory (visitid, userid, doctorid, day, starttime, endtime, roomid, price) 
   VALUES (new_visit_id, userid, doctorid, day, starttime, endtime, roomid, price);
END;
$$ LANGUAGE plpgsql;