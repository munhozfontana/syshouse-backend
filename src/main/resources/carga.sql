CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
insert into Municipio (createdAt, ibge, nome, pais, populacao, uf, id) values (NOW(), 1, 'nome', 'pais', 1, 'uf',  uuid_generate_v4());
 insert into TipoPatrimonio (createdAt, descricao, id) values (NOW(), 'Descricao',  uuid_generate_v4());