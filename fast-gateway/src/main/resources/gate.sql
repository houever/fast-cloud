drop table if exists route;
drop table if exists dynamic_version;
/*==============================================================*/
/* Table: route                                                 */
/*==============================================================*/
create table route
(
   id                   bigint not null comment '主键',
   route_id             varchar(100) comment '路由id',
   route_uri            varchar(100) comment '转发目标uri',
   route_order          int(11) comment '路由执行顺序',
   predicates           text comment '断言字符串',
   filters              text comment '过滤器字符串',
   is_ebl               tinyint(1) comment '是否启用',
   is_del               tinyint(1) comment '是否删除',
   create_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   update_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
   primary key (id)
);

alter table route comment '路由信息表';

/*==============================================================*/
/* Table: dynamic_version                                       */
/*==============================================================*/
create table dynamic_version
(
   id                   bigint comment '主键',
   create_time          timestamp default CURRENT_TIMESTAMP comment '创建时间',
   update_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间'
);

alter table dynamic_version comment '版本发布信息表';
