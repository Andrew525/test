DROP TABLE IF EXISTS tblDepartments;
CREATE TABLE IF NOT EXISTS tblDepartments
(
   dpID serial,
   dpName varchar(255) not null,
   primary key(dpID)
);
DROP TABLE IF EXISTS tblEmployees;
CREATE TABLE IF NOT EXISTS tblEmployees
(
   empID serial,
   empName varchar(255) not null,
   empActive varchar(255) not null,
   emp_dpID bigint unsigned not null,
   primary key(empID) ,
   foreign key(emp_dpID) references tblDepartments(dpID)
);