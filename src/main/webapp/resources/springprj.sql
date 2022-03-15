CREATE TABLE `m_like` (
	`email`	varchar(100)	NOT NULL,
	`mid`	bigint	NOT NULL
);

CREATE TABLE `user` (
	`email`	varchar(100)	NOT NULL,
	`name`	varchar(100)	NOT NULL,
	`nick_name`	varchar(100)	NOT NULL,
	`pwd`	varchar(100)	NOT NULL,
	`profile_img`	varchar(200)	NOT NULL,
	`total_points`	int	NULL	DEFAULT 0,
	`curr_points`	int	NULL	DEFAULT 0,
	`grade`	int	NOT NULL	DEFAULT 10,
	`reg_at`	datetime	NULL	DEFAULT (now()),
	`last_login`	datetime	NULL,
	`slang`	tinyint	NULL	DEFAULT 0,
	`adult`	tinyint	NULL	DEFAULT 0
);

CREATE TABLE `notice` (
	`nid`	bigint	NOT NULL,
	`email`	varchar(100)	NOT NULL,
	`title`	varchar(50)	NOT NULL,
	`content`	text	NOT NULL,
	`reg_at`	datetime	NULL	DEFAULT (now()),
	`mod_at`	datetime	NULL	DEFAULT (now()),
	`read_count`	int	NULL	DEFAULT 0
);

CREATE TABLE `m_board` (
	`m_bid`	bigint	NOT NULL,
	`writer`	varchar(100)	NOT NULL,
	`mid`	bigint	NOT NULL,
	`title`	varchar(50)	NOT NULL,
	`like_hate`	tinyint	NOT NULL,
	`content`	text	NOT NULL,
	`reg_at`	datetime	NULL	DEFAULT (now()),
	`mod_at`	datetime	NULL	DEFAULT (now()),
	`read_count`	int	NULL	DEFAULT 0,
	`heart`	text	NULL
);

CREATE TABLE `m_review` (
	`mid`	bigint	NOT NULL,
	`writer`	varchar(100)	NOT NULL,
	`reg_at`	datetime	NULL	DEFAULT (now()),
	`content`	varchar(1000)	NOT NULL
);

CREATE TABLE `movie` (
	`mid`	bigint	NOT NULL,
	`title`	varchar(200)	NOT NULL,
	`poster`	varchar(200)	NULL,
	`like_cnt`	int	NULL	DEFAULT 0
);

CREATE TABLE `tv` (
	`tvid`	bigint	NOT NULL,
	`title`	varchar(200)	NOT NULL,
	`poster`	varchar(200)	NULL,
	`like_cnt`	int	NULL	DEFAULT 0
);

CREATE TABLE `tv_board` (
	`tv_bid`	bigint	NOT NULL,
	`writer`	varchar(100)	NOT NULL,
	`tvid`	bigint	NOT NULL,
	`title`	varchar(50)	NOT NULL,
	`like_hate`	tinyint	NOT NULL,
	`content`	text	NOT NULL,
	`reg_at`	datetime	NULL	DEFAULT (now()),
	`mod_at`	datetime	NULL	DEFAULT (now()),
	`read_count`	int	NULL	DEFAULT 0,
	`heart`	text	NULL
);

CREATE TABLE `tv_like` (
	`email`	varchar(100)	NOT NULL,
	`tvid`	bigint	NOT NULL
);

CREATE TABLE `tv_review` (
	`writer`	varchar(100)	NOT NULL,
	`tvid`	bigint	NOT NULL,
	`reg_date`	datetime	NULL	DEFAULT (now()),
	`content`	varchar(1000)	NOT NULL
);

CREATE TABLE `shop` (
	`sid`	bigint	NOT NULL,
	`category`	int	NULL,
	`sname`	varchar(100)	NULL,
	`price`	int	NULL,
	`value`	varchar(50)	NULL
);

