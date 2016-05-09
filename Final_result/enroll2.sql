delimiter //
drop procedure if exists enroll //
create procedure enroll 
(in id int, in cid char(8), in sem char(2), in y int, out enrollresult int)

begin
	if exists (select * from uosoffering o
		where o.uoscode = cid
		and o.enrollment < o.maxenrollment
		and sem = o.Semester 
	    and y = o.Year)
	then 
	begin
		if not exists (select r.prerequoscode as precid
		    from requires r
		    where cid = r.uoscode
            and r.prerequoscode  not in (select s.uoscode
								from transcript s
								where 	s.studid = id 
										and s.grade is not null 
										and s.grade <> 'F')) 
		then
			set enrollresult = 1; -- enroll success 
            
		elseif not exists
        (select r2.PrereqUoSCode from requires r2 where cid = r2.uoscode)
        then set enrollresult = 1; -- enroll success (no prerequisite required!)
		else
        begin
			select r.prerequoscode as precid
		    from requires r
		    where cid = r.uoscode
            
            and r.prerequoscode not in 	(select s1.uoscode
									from transcript s1
									where s1.StudId = id 
									and s1.grade is not null 
									and s1.grade <> 'F');
			set enrollresult = 0; -- enroll fail (prerequisite)
		end;
		end if;
	end;
    else set enrollresult = -1; -- enroll fail (max)
    
    end if;
end ; //
delimiter ;


-- select @a;