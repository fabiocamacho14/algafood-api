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
delete from restaurante_usuario_responsavel;
delete from usuario;
delete from usuario_grupo;
delete from pedido;
delete from item_pedido;
delete from foto_produto;

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
alter table pedido auto_increment = 1;
alter table item_pedido auto_increment = 1;
alter table foto_produto auto_increment = 1;

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

insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, endereco_cidade_id, ativo, aberto) values ('Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, '01132-023', 'Rua Castro Silva', '835', 'Montadinha', 2, true, true);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, endereco_cidade_id, ativo, aberto) values ('Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp, '01132056', 'Rua Carlos da Silva', '12', 'Campo Limpo', 2, true, true);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, endereco_cidade_id, ativo, aberto) values ('Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp, '123456789', 'Rua Santos de Almeida', '5', 'Campo Sujo', 1, true, true);

insert into forma_pagamento (descricao, data_atualizacao) values ('Cartão de débito', utc_timestamp);
insert into forma_pagamento (descricao, data_atualizacao) values ('Cartão de crédito', utc_timestamp);
insert into forma_pagamento (descricao, data_atualizacao) values ('Dinheiro', utc_timestamp);

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

insert into grupo (nome) values ('Soldado');
insert into grupo (nome) values ('Cabo');
insert into grupo (nome) values ('Sargento');
insert into grupo (nome) values ('Tenente');

insert into permissao (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');
insert into permissao (nome, descricao) values ('QUEBRAR_COZINHAS', 'Permite editar cozinhas');

insert into grupo_permissao (grupo_id, permissao_id) values (1, 1);
insert into grupo_permissao (grupo_id, permissao_id) values (1, 2);
insert into grupo_permissao (grupo_id, permissao_id) values (1, 3);
insert into grupo_permissao (grupo_id, permissao_id) values (2, 1);
insert into grupo_permissao (grupo_id, permissao_id) values (2, 2);
insert into grupo_permissao (grupo_id, permissao_id) values (3, 1);

insert into usuario (nome, email, senha, data_cadastro) values ('Carlos', 'carlosdasilva@gmail.com', 'carlao123', utc_timestamp);
insert into usuario (nome, email, senha, data_cadastro) values ('Enrico', 'enricodasilva@gmail.com', 'senha123', utc_timestamp);
insert into usuario (nome, email, senha, data_cadastro) values ('Vinicius', 'viniciusdasilva@gmail.com', 'qualquersenhaai', utc_timestamp);
insert into usuario (nome, email, senha, data_cadastro) values ('Guilherme', 'guilermedasilva@gmail.com', 'fasdfa4s5f4a5378', utc_timestamp);
insert into usuario (nome, email, senha, data_cadastro) values ('Fábio Camacho Carvajal', 'fabtuscamacho@hotmail.com', 'senhadofabio', utc_timestamp);

insert into restaurante_usuario_responsavel (restaurante_id, usuario_id) values (1, 1);
insert into restaurante_usuario_responsavel (restaurante_id, usuario_id) values (2, 2);
insert into restaurante_usuario_responsavel (restaurante_id, usuario_id) values (3, 3);

insert into pedido (endereco_cidade_id,
                    forma_pagamento_id,
                    restaurante_id,
                    subtotal,
                    usuario_id,
                    valor_total,
                    data_atualizacao,
                    data_cancelamento,
                    data_criacao,
                    data_entrega,
                    endereco_cep,
                    endereco_numero,
                    endereco_bairro,
                    endereco_complemento,
                    endereco_logradouro,
                    status_pedido,
                    codigo)
                    values (
                            3,
                            1,
                            2,
                            10.5,
                            2,
                            50.2,
                            utc_timestamp,
                            null,
                            utc_timestamp,
                            utc_timestamp,
                            '01122555',
                            '2',
                            'Mal Retiro',
                            'Nada',
                            'Rua Visconde da Silva',
                            'CONFIRMADO',
                            '107a964d-6710-11ee-bd22-025072131a20'
                           );
insert into pedido (endereco_cidade_id,
                    forma_pagamento_id,
                    restaurante_id,
                    subtotal,
                    usuario_id,
                    valor_total,
                    data_atualizacao,
                    data_cancelamento,
                    data_criacao,
                    data_entrega,
                    endereco_cep,
                    endereco_numero,
                    endereco_bairro,
                    endereco_complemento,
                    endereco_logradouro,
                    status_pedido,
                    codigo)
                    values (
                            3,
                            1,
                            2,
                            10.5,
                            5,
                            250.2,
                            utc_timestamp,
                            null,
                            utc_timestamp,
                            utc_timestamp,
                            '01122555',
                            '2',
                            'Mal Retiro',
                            'Nada',
                            'Rua Visconde da Silva',
                            'CRIADO',
                            '107a864d-6710-11ee-bd22-025072131a20'
                           );

insert into pedido (endereco_cidade_id,
                    forma_pagamento_id,
                    restaurante_id,
                    subtotal,
                    usuario_id,
                    valor_total,
                    data_atualizacao,
                    data_cancelamento,
                    data_criacao,
                    data_entrega,
                    endereco_cep,
                    endereco_numero,
                    endereco_bairro,
                    endereco_complemento,
                    endereco_logradouro,
                    status_pedido,
                    codigo)
values (
           3,
           1,
           2,
           10.5,
           2,
           50.2,
           utc_timestamp,
           null,
           DATE("2017-06-15 09:34:21"),
           utc_timestamp,
           '01322555',
           '2',
           'Barra Larga',
           'Uma bela de uma observação',
           'Rua Dona Marcia',
           'CONFIRMADO',
           '107a967d-6710-11ee-bd22-025072131a20'
       );


insert into item_pedido (pedido_id, preco_total, preco_unitario, produto_id, quantidade, observacao)
values (1, 50.0, 12.25, 2, 3, 'Observado');
insert into item_pedido (pedido_id, preco_total, preco_unitario, produto_id, quantidade, observacao)
values (1, 30.0, 2.25, 1, 30, 'Mal Observado');