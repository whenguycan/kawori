
drop table t_e_anime;
create table t_e_anime(
id int(11) primary key auto_increment,
f_name varchar(255),
f_group varchar(255),
f_curr int(11),
f_all int(11),
f_season varchar(255),
f_status varchar(255),
f_creator varchar(255)
);

drop table t_e_user;
create table t_e_user(
id int(11) primary key auto_increment,
f_username varchar(255),
f_password varchar(255),
f_realname varchar(255)
);

insert into t_e_user values (null,'wang','57931574aeb159cc7552d1fe45f1dcdc2480b9e','');