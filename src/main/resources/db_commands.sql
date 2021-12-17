use heroku_4611fbb40488d8a;
-- use heroku_4611fbb40488d8a;
drop table staff;
drop table doctor;
drop table patient;

create table staff (id int primary key AUTO_INCREMENT, Username varchar(255) unique not null, password varchar(255), gender enum('Male', 'Female') default 'Female', department enum('account', 'doctor', 'receptionist', 'admin', 'nurse'), firstName varchar(255), lastName varchar(255), email varchar(255), phone bigint, address varchar(255) );

insert into staff (username, password, department) values('uche', '123456', 'account');
insert into staff (username, password, department) values('sandra', '123456', 'admin');

create table doctor(id int primary key AUTO_INCREMENT, username varchar(255), password varchar(255), firstName varchar(255), lastName varchar(255), Specialty enum('Pediatrician', 'Cardiologist', 'Gynecologist', 'Pulmonologist', 'PsyChiatrist', 'Surgeon'), 
Qualification enum('BSC', 'MSC', 'PHD'), phone bigint, Gender enum('Male', 'Female'));

insert into doctor (id, username, password, firstName, lastName, specialty, qualification, gender) values(101, 'emma12345', '12345', 'emma', 'Doe', 'Cardiologist', 'BSC', 'Male'),
 (102, 'sandra12345', '12345', 'sandra', 'Philip', 'Pediatrician', 'MSC', 'Female');

insert into doctor (username, password, firstName, lastName, specialty, qualification, gender) values('steph12345', '12345', 'Steph', 'Ugwu', 'Surgeon', 'PHD', 'Female');

create table patient(id int primary key AUTO_INCREMENT, firstName varchar(150), lastName varchar(200), sickness varchar(150), date_admitted datetime default CURRENT_TIMESTAMP, discharged bool, doctor_id int );

insert into patient (firstName, lastName, sickness, date_admitted, discharged, doctor_id) values('Stephen', 'Doe', 'Malaria','2021-05-21', false, 101);
insert into patient (firstName, lastName, sickness, date_admitted, discharged, doctor_id) values('Philip', 'Peters', 'Typhoid','2020-07-27', true, 103);

