create table users (
   id bigint not null auto_increment,
   user_token varchar(255) not null,
   email varchar(255) not null,
   password varchar(255) not null,
   created_at datetime(6),
   updated_at datetime(6),
   primary key (id)
) engine=InnoDB;

alter table users add constraint UK_email unique (email);
alter table users add constraint UK_user_token unique (user_token);