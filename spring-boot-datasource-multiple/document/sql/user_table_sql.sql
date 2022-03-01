/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.7.21 : Database - sakila
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sakila` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `sakila`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(100) DEFAULT NULL COMMENT '账号',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `home_address` varchar(100) DEFAULT NULL COMMENT '家庭住址',
  `state` tinyint(2) DEFAULT NULL COMMENT '状态:0-禁用,1-启动',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`home_address`,`state`) values 
(1,'user1','d23b23ccde86014cb2386b195978b9d0','中国广东深圳1',1),
(2,'user2','d23b23ccde86014cb2386b195978b9d0','中国广东深圳2',1),
(3,'user3','d23b23ccde86014cb2386b195978b9d0','中国广东深圳3',1),
(4,'user4','d23b23ccde86014cb2386b195978b9d0','中国广东深圳4',1),
(5,'user5','d23b23ccde86014cb2386b195978b9d0','中国广东深圳5',1),
(6,'user6','d23b23ccde86014cb2386b195978b9d0','中国广东深圳6',1),
(7,'user7','d23b23ccde86014cb2386b195978b9d0','中国广东深圳7',1),
(8,'user8','d23b23ccde86014cb2386b195978b9d0','中国广东深圳8',1),
(9,'user9','d23b23ccde86014cb2386b195978b9d0','中国广东深圳9',1),
(10,'user10','d23b23ccde86014cb2386b195978b9d0','中国广东深圳10',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
