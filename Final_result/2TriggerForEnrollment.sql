delimiter //
drop trigger if exists Enrollment //
create trigger Enrollment after update on uosoffering 
for each row
begin
	if new.Enrollment < 0.5* new.MaxEnrollment
    then 
    begin
    insert into WithdrawTrigger
    values (new.UoSCode, new.Semester, new.Year);
    end;
	end if; 
end; //
delimiter ;