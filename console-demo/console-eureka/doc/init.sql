create table if not exists `sys_user`
(
    id              bigint auto_increment primary key,
    login_name      varchar(50)  not null comment '登录名',
    login_pwd       varchar(50)  not null comment '登录密码',
    login_salt      varchar(100) not null comment '加密盐',
    register_time   datetime     not null comment '注册时间',
    last_login_time datetime     not null comment '最后登录时间'
);

create table if not exists `sys_role`
(
    id          bigint auto_increment primary key,
    role_name   varchar(50) not null comment '角色名',
    create_time datetime    not null comment '创建时间'
);

create table if not exists `sys_user_role`
(
    id      bigint auto_increment primary key,
    user_id bigint not null comment '用户id',
    role_id bigint not null comment '角色id'
);
