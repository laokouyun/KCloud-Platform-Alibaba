ALTER TABLE boot_sys_user ADD COLUMN username_phrase VARCHAR(600) NOT NULL COMMENT '用户名短语';
ALTER TABLE boot_sys_user ADD COLUMN mail_phrase VARCHAR(600) DEFAULT NULL COMMENT '邮箱短语';
ALTER TABLE boot_sys_user ADD COLUMN mobile_phrase VARCHAR(100) DEFAULT NULL COMMENT '手机号短语';
update boot_sys_user set username_phrase = 'admin' where password = '{bcrypt}$2a$10$bGXM7u58FPMDanMyqvZ7Reb9sqJiUTCdAcb1wN5IIkFa8nYOMOioK';
update boot_sys_user set username_phrase = 'test' where password = '{bcrypt}$2a$10$J0DMR5098R33H6F.s5H/deeMLyo/j4yyzZgAn6gkyC0j537G7veKG';
update boot_sys_user set username_phrase = 'koush5' where password = '{bcrypt}$2a$10$ysAmruc249SiAUpIqQzrpeM8wcdpgIJ6nEdtsXQnDrBgvLZkt7tJ6';
update boot_sys_user set username_phrase = 'wumh5' where password = '{bcrypt}$2a$10$Wac.3sTE4A4pi/Zy6B/HWOstwLFjOH9g8Qrf4gHiBLa/avKAVcwpG';
update boot_sys_user set username_phrase = 'laok5' where password = '{bcrypt}$2a$10$RX9zW6rUMbGjybnlW77FWezhgbH0ZsFinGtKaoOsbovkEgij7kzNC';
ALTER TABLE boot_sys_user DROP username;
ALTER TABLE `boot_sys_user` ADD COLUMN `username` varchar(50) NOT NULL COMMENT '用户名';
update boot_sys_user set username = 'admin' where username_phrase = 'admin';
update boot_sys_user set username = 'test' where username_phrase = 'test';
update boot_sys_user set username = 'koush5' where username_phrase = 'koush5';
update boot_sys_user set username = 'wumh5'  where username_phrase = 'wumh5';
update boot_sys_user set username = 'laok5' where username_phrase = 'laok5';
ALTER TABLE `boot_sys_user` ADD UNIQUE `idx_username_tenant_id` ( `username`, `tenant_id` ) comment '用户名_租户_唯一索引' USING BTREE;
