delimiter //
drop procedure if exists TriggerInsert //
create procedure TriggerInsert
(out result int)
begin
	if exists (select * from WithdrawTrigger)
    then 
    begin
		set result = 1;
        delete from WithdrawTrigger;
    end;
    else set result = -1;
    end if;
end; //
delimiter ;