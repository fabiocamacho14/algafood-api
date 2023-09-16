create table restaurante_usuario_responsavel (
restaurante_id integer not null,
usuario_id integer not null,

primary key (restaurante_id, usuario_id)
) engine=InnoDB;

alter table restaurante_usuario_responsavel add constraint FK_RESTAURANTE_USUARIO_RESPONSAVEL_USUARIO foreign key (usuario_id) references usuario (id);
alter table restaurante_usuario_responsavel add constraint FK_RESTAURANTE_USUARIO_RESPONSAVEL_RESTAURANTE foreign key (restaurante_id) references restaurante (id);
