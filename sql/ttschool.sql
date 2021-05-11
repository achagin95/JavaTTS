DROP DATABASE IF EXISTS ttschool;
CREATE DATABASE `ttschool`; 
USE `ttschool`;

CREATE TABLE trainee (
id INT (11) NOT NULL auto_increment,
firstname varchar(45) NOT NULL,
lastname varchar(45) not null,
raiting INT(11) not null,/*добавить групИд*/
PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE subjects (
id INT (11) NOT NULL auto_increment,
subjname varchar(45) NOT NULL,
primary key(id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE groups (
id INT (11) NOT NULL auto_increment,
groupName varchar(45) not null,
room varchar(45) not null,
schoolId INT (11) not null,
/*traineeId INT(45),*/
/*subjectsId INT(45),*/
primary key (id)
/*unique key groupName (groupName, room)*/
/*foreign key (traineeId) references trainee (id) on delete cascade,*/
/*foreign key (subjectsId) references subjects (id) on delete cascade*/
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE school (
id INT (11) NOT NULL auto_increment,
schoolName varchar(45) not null,
year INT(45) not null,
primary key (id),
unique key schoolName (schoolName, year)
/*foreign key (groupsid) references groups (id) on delete cascade*/
) ENGINE=INNODB DEFAULT CHARSET=utf8;


CREATE TABLE trainee_groups (
id INT (11) NOT NULL auto_increment,
traineeId INT(45) not null,
groupsid INT(45) not null,
primary key (id),
unique key traineeid (traineeId),
/*unique key trainee_groups (traineeId,groupsid),*/
foreign key (groupsid) references groups (id) on delete cascade,
foreign key (traineeId) references trainee (id) on delete cascade
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE subjects_groups (
id INT (11) NOT NULL auto_increment,
subjectsId INT(45) not null,
groupsid INT(45) not null,
primary key (id),
unique key subjects_groups (subjectsId,groupsid),
foreign key (groupsid) references groups (id) on delete cascade,
foreign key (subjectsId) references subjects (id) on delete cascade
) ENGINE=INNODB DEFAULT CHARSET=utf8;
