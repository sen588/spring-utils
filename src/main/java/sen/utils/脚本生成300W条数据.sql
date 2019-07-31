#创建库
CREATE DATABASE bigdata;
USE bigdata;

#创建dept表
CREATE TABLE dept(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	deptno MEDIUMINT UNSIGNED NOT NULL DEFAULT 0,
	dname VARCHAR(20) NOT NULL DEFAULT "",
	loc VARCHAR(13) NOT NULL DEFAULT ""
)ENGINE=INNODB DEFAULT CHARSET=gbk;


#创建emp表
CREATE TABLE emp(
	Id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	empno MEDIUMINT UNSIGNED NOT NULL DEFAULT 0,
	ename VARCHAR(20) NOT NULL DEFAULT "",
	job VARCHAR(9) NOT NULL DEFAULT "",
	mgr MEDIUMINT UNSIGNED NOT NULL DEFAULT 0,
	hiredate DATE NOT NULL,
	sal DECIMAL(7,2) NOT NULL,
	comm DECIMAL(7,2) NOT NULL,
	deptno MEDIUMINT UNSIGNED NOT NULL DEFAULT 0
)ENGINE=INNODB DEFAULT CHARSET=gbk;



#参数设置
SET GLOBAL log_bin_trust_function_creators=1;
SHOW VARIABLES LIKE 'log_bin_trust_function_creators';


#生成随机员工信息emp
DELIMITER $$
CREATE FUNCTION rand_string(n INT) RETURNS VARCHAR(255)
BEGIN
	DECLARE chars_str VARCHAR(100)DEFAULT 'abcdefghijkimnopqrstuvwxyzABCDEFJHIJKLMNOPQRSTUVWXYZ';
	DECLARE return_str VARCHAR(255)DEFAULT "";
	DECLARE i INT DEFAULT 0;
WHILE i < n DO
	SET return_str = CONCAT(return_str,SUBSTRING(chars_str,FLOOR(1+RAND()*52),1));
	SET i = i + 1;
	END WHILE;
RETURN return_str;
END $$




#生成随机部门编号dept
DELIMITER $$
CREATE FUNCTION rand_num()
RETURNS INT(5)
BEGIN
	DECLARE i INT DEFAULT 0;
	SET i = FLOOR(100+RAND()*10);
RETURN i;
END $$




#往emp表中插入存储过程
DELIMITER $$
CREATE PROCEDURE insert_emp(IN START INT(10),IN max_num INT(10))
BEGIN
DECLARE i INT DEFAULT 0;
SET autocommit = 0;
REPEAT
SET i=i+1;
INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,deptno)VALUES((START+i),rand_string(6),'salesman',0001,CURDATE(),2000,400,rand_num());
UNTIL i =max_num
END REPEAT;
COMMIT;
END $$

#删除存储过程
#Drop procedure insert_emp;



#往dept表中插入存储过程
DELIMITER $$
CREATE PROCEDURE insert_dept(IN START INT(10),IN max_num INT(10))
BEGIN
DECLARE i INT DEFAULT 0;
SET autocommit = 0;
REPEAT
SET i=i+1;
INSERT INTO dept(deptno,dname,loc)VALUES((START+i),rand_string(10),rand_string(8));
UNTIL i=max_num
END REPEAT;
COMMIT;
END $$


#往dept表插入数据
CALL insert_dept(100,10);

#往dept表插入数据
CALL insert_emp(100001,3000000);


