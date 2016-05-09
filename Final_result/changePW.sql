delimiter //
drop procedure if exists changePW //
create procedure changePW
(IN THEID int(11), IN oldPW VARCHAR(10), in newPW varchar(10))
begin
update student s
SET S.password = NEWPW
where s.id = theid and s.password = oldPW;
end ; //
delimiter ;


-- call changePW(3213, "lunch", "dinner");