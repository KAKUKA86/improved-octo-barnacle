create table dectionary
(
    d_id    int auto_increment
        primary key,
    d_name  varchar(20)                    null comment '辞书名',
    d_level enum ('1', '2', '3', '4', '5') null comment '辞书等级',
    d_time  timestamp                      null comment '添加时间'
)
    comment '辞书表';

create table glossary
(
    word_list_id int auto_increment comment '词单列表id'
        primary key
)
    comment '词单';

create table user
(
    user_id          int auto_increment comment '用户id'
        primary key,
    user_name        varchar(20)  null,
    user_password    varchar(100) null comment '用户密码',
    user_regist_time timestamp    null comment '用户注册时间'
)
    comment '用户表';

create table word
(
    word_id       varchar(5)  not null
        primary key,
    word          varchar(20) null comment '单词',
    hiragana      varchar(10) null comment '平假名',
    romaji        varchar(10) null comment '罗马字',
    pronunciation varchar(5)  null comment '发音',
    d_id          int         null comment '辞书编号',
    constraint word_dectionary_d_id_fk
        foreign key (d_id) references dectionary (d_id)
)
    comment '单词表';

create table paraphrase
(
    word_id      varchar(20)  null comment '单词id',
    para_id      varchar(20)  not null comment '释义编号'
        primary key,
    para_content varchar(255) null comment '释义内容',
    para_type    varchar(20)  null,
    constraint paraphrase_word_word_id_fk
        foreign key (word_id) references word (word_id)
            on update cascade
)
    comment '释义表';

create table illustrative_sentence
(
    para_id     varchar(20)  null,
    sen_content varchar(255) null comment '句子内容',
    sen_trans   varchar(255) null comment '句子翻译',
    constraint illustrative_sentence_paraphrase_para_id_fk
        foreign key (para_id) references paraphrase (para_id)
            on update cascade
)
    comment '例句表';


