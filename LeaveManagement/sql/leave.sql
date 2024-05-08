create database Employee;
use Employee;
create table Employee_details(
userId int primary key,name varchar(20),password varchar(8));
select*from Employee_details;
desc Employee_details;
create table leavemanagement(leaveId int auto_increment primary key,leave_type varchar(20),days int , userId int);
ALTER TABLE leavemanagement
ADD CONSTRAINT fk_userId
FOREIGN KEY (userId)
REFERENCES Employee_details (userId)
ON DELETE CASCADE;
select*from leavemanagement;
alter table leavemanagement add endDate date ;
ALTER TABLE leavemanagement add permission_time timestamp;
