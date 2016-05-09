delimiter //
drop procedure if exists processenroll //
create procedure processenroll
(in id int, in cid char(8), in sem char(2), in y int)
begin 
	-- update the enrollment number 
	update uosoffering 
    set Enrollment = Enrollment + 1
    where UoSCode = cid and Year = y and Semester = sem;
    
    insert into transcript
    values(id, cid, sem, y, null);
end ; //
delimiter ;

