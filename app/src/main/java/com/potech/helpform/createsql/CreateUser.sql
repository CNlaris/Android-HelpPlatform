CREATE TABLE IF NOT EXISTS `admin` (
    `id` BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '管理员ID',
    `login_name` VARCHAR(30) NOT NULL UNIQUE COMMENT '登录账户',
    `login_password` VARCHAR(50) NOT NULL COMMENT '登录密码',
    `user_name` VARCHAR(50) NOT NULL COMMENT '用户名',
    `power_grade` TINYINT UNSIGNED NOT NULL COMMENT '权限等级',
    `last_login_ip_address` CHAR(15) COMMENT '上次登录IP',
    `last_login_time` varchar(30) COMMENT  '上次登录时间'
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='用户管理员';
INSERT INTO `admin` (login_name, login_password, user_name, power_grade) VALUES ('admin', '123456', '管理员', 2);