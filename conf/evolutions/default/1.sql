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




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table hospital;

SET FOREIGN_KEY_CHECKS=1;

