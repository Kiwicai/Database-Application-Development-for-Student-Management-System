delimiter //
drop procedure if exists FullTranscript //
create procedure FullTranscript
(in theId int(11), in thePassword varchar(10))
begin
	declare p int;
	select count(*) into p from student S1 -- teachers login?????
    where 	S1.Id = theId and S1.Password = thePassword;
	if(p = 1)then
    begin
	select T.UoSCode, T.Grade, T.SEMESTER, T.year  from transcript T, 
    (select S2.Id from student S2
    where 	S2.Id = theId and S2.Password = thePassword) as S
    where 	S.Id = T.StudId;
	end;
	end if;
end; //
delimiter ;
    
