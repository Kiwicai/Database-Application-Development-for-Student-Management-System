delimiter //
drop procedure if exists changeAddress //
create procedure changeAddress
(IN THEID int(11), IN oldPW VARCHAR(10), in newAd varchar(50))
begin
update student s
SET S.address = newAd
where s.id = theid and s.password = oldPW;
end ; //
delimiter ;


-- call changeAddress(3213, "dinner", "Maple");