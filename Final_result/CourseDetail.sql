delimiter //
drop procedure if exists CourseDetail //
create procedure CourseDetail
(in theId int(11), in thePassword varchar(10), in theCourseNum char(8))
begin
	declare p int;
    
	select count(*) into p from student S1 -- teachers login?????
    where 	S1.Id = theId and S1.Password = thePassword;
	if(p = 1)then
    begin
	select T.UoSCode as CourseNum, C.UoSName as Title, T.Year , T.Semester as Quarter, CTime.Enrollment, CTime.MaxEnrollment, F.Name as Lecturer, T.Grade
    from transcript T, unitofstudy C, uosoffering CTime, faculty F, 
    (select S2.Id from student S2
    where 	S2.Id = theId and S2.Password = thePassword) as S
    where 	S.Id = T.StudId and T.UoSCode = theCourseNum and 
			CTime.UoSCode = T.UoSCode and CTime.Year = T.Year and 
            CTime.Semester = T.Semester and C.UoSCode = T.UoSCode and 
            CTime.InstructorId = F.Id;
	end;
	end if;
end; //
delimiter ;
    
