# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table applicant (
  id                        bigint auto_increment not null,
  user_id                   bigint,
  first_name                varchar(20) not null,
  last_name                 varchar(30) not null,
  photo_url                 varchar(255),
  summary                   varchar(255),
  email                     varchar(50) not null,
  phone                     varchar(12),
  street                    varchar(50),
  city                      varchar(25),
  state                     varchar(2),
  zipcode                   varchar(5),
  residency_program         varchar(50) not null,
  residency_graduation      datetime not null,
  med_school                varchar(50) not null,
  med_school_graduation     datetime not null,
  cvlink                    varchar(255),
  constraint ck_applicant_state check (state in ('AL','AK','AZ','AR','CA','CO','CT','DE','FL','GA','HI','ID','IL','IN','IA','KS','KY','LA','ME','MD','MA','MI','MN','MS','MO','MT','NE','NV','NH','NJ','NM','NY','NC','ND','OH','OK','OR','PA','RI','SC','SD','TN','TX','UT','VT','VA','WA','WV','WI','WY')),
  constraint pk_applicant primary key (id))
;

create table application_entry (
  id                        bigint auto_increment not null,
  applicant_id              bigint,
  submitted                 time,
  constraint pk_application_entry primary key (id))
;

create table er_group (
  id                        bigint auto_increment not null,
  group_name                varchar(50) not null,
  logo_url                  varchar(50),
  type                      varchar(1),
  D                         tinyint(1) default 0,
  L                         tinyint(1) default 0,
  R                         tinyint(1) default 0,
  N                         tinyint(1) default 0,
  P                         tinyint(1) default 0,
  constraint ck_er_group_type check (type in ('N','L','R')),
  constraint pk_er_group primary key (id))
;

create table recruiter (
  id                        bigint auto_increment not null,
  user_id                   bigint,
  email_for_cvs             varchar(255),
  constraint pk_recruiter primary key (id))
;

create table site (
  id                        bigint auto_increment not null,
  site_name                 varchar(50),
  logo_url                  varchar(50),
  kind                      varchar(1),
  territory                 varchar(1),
  C                         tinyint(1) default 0,
  A                         tinyint(1) default 0,
  T                         tinyint(1) default 0,
  street                    varchar(50) not null,
  suite                     varchar(10),
  city                      varchar(25) not null,
  state                     varchar(2) not null,
  zipcode                   varchar(5) not null,
  lat                       float,
  lon                       float,
  group_id                  bigint,
  has_group                 tinyint(1) default 0,
  volume                    integer(6),
  constraint ck_site_kind check (kind in ('U','H','F')),
  constraint ck_site_territory check (territory in ('D','R','S','I')),
  constraint ck_site_state check (state in ('AL','AK','AZ','AR','CA','CO','CT','DE','FL','GA','HI','ID','IL','IN','IA','KS','KY','LA','ME','MD','MA','MI','MN','MS','MO','MT','NE','NV','NH','NJ','NM','NY','NC','ND','OH','OK','OR','PA','RI','SC','SD','TN','TX','UT','VT','VA','WA','WV','WI','WY')),
  constraint pk_site primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  email_address             varchar(255) not null,
  pwd                       varchar(255),
  last_login                datetime,
  role                      varchar(1),
  plan                      varchar(5),
  stripe_token              varchar(100),
  constraint ck_user_role check (role in ('R','A','X')),
  constraint ck_user_plan check (plan in ('PLAN1','PLAN2','PLAN3')),
  constraint pk_user primary key (id))
;


create table recruiter_site (
  recruiter_id                   bigint not null,
  site_id                        bigint not null,
  constraint pk_recruiter_site primary key (recruiter_id, site_id))
;

create table recruiter_group (
  recruiter_id                   bigint not null,
  er_group_id                    bigint not null,
  constraint pk_recruiter_group primary key (recruiter_id, er_group_id))
;
alter table applicant add constraint fk_applicant_user_1 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_applicant_user_1 on applicant (user_id);
alter table application_entry add constraint fk_application_entry_applicant_2 foreign key (applicant_id) references applicant (id) on delete restrict on update restrict;
create index ix_application_entry_applicant_2 on application_entry (applicant_id);
alter table recruiter add constraint fk_recruiter_user_3 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_recruiter_user_3 on recruiter (user_id);
alter table site add constraint fk_site_group_4 foreign key (group_id) references er_group (id) on delete restrict on update restrict;
create index ix_site_group_4 on site (group_id);



alter table recruiter_site add constraint fk_recruiter_site_recruiter_01 foreign key (recruiter_id) references recruiter (id) on delete restrict on update restrict;

alter table recruiter_site add constraint fk_recruiter_site_site_02 foreign key (site_id) references site (id) on delete restrict on update restrict;

alter table recruiter_group add constraint fk_recruiter_group_recruiter_01 foreign key (recruiter_id) references recruiter (id) on delete restrict on update restrict;

alter table recruiter_group add constraint fk_recruiter_group_er_group_02 foreign key (er_group_id) references er_group (id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table applicant;

drop table application_entry;

drop table er_group;

drop table recruiter_group;

drop table recruiter;

drop table recruiter_site;

drop table site;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

