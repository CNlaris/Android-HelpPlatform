CREATE TABLE IF NOT EXISTS `user` (
    `id` BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '管理员ID',
    `login_name` VARCHAR(30) NOT NULL UNIQUE COMMENT '登录账户',
    `login_password` VARCHAR(50) NOT NULL COMMENT '登录密码',
    `user_name` VARCHAR(50) NOT NULL COMMENT '用户名',
    `uuid` VARCHAR(50) NOT NULL COMMENT '用户uuid'
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='用户';
INSERT INTO `admin` (login_name, login_password, user_name, `uuid`) VALUES ('test123456', '123456', '测试账号', `123456789`);