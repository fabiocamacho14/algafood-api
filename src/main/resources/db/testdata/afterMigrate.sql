set foreign_key_checks = 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from usuario;
delete from usuario_grupo;

set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table produto auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;

insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Indiana');
insert into cozinha (nome) values ('Portuguesa');
insert into cozinha (nome) values ('Espanhola');

# insert into restaurante (nome, taxa_frete, cozinha_id) values ('Thai Gourmet', 10, 1);

insert into estado (nome) values ('São Paulo');
insert into estado (nome) values ('Bahia');
insert into estado (nome) values ('Rio de Janeiro');

insert into cidade (nome, estado_id) values ('Sorocaba', 1);
insert into cidade (nome, estado_id) values ('Campinas', 1);
insert into cidade (nome, estado_id) values ('Salvador', 2);
insert into cidade (nome, estado_id) values ('Volta Redonda', 3);

insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, endereco_cidade_id, ativo) values ('Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, '01132-023', 'Rua Castro Silva', '835', 'Montadinha', 2, true);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, endereco_cidade_id, ativo) values ('Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp, '01132056', 'Rua Carlos da Silva', '12', 'Campo Limpo', 2, true);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, endereco_cidade_id, ativo) values ('Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp, '123456789', 'Rua Santos de Almeida', '5', 'Campo Sujo', 1, true);

insert into forma_pagamento (descricao) values ('Cartão de débito');
insert into forma_pagamento (descricao) values ('Cartão de crédito');
insert into forma_pagamento (descricao) values ('Dinheiro');

insert into permissao (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (forma_pagamento_id, restaurante_id) values (1, 1);
insert into restaurante_forma_pagamento (forma_pagamento_id, restaurante_id) values (1, 2);
insert into restaurante_forma_pagamento (forma_pagamento_id, restaurante_id) values (1, 3);
insert into restaurante_forma_pagamento (forma_pagamento_id, restaurante_id) values (2, 3);
insert into restaurante_forma_pagamento (forma_pagamento_id, restaurante_id) values (3, 2);
insert into restaurante_forma_pagamento (forma_pagamento_id, restaurante_id) values (3, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Thai Temperado', 'Comida temperada.', 15.50, true, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Thai destemperado', 'Comida destemperada.', 12.50, true, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Thai monstro', 'Comida monstruosa.', 25.50, true, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Nhumi', 'Hmm comida boa.', 13.50, false, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Petiscos', 'Batata, calabresa e petiscos.', 12.50, true, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Tuk Tuk Temperado', 'Comida inidiana temperada.', 15.50, true, 3);