CREATE TABLE `purchase` (
	`sid`	bigint	NOT NULL,
	`email`	varchar(100)	NOT NULL,
	`category`	int	NULL,
	`pname`	varchar(200)	NULL,
	`pvalue`	varchar(50)	NULL,
	`price`	int	NULL,
	`purch_date`	datetime	NULL
);

CREATE TABLE `m_comment` (
	`m_cid`	bigint	NOT NULL,
	`m_bid`	bigint	NOT NULL,
	`writer`	varchar(100)	NOT NULL,
	`content`	text	NOT NULL,
	`heart`	text	NULL,
	`reg_at`	datetime	NULL	DEFAULT (now()),
	`mod_at`	datetime	NULL	DEFAULT (now())
);

CREATE TABLE `tv_comment` (
	`tv_cid`	bigint	NOT NULL,
	`tv_bid`	bigint	NOT NULL,
	`writer`	varchar(100)	NOT NULL,
	`content`	text	NOT NULL,
	`heart`	text	NULL,
	`reg_at`	datetime	NULL	DEFAULT (now()),
	`mod_at`	datetime	NULL	DEFAULT (now())
);

CREATE TABLE `tv_rating` (
	`tvid`	bigint	NOT NULL,
	`email`	varchar(100)	NOT NULL,
	`rating`	float	NOT NULL
);

CREATE TABLE `m_rating` (
	`mid`	bigint	NOT NULL,
	`email`	varchar(100)	NOT NULL,
	`rating`	float	NOT NULL
);

ALTER TABLE `m_like` ADD CONSTRAINT `PK_M_LIKE` PRIMARY KEY (
	`email`,
	`mid`
);

ALTER TABLE `user` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
	`email`
);

ALTER TABLE `notice` ADD CONSTRAINT `PK_NOTICE` PRIMARY KEY (
	`nid`,
	`email`
);

ALTER TABLE `m_board` ADD CONSTRAINT `PK_M_BOARD` PRIMARY KEY (
	`m_bid`,
	`writer`,
	`mid`
);

ALTER TABLE `m_review` ADD CONSTRAINT `PK_M_REVIEW` PRIMARY KEY (
	`mid`,
	`writer`
);

ALTER TABLE `movie` ADD CONSTRAINT `PK_MOVIE` PRIMARY KEY (
	`mid`
);

ALTER TABLE `tv` ADD CONSTRAINT `PK_TV` PRIMARY KEY (
	`tvid`
);

ALTER TABLE `tv_board` ADD CONSTRAINT `PK_TV_BOARD` PRIMARY KEY (
	`tv_bid`,
	`writer`,
	`tvid`
);

ALTER TABLE `tv_like` ADD CONSTRAINT `PK_TV_LIKE` PRIMARY KEY (
	`email`,
	`tvid`
);

ALTER TABLE `tv_review` ADD CONSTRAINT `PK_TV_REVIEW` PRIMARY KEY (
	`writer`,
	`tvid`
);

ALTER TABLE `shop` ADD CONSTRAINT `PK_SHOP` PRIMARY KEY (
	`sid`
);

ALTER TABLE `purchase` ADD CONSTRAINT `PK_PURCHASE` PRIMARY KEY (
	`sid`,
	`email`
);

ALTER TABLE `m_comment` ADD CONSTRAINT `PK_M_COMMENT` PRIMARY KEY (
	`m_cid`,
	`m_bid`
);

ALTER TABLE `tv_comment` ADD CONSTRAINT `PK_TV_COMMENT` PRIMARY KEY (
	`tv_cid`,
	`tv_bid`
);

ALTER TABLE `tv_rating` ADD CONSTRAINT `PK_TV_RATING` PRIMARY KEY (
	`tvid`,
	`email`
);

ALTER TABLE `m_rating` ADD CONSTRAINT `PK_M_RATING` PRIMARY KEY (
	`mid`,
	`email`
);

ALTER TABLE `m_like` ADD CONSTRAINT `FK_user_TO_m_like_1` FOREIGN KEY (
	`email`
)
REFERENCES `user` (
	`email`
);

