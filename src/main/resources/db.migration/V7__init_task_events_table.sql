drop table if exists task_events;

create table task_events(
id int primary key auto_increment ,
task_id int,
name varchar(30),
occurence datetime
);