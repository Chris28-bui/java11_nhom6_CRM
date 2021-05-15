create database if not exists `crm`;

use crm;
use `crm`;

create table if not exists `role`(
	id int auto_increment,
    name varchar(50) not null,
    description varchar(500),
    primary key (id)
);


create table if not exists `status`(
	id int auto_increment,
    name varchar(50) not null,
    description varchar(500),
    primary key (id)
);

create table if not exists `user`(
	id 			int auto_increment,
    email 		varchar(50) not null unique,
    password 	varchar(500) not null,
    fullName 	varchar(255) not null,
    address 	varchar(500), role
    phone 		varchar(36),
    role_id 	int not null,
    primary key (id)
);

create table if not exists `task`(
	id 			int auto_increment,
    name 		varchar(100) not null,
    description varchar(500) not null,
    start_date	date default(NOW()),
    due_date 	date,
    assignee	int,
    project_id	int,
    status_id	int,
    primary key(id)
);

create table if not exists `project`(
	id int auto_increment,
    name varchar(100) not null,
    description varchar(500) not null,
    start_date date,
    end_date date,
    create_user_id int,
    primary key(id)
);

create table if not exists `Project_User`(
	project_id int auto_increment,
    user_id int,
    join_date date default(NOW()),
    role_description varchar(500) not null,
    primary key(project_id, user_id)
);

-- add foreign key
alter table `user`
	add constraint `fk_user_role`
		foreign key (role_id) references `role`(id);
        
alter table `task`
	add constraint `fk_task_user`
		foreign key (assignee) references `user`(id);
alter table `task`
	add constraint `fk_task_project`
		foreign key (project_id) references `project`(id);        
alter table `task`
	add constraint `fk_task_status`
		foreign key (status_id) references `status`(id);
        
alter table `project`
	add constraint `fk_project_user`
		foreign key (create_user_id) references `user`(id);

alter table `Project_User`
	add constraint `fk_Project_User_user`
		foreign key (user_id) references `user`(id);
alter table `Project_User`
	add constraint `fk_Project_User_project`
		foreign key (project_id) references `project`(id);
