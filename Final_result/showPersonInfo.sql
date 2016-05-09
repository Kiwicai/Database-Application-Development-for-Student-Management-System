delimiter //
drop procedure if exists showPersonInfo //
create procedure showPersonInfo
(IN theID INT(11), IN thePassWord VARCHAR(10))
BEGIN
SELECT * FROM STUDENT S
WHERE S.ID = THEID AND S.PASSWORD = THEPASSWORD;
END; //
delimiter ;


-- call showPersonInfo(3213, "dinner");