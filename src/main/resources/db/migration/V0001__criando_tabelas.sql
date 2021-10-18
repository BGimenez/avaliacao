create table if not exists pessoa (
    id bigserial primary key not null,
    nome varchar not null,
    cpf varchar not null,
    data_nascimento date not null
);

create table if not exists contato (
   id bigserial primary key not null,
   pessoa_id bigint constraint contato_pessoa_id_fk references pessoa,
   nome varchar not null,
   telefone varchar not null,
   email varchar not null
);