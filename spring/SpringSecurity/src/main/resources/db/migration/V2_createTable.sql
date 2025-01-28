use spring_security;

create table `t_users` (
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `username`    varchar(255) DEFAULT NULL,
    `password`    varchar(255) DEFAULT NULL,
    PRIMARY KEY(`id`)
);