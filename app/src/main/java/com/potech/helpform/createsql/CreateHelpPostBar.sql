CREATE TABLE IF NOT EXISTS `help_post_bar` (
    `id` BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '帖子ID',
    `help_user_name` VARCHAR(30) NOT NULL COMMENT '求助人用户名',
    `help_user_uuid` VARCHAR(50) NOT NULL COMMENT '求助人uuid',
    `help_location` VARCHAR(50) NOT NULL COMMENT '被困地点',
    `trapped_people_number` INT UNSIGNED  COMMENT '被困人数',
    `trapped_time` VARCHAR(50)  COMMENT '被困时间',
    `trapped_description` TEXT  COMMENT '受灾情况',
    `need_help_kind` TEXT  COMMENT '需要何种帮助',
    `contact_people` TEXT COMMENT '联系人',
    `contact_phone_number` TEXT COMMENT '联系方式',
    `release_time` VARCHAR(30) NOT NULL COMMENT '提交时间',
    `urgency_degree` VARCHAR(30) NOT NULL COMMENT '紧急程度'
    )ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='求助帖子';
INSERT INTO `help_post_bar` (`help_user_name`,`help_user_uuid`,`help_location`,`trapped_people_number`,`trapped_time`,`trapped_description`,`need_help_kind`
,`contact_people`,`contact_phone_number`,`release_time`,`urgency_degree`) VALUES ('管理员','321897389519','郑州','1','2021/7/10 12:30','测试使用','测试使用','管理员','1234567890'
,'2021/7/10 12:30','紧急');