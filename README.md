 
Step 1: Download Spring Tool Suite from https://spring.io/tools3/sts/all. Click on the platform which you are using.  
Step 2: Extract the zip file and install the STS.

https://download.springsource.com/release/STS/3.9.15.RELEASE/dist/e4.18/spring-tool-suite-3.9.15.RELEASE-e4.18.0-win32-x86_64.zip

https://dist.springsource.com/release/STS/index.html

Spring intialiser 
https://start.spring.io/

mysql installer for 64 bit 

dev.mysql.com/downloads
watch below video 

https://www.youtube.com/watch?v=WuBcTJnIuzo

create database testdb;

use testdb;
below is the query to create table 


create table course_entity(id bigint not null auto_increment, description varchar(45), feedback varchar(45), lastupdated varchar(45), location varchar(45), prerequesite varchar(45), primary key (id)) engine=InnoDB

create table course_entity_creator (course_entity_id bigint not null, creator_id bigint not null) engine=InnoDB

create table course_entity_skill (course_entity_id bigint not null, skill_id bigint not null) engine=InnoDB

create table creator (id bigint not null, name varchar(255), primary key (id)) engine=InnoDB
create table skill (id bigint not null, name varchar(255), primary key (id)) engine=InnoDB

How to generate RSA key 

https://docs.github.com/en/github/authenticating-to-github/connecting-to-github-with-ssh/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent


ssh-keygen -t rsa -b 4096 -C "your_email@example.com"

Testing --------
# course
hello
# course
