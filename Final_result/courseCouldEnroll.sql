delimiter //
drop procedure if exists CourseCouldEnroll //
create procedure CourseCouldEnroll
 (in sid int, out couldEnrollResult int)
begin
    declare curYear int;
    declare curMonth int;
    declare curQ char(2);
    declare nextQ char(2);
    declare nextY int;
    
	set curYear =  year(now());
    set curMonth =  month(now());
	if(9<= curMonth <=12) then set curQ = 'Q1';
	elseif(1<= curMonth <=3) then set curQ = 'Q2';
	elseif(4<= curMonth <=6) then set curQ = 'Q3';
	elseif(7<= curMonth <=8) then set curQ = 'Q4';
    end if;
    
	if(9<= curMonth <=12) 
    then begin
		set nextQ = 'Q2';
		set nextY = curYear+1;
	end;
	elseif(1<= curMonth <=3) 
    then begin
		set nextQ = 'Q3';
        set nextY = curYear;
	end;
	elseif(4<= curMonth <=6) 
	then begin
		set nextQ = 'Q4';
        set nextY = curYear;
	end;
	elseif(7<= curMonth <=8)
	then begin
		set nextQ = 'Q1';
        set nextY = curYear;
	end;
	end if;

    if not exists(
		select o.UoSCode as cid, o.Semester as sem, o.Year as y
		from uosoffering o
		where o.UoSCode not in 	(select t.UoSCode
								from transcript t
								where t.StudId = sid)
		and 	((o.Semester = curQ and o.Year = curYear) 
				or (o.Semester = nextQ and o.Year = nextY)))
    then set couldEnrollResult = -1;                        
	else
    begin     
		select o.UoSCode as cid, o.Semester as sem, o.Year as y
		from uosoffering o
		where o.UoSCode not in 	(select t.UoSCode
								from transcript t
								where t.StudId = sid)
		and 	((o.Semester = curQ and o.Year = curYear) 
				or (o.Semester = nextQ and o.Year = nextY));	
		set couldEnrollResult = 1;
     end;
     end if;
    
end ; //
delimiter ;


-- call CourseCouldEnroll(5123, @a);