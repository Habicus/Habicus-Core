INSERT INTO `user` (`user_id`,`dob`,`email`,`gender`,`password`,`username`) VALUES (1,'2013-07-17 17:18:55.000000','code@ryan-schachte.com','male','$2a$10$3Y/2.kpjOCCGAW6Vkl8Ct./FQKrcJJQG553ZoqnrmS6djSlO4iXMC','testerUser1');
INSERT INTO `user` (`user_id`,`dob`,`email`,`gender`,`password`,`username`) VALUES (2,'2013-07-17 17:18:55.000000','austinsorrells@hacker.com','female','$2a$10$3Y/2.kpjOCCGAW6Vkl8Ct./FQKrcJJQG553ZoqnrmS6djSlO4iXMC','testerUser2');
INSERT INTO `user` (`user_id`,`dob`,`email`,`gender`,`password`,`username`) VALUES (3,'2013-07-17 17:18:55.000000','jordansorrells2@hacker.com','female','$2a$10$3Y/2.kpjOCCGAW6Vkl8Ct./FQKrcJJQG553ZoqnrmS6djSlO4iXMC','testerUser3');

INSERT INTO `goal` (`users_user_id`,`goal_id`,`due_date`,`description`,`goal_complete`,`goal_interval`,`label_color`,`pledge_amount`,`task_unit_count`,`title`) VALUES (1,1,'1525136658','Tester Description','0','daily','green',5,1,'Running');
INSERT INTO `goal` (`users_user_id`,`goal_id`,`due_date`,`description`,`goal_complete`,`goal_interval`,`label_color`,`pledge_amount`,`task_unit_count`,`title`) VALUES (2,2,'1525136658','Tester Description User 2','0','daily','purple',5,1,'Reading Books');
INSERT INTO `goal` (`users_user_id`,`goal_id`,`due_date`,`description`,`goal_complete`,`goal_interval`,`label_color`,`pledge_amount`,`task_unit_count`,`title`) VALUES (3,3,'1525136658','Tester Description User 3','0','daily','yellow',5,1,'Kayaking');
