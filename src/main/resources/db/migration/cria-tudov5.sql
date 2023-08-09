create table cidade (
    estado_id integer,
    id integer not null auto_increment,
    nome varchar(255),

    primary key (id)
) engine=InnoDB default charset=utf8;


create table cozinha (
    id integer not null auto_increment,
    nome varchar(255),

    primary key (id)
) engine=InnoDB default charset=utf8;


create table estado (
    id integer not null auto_increment,
    nome varchar(255),

    primary key (id)
) engine=InnoDB default charset=utf8;


create table forma_pagamento (
    id integer not null auto_increment,
    descricao varchar(60) not null,

    primary key (id)
) engine=InnoDB default charset=utf8;


create table grupo (
    id integer not null auto_increment,
    nome varchar(60) not null,

    primary key (id)
) engine=InnoDB default charset=utf8;


create table grupo_permissao (
    grupo_id integer not null,
    permissao_id integer not null
) engine=InnoDB default charset=utf8;


create table permissao (
    id integer not null auto_increment,
    nome varchar(60) not null,
    descricao varchar(100) not null,

    primary key (id)
) engine=InnoDB default charset=utf8;


create table produto (
    ativo bit not null,
    id integer not null auto_increment,
    preco decimal(10,2) not null,
    restaurante_id integer not null,
    nome varchar(80) not null,
    descricao varchar(255) not null,

    primary key (id)
) engine=InnoDB default charset=utf8;


create table restaurante (
    nome varchar(80) not null,
    cozinha_id integer not null,
    id integer not null auto_increment,
    taxa_frete decimal(10,2) not null,
    data_atualizacao datetime not null,
    data_cadastro datetime not null,
    restaurante_cidade_id integer,
    endereco_cep varchar(9),
    endereco_numero varchar(20),
    endereco_bairro varchar(60),
    endereco_complemento varchar(60),
    endereco_logradouro varchar(100),

    primary key (id)
) engine=InnoDB default charset=utf8;


create table restaurante_forma_pagamento (
    forma_pagamento_id integer not null,
    restaurante_id integer not null
) engine=InnoDB default charset=utf8;


create table usuario (
    id integer not null auto_increment,
    data_cadastro datetime not null,
    nome varchar(80) not null,
    email varchar(255) not null,
    senha varchar(255) not null,

    primary key (id)
) engine=InnoDB default charset=utf8;


create table usuario_grupo (
    grupo_id integer not null,
    usuario_id integer not null
) engine=InnoDB default charset=utf8;


alter table cidade add constraint FK_CIDADE_ESTADO foreign key (estado_id) references estado (id);
alter table grupo_permissao add constraint FK_GRUPO_PERMISSAO_PERMISSAO foreign key (permissao_id) references permissao (id);
alter table grupo_permissao add constraint FK_GRUPO_PERMISSAO_GRUPO foreign key (grupo_id) references grupo (id);
alter table produto add constraint FK_PRODUTO_RESTAURANTE foreign key (restaurante_id) references restaurante (id);
alter table restaurante add constraint FK_RESTAURANTE_COZINHA foreign key (cozinha_id) references cozinha (id);
alter table restaurante add constraint FK_RESTAURANTE_CIDADE foreign key (restaurante_cidade_id) references cidade (id);
alter table restaurante_forma_pagamento add constraint FK_RESTAURANTE_FORMA_PAGAMENTO_FORMA_PAGAMENTO foreign key (forma_pagamento_id) references forma_pagamento (id);
alter table restaurante_forma_pagamento add constraint FK_RESTAURANTE_FORMA_PAGAMENTO_RESTAURANTE foreign key (restaurante_id) references restaurante (id);
alter table usuario_grupo add constraint FK_USUARIO_GRUPO_GRUPO foreign key (grupo_id) references grupo (id);
alter table usuario_grupo add constraint FK_USUARIO_GRUPO_USUARIO foreign key (usuario_id) references usuario (id);
