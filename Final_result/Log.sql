delimiter //
drop procedure if exists Login //
create procedure Login
(in theId int(11), in thePassword varchar(10), out Result int)
begin
	declare p int;

	select count(*) into p from student S1 -- teachers login?????
    where 	S1.Id = theId and S1.Password = thePassword;
    
	if(p = 1)
    then set Result = 1;
	elseif(p = 0)
    then set Result = -1;
	end if;
    select Result;
end; //
delimiter ;