create table dectionary
(
    d_id    int auto_increment
        primary key,
    d_name  varchar(20)                             null comment '辞书名',
    d_level enum ('1', '2', '3', '4', '5', 'other') null comment '辞书等级',
    d_time  timestamp                               null comment '添加时间'
)
    comment '辞书表';

create table glossary_category
(
    category_id   int auto_increment
        primary key,
    category_name varchar(50) not null,
    constraint category_name
        unique (category_name)
)
    comment '词单分类表';

create table glossary_word
(
    glossary_id int                                 not null comment '词单ID',
    word_id     varchar(6)                          not null comment '单词ID',
    created_at  timestamp default CURRENT_TIMESTAMP null comment '添加时间',
    primary key (glossary_id, word_id)
)
    comment '词单单词关联表';

create table user
(
    user_id          int auto_increment comment '用户id'
        primary key,
    user_name        varchar(20)               null,
    user_password    varchar(100)              null comment '用户密码',
    user_email       varchar(255)              null,
    user_phone       varchar(20)               null,
    user_regist_time timestamp default (now()) null comment '用户注册时间',
    constraint user_pk
        unique (user_email),
    constraint user_pk_2
        unique (user_email)
)
    comment '用户表';

create table glossary
(
    glossary_id      int auto_increment comment '词单ID'
        primary key,
    user_id          int                                  null comment '用户编号',
    list_name        varchar(255)                         null comment '词单名称',
    description      text                                 null comment '词单描述',
    is_public        tinyint(1) default 0                 null comment '是否公开（默认私有）',
    created_at       timestamp  default CURRENT_TIMESTAMP null comment '创建时间',
    updated_at       timestamp  default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    category_id      int                                  null comment '分类ID',
    cover_url        varchar(255)                         null comment '封面图',
    cover_meta       json                                 null comment '封面元数据（尺寸格式等）',
    default_cover_id int                                  null comment '默认封面ID',
    constraint glossary_user_user_id_fk
        foreign key (user_id) references user (user_id)
);

create table glossary_collection
(
    collection_id int auto_increment
        primary key,
    glossary_id   int                                 not null,
    user_id       int                                 not null,
    collection_at timestamp default CURRENT_TIMESTAMP null comment '收藏时间',
    constraint glossary_collection_glossary_glossary_id_fk
        foreign key (glossary_id) references glossary (glossary_id),
    constraint glossary_collection_user_user_id_fk
        foreign key (user_id) references user (user_id)
);

create table word
(
    word_id       varchar(6)  not null
        primary key,
    word          varchar(20) null comment '单词',
    hiragana      varchar(10) null comment '平假名',
    romaji        varchar(10) null comment '罗马字',
    pronunciation varchar(5)  null comment '发音'
)
    comment '单词表';

create table paraphrase
(
    word_id         varchar(20)  null comment '单词id',
    para_id         varchar(20)  not null comment '释义编号'
        primary key,
    para_content_zh varchar(255) null comment '释义内容',
    para_content_ja varchar(255) null comment '释义（日语）',
    para_type       varchar(20)  null comment '词类型',
    constraint paraphrase_word_word_id_fk
        foreign key (word_id) references word (word_id)
            on update cascade
)
    comment '释义表';

create table illustrative_sentence
(
    Illu_id     int auto_increment
        primary key,
    para_id     varchar(20)  null,
    sen_content varchar(255) null comment '句子内容',
    sen_trans   varchar(255) null comment '句子翻译',
    constraint illustrative_sentence_paraphrase_para_id_fk
        foreign key (para_id) references paraphrase (para_id)
            on update cascade
)
    comment '例句表';

create table word_dictionary
(
    word_id varchar(6) not null,
    d_id    int        not null,
    primary key (word_id, d_id),
    constraint word_dictionary_ibfk_1
        foreign key (word_id) references word (word_id),
    constraint word_dictionary_ibfk_2
        foreign key (d_id) references dectionary (d_id)
);

create index d_id
    on word_dictionary (d_id);

