delimiter //
drop procedure if exists CourseCouldWithdraw //
create procedure CourseCouldWithdraw
(in theId int(11), in thePassword varchar(10))
begin	
    declare MonthNow int;
	declare QuarterNow char(2);
    
    set MonthNow =  month(now());
    
	if(9<= MonthNow <=12) then set QuarterNow = 'Q1';
	elseif(1<= MonthNow <=3) then set QuarterNow = 'Q2';
	elseif(4<= MonthNow <=6) then set QuarterNow = 'Q3';
	elseif(7<= MonthNow <=8) then set QuarterNow = 'Q4';
    end if;
    
	select T.UoSCode,T.Semester, T.YEAR FROM transcript T, 
    (select S2.Id from student S2
    where 	S2.Id = theId and S2.Password = thePassword) as S
    where 	S.Id = T.StudId and T.Grade is null;
		
end; //
delimiter ;
