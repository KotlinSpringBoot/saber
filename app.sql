CREATE SCHEMA `saber` DEFAULT CHARACTER SET utf8;

INSERT INTO `saber`.`craw_source` (`type`, `url`) VALUES ('JIAN_SHU', 'http://www.jianshu.com/c/498ebcfd27ad');
INSERT INTO `saber`.`craw_source` (`type`, `url`) VALUES ('JIAN_SHU', 'http://www.jianshu.com/c/c3fe8e7aeb09');
INSERT INTO `saber`.`craw_source` (`type`, `url`) VALUES ('JIAN_SHU', 'http://www.jianshu.com/c/61314ad84456');
INSERT INTO `saber`.`craw_source` (`type`, `url`) VALUES ('JIAN_SHU', 'http://www.jianshu.com/c/f0cf6eae1754');
INSERT INTO `saber`.`craw_source` (`type`, `url`) VALUES ('JIAN_SHU', 'http://www.jianshu.com/c/98aaef9f5d2f');
INSERT INTO `saber`.`craw_source` (`type`, `url`) VALUES ('JIAN_SHU', 'http://www.jianshu.com/c/1d2b61da81ad');
INSERT INTO `saber`.`craw_source` (`type`, `url`) VALUES ('JIAN_SHU', 'http://www.jianshu.com/c/2e2ddd6ba967');
INSERT INTO `saber`.`craw_source` (`type`, `url`) VALUES ('JIAN_SHU', 'http://www.jianshu.com/c/ef7836bf3e22');
INSERT INTO `saber`.`craw_source` (`type`, `url`) VALUES ('JIAN_SHU', 'http://www.jianshu.com/c/38d96caffb2f');
INSERT INTO `saber`.`craw_source` (`type`, `url`) VALUES ('JIAN_SHU', 'http://www.jianshu.com/c/04cb7410c597');
INSERT INTO `saber`.`craw_source` (`type`, `url`) VALUES ('JIAN_SHU', 'https://www.jianshu.com/c/22f2ca261b85');
INSERT INTO `saber`.`craw_source` (`type`, `url`) VALUES ('JIAN_SHU', 'https://www.jianshu.com/c/0bab91ded569');
INSERT INTO `saber`.`craw_source` (`type`, `url`) VALUES ('JIAN_SHU', 'https://www.jianshu.com/c/8c01bfa7b98a');
INSERT INTO `saber`.`craw_source` (`type`, `url`) VALUES ('JIAN_SHU', 'https://www.jianshu.com/c/0690e20b7e7d');
INSERT INTO `saber`.`craw_source` (`type`, `url`) VALUES ('JIAN_SHU', 'https://www.jianshu.com/c/9bc3ae683403');
INSERT INTO `saber`.`craw_source` (`type`, `url`) VALUES ('JIAN_SHU', 'https://www.jianshu.com/c/a480500350e7');
INSERT INTO `saber`.`craw_source` (`type`, `url`) VALUES ('JIAN_SHU', 'https://www.jianshu.com/c/3ce88fc43e68');

delete from knowledge where content is null or content ='' or title is null or title='';

	id	type	url
	2	JIAN_SHU	http://www.jianshu.com/c/498ebcfd27ad
	3	JIAN_SHU	http://www.jianshu.com/c/c3fe8e7aeb09
	4	JIAN_SHU	http://www.jianshu.com/c/61314ad84456
	5	JIAN_SHU	http://www.jianshu.com/c/f0cf6eae1754
	6	JIAN_SHU	http://www.jianshu.com/c/98aaef9f5d2f
	7	JIAN_SHU	http://www.jianshu.com/c/1d2b61da81ad
	8	JIAN_SHU	http://www.jianshu.com/c/2e2ddd6ba967
	9	JIAN_SHU	http://www.jianshu.com/c/ef7836bf3e22
	10	JIAN_SHU	http://www.jianshu.com/c/38d96caffb2f
	11	JIAN_SHU	http://www.jianshu.com/c/04cb7410c597

	12	JIAN_SHU	https://www.jianshu.com/c/22f2ca261b85
	13	JIAN_SHU	https://www.jianshu.com/c/0bab91ded569
	14	JIAN_SHU	https://www.jianshu.com/c/8c01bfa7b98a
	15	JIAN_SHU	https://www.jianshu.com/c/0690e20b7e7d
	16	JIAN_SHU	https://www.jianshu.com/c/9bc3ae683403
	17	JIAN_SHU	https://www.jianshu.com/c/a480500350e7
	18	JIAN_SHU	https://www.jianshu.com/c/3ce88fc43e68
