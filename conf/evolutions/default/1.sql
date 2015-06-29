# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table hospital (
  id                        bigint not null,
  name                      varchar(255),
  lat                       float,
  lon                       float,
  constraint pk_hospital primary key (id))
;

create table user (
  id                        bigint not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  email_address             varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (id))
;

create sequence hospital_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists hospital;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists hospital_seq;

drop sequence if exists user_seq;

