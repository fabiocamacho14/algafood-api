create table foto_produto (
    produto_id integer not null auto_increment,
    nome_arquivo varchar(150) not null,
    tamanho integer not null,
    content_type varchar(80) not null,
    descricao tinytext,
    primary key (produto_id)
) engine=InnoDB;

alter table foto_produto
    add constraint FK_FOTO_PRODUTO_PRODUTO foreign key (produto_id) references produto (id);