ALTER TABLE `m_like` ADD CONSTRAINT `FK_movie_TO_m_like_1` FOREIGN KEY (
	`mid`
)
REFERENCES `movie` (
	`mid`
);

ALTER TABLE `notice` ADD CONSTRAINT `FK_user_TO_notice_1` FOREIGN KEY (
	`email`
)
REFERENCES `user` (
	`email`
);

ALTER TABLE `m_board` ADD CONSTRAINT `FK_user_TO_m_board_1` FOREIGN KEY (
	`writer`
)
REFERENCES `user` (
	`email`
);

ALTER TABLE `m_board` ADD CONSTRAINT `FK_movie_TO_m_board_1` FOREIGN KEY (
	`mid`
)
REFERENCES `movie` (
	`mid`
);

ALTER TABLE `m_review` ADD CONSTRAINT `FK_movie_TO_m_review_1` FOREIGN KEY (
	`mid`
)
REFERENCES `movie` (
	`mid`
);

ALTER TABLE `m_review` ADD CONSTRAINT `FK_user_TO_m_review_1` FOREIGN KEY (
	`writer`
)
REFERENCES `user` (
	`email`
);

ALTER TABLE `tv_board` ADD CONSTRAINT `FK_user_TO_tv_board_1` FOREIGN KEY (
	`writer`
)
REFERENCES `user` (
	`email`
);

ALTER TABLE `tv_board` ADD CONSTRAINT `FK_tv_TO_tv_board_1` FOREIGN KEY (
	`tvid`
)
REFERENCES `tv` (
	`tvid`
);

ALTER TABLE `tv_like` ADD CONSTRAINT `FK_user_TO_tv_like_1` FOREIGN KEY (
	`email`
)
REFERENCES `user` (
	`email`
);

ALTER TABLE `tv_like` ADD CONSTRAINT `FK_tv_TO_tv_like_1` FOREIGN KEY (
	`tvid`
)
REFERENCES `tv` (
	`tvid`
);

ALTER TABLE `tv_review` ADD CONSTRAINT `FK_user_TO_tv_review_1` FOREIGN KEY (
	`writer`
)
REFERENCES `user` (
	`email`
);

ALTER TABLE `tv_review` ADD CONSTRAINT `FK_tv_TO_tv_review_1` FOREIGN KEY (
	`tvid`
)
REFERENCES `tv` (
	`tvid`
);

ALTER TABLE `purchase` ADD CONSTRAINT `FK_shop_TO_purchase_1` FOREIGN KEY (
	`sid`
)
REFERENCES `shop` (
	`sid`
);

ALTER TABLE `purchase` ADD CONSTRAINT `FK_user_TO_purchase_1` FOREIGN KEY (
	`email`
)
REFERENCES `user` (
	`email`
);

ALTER TABLE `m_comment` ADD CONSTRAINT `FK_m_board_TO_m_comment_1` FOREIGN KEY (
	`m_bid`
)
REFERENCES `m_board` (
	`m_bid`
);

ALTER TABLE `tv_comment` ADD CONSTRAINT `FK_tv_board_TO_tv_comment_1` FOREIGN KEY (
	`tv_bid`
)
REFERENCES `tv_board` (
	`tv_bid`
);

ALTER TABLE `tv_rating` ADD CONSTRAINT `FK_tv_TO_tv_rating_1` FOREIGN KEY (
	`tvid`
)
REFERENCES `tv` (
	`tvid`
);

ALTER TABLE `tv_rating` ADD CONSTRAINT `FK_user_TO_tv_rating_1` FOREIGN KEY (
	`email`
)
REFERENCES `user` (
	`email`
);

ALTER TABLE `m_rating` ADD CONSTRAINT `FK_movie_TO_m_rating_1` FOREIGN KEY (
	`mid`
)
REFERENCES `movie` (
	`mid`
);

ALTER TABLE `m_rating` ADD CONSTRAINT `FK_user_TO_m_rating_1` FOREIGN KEY (
	`email`
)
REFERENCES `user` (
	`email`
);