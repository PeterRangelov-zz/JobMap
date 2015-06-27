# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table hospital (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  lat                       float,
  lon                       float,
  constraint pk_hospital primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  email_address             varchar(255),
  constraint pk_user primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table hospital;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

