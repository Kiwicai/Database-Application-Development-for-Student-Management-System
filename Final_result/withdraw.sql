delimiter //
drop procedure if exists Withdraw //
create procedure Withdraw 
( 	in theId int(11),
    in theCourseId char(8),
    in y int(11),
    in s char(2),
    out result int)
begin
    declare p2 int;

		select count(*)into p2
		from transcript T
		where 	theId = T.StudId and
				T.Grade is null and
				T.UoSCode = theCourseId and
                T.year = y and
                T.Semester = s;
            
		if(p2 = 0) then 
        set result = -1; -- Try another CourseId!!
		elseif (p2 = 1) then
		begin
			delete from transcript
			where 	UoSCode = theCourseId and
					Year = y and
                    Semester = s;
			
			update uosoffering
			set Enrollment = Enrollment-1
			where 	UoSCode = theCourseId and
					Year = y and
                    Semester = s;

			set result = 1;            
		end;
		end if;
end; //
delimiter ;
    
