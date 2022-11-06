create table persons
(
    id           int auto_increment
        primary key,
    age          int null,
    firstName    varchar(75) null,
    lastName     varchar(85) null,
    mobile       varchar(11) null,
    nationalCode varchar(10) null,
    createdAt    varchar(50) null,
    updatedAt    varchar(50) null,
    isDeleted    tinyint(1) default 0 not null,
    deletedAt    varchar(50) null,
    constraint UK_d1xrow79et3e822acnfg3rrnn
        unique (nationalCode)
);

#######################################################################################
CREATE TABLE users
(
    id             int not null primary key auto_increment,
    name           varchar(200),
    pass           varchar(200),
    email          varchar(200),
    active_code varchar(10),
    is_active      enum('ok','nok'),
    remember_token varchar(400),
    created_at     timestamp default current_timestamp,
    updated_at     timestamp null default null,
    deleted_at     timestamp null default null
)engine=InnoDB default character set utf8;

CREATE TABLE groups
(
    id         int not null primary key auto_increment,
    name_en    varchar(200),
    name_fa    varchar(200),
    created_at timestamp default current_timestamp,
    updated_at timestamp null default null,
    deleted_at timestamp null default null
)engine=InnoDB default character set utf8;

CREATE TABLE permissions
(
    id         int not null primary key auto_increment,
    name_en    varchar(200),
    name_fa    varchar(200),
    created_at timestamp default current_timestamp,
    updated_at timestamp null default null,
    deleted_at timestamp null default null
)engine=InnoDB default character set utf8;


CREATE TABLE group_user
(
    id         int not null primary key auto_increment,
    user_id    int,
    group_id   int,
    index group_user_user_index(user_id),
    constraint  group_user_user_fk foreign key (user_id) references users(id),

    index group_user_group_index(group_id),
    constraint  group_user_group_fk foreign key (group_id) references groups(id),
    created_at timestamp default current_timestamp
)engine=InnoDB default character set utf8;



CREATE TABLE group_permission
(
    id         int not null primary key auto_increment,
    permission_id    int,
    group_id   int,
    index group_permission_index(permission_id),
    constraint  group_permission_permission_fk foreign key (permission_id) references permissions(id),
    index  group_permission_group_index(group_id),
    constraint   group_permission_group_fk foreign key (group_id) references groups(id),
    created_at timestamp default current_timestamp
)engine=InnoDB default character set utf8;


CREATE TABLE vip_permission
(
    id         int not null primary key auto_increment,
    permission_id    int,
    user_id   int,
    index vip_permission_index(permission_id),
    constraint  vip_permission_permission_fk foreign key (permission_id) references permissions(id),
    index  vip_permission_user_index(user_id),
    constraint  vip_permission_user_fk foreign key (user_id) references users(id),
    created_at timestamp default current_timestamp
)engine=InnoDB default character set utf8;


CREATE TABLE block_permission
(
    id         int not null primary key auto_increment,
    permission_id    int,
    user_id   int,
    index block_permission_index(permission_id),
    constraint  block_permission_permission_fk foreign key (permission_id) references permissions(id),

    index  block_permission_user_index(user_id),
    constraint  block_permission_user_fk foreign key (user_id) references users(id),

    created_at timestamp default current_timestamp
)engine=InnoDB default character set utf8;