/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.7.21 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `user` (
	`id` bigint (20),
	`username` varchar (300),
	`password` varchar (300),
	`role` varchar (600),
	`address` varchar (600),
	`age` int (11),
	`state` tinyint (1)
); 
insert into `user` (`id`, `username`, `password`, `role`, `address`, `age`, `state`) values('1','root','{bcrypt}$2a$10$/.4eK1JTNF9h6jBzPh94ROgdgsj6KBVNAmg3I7pNBx1wWbckq97jG','ADMIN','广州','11','1');
insert into `user` (`id`, `username`, `password`, `role`, `address`, `age`, `state`) values('3','admin','{bcrypt}$2a$10$/.4eK1JTNF9h6jBzPh94ROgdgsj6KBVNAmg3I7pNBx1wWbckq97jG','ADMIN','中国','21','1');
insert into `user` (`id`, `username`, `password`, `role`, `address`, `age`, `state`) values('11','kitty','{bcrypt}$2a$10$/.4eK1JTNF9h6jBzPh94ROgdgsj6KBVNAmg3I7pNBx1wWbckq97jG','USER','中国','12','1');
insert into `user` (`id`, `username`, `password`, `role`, `address`, `age`, `state`) values('12','tom','{bcrypt}$2a$10$/.4eK1JTNF9h6jBzPh94ROgdgsj6KBVNAmg3I7pNBx1wWbckq97jG','USER','中国','13','1');
insert into `user` (`id`, `username`, `password`, `role`, `address`, `age`, `state`) values('13','feifei','{bcrypt}$2a$10$/.4eK1JTNF9h6jBzPh94ROgdgsj6KBVNAmg3I7pNBx1wWbckq97jG','USER','中国','14','1');
insert into `user` (`id`, `username`, `password`, `role`, `address`, `age`, `state`) values('14','bush','{bcrypt}$2a$10$/.4eK1JTNF9h6jBzPh94ROgdgsj6KBVNAmg3I7pNBx1wWbckq97jG','USER','中国','16','1');
