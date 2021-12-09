CREATE TABLE IF NOT EXISTS `help_post_bar_message` (
    `id` BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '回复ID',
    `help_post_bar_id` BIGINT UNSIGNED NOT NULL COMMENT '回复帖子ID',
    `message_username` TEXT NOT NULL COMMENT '回复帖子的用户名',
    `message` TEXT NOT NULL COMMENT '回复帖子的内容',
    `message_username_uuid` TEXT NOT NULL COMMENT '回复帖子的用户名的UUID'
    )ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='求助帖子回复';
