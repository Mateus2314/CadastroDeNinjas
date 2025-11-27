-- V2: Migrations para adicionar a coluna de Sobrenome na tabela de cadastro

ALTER TABLE tb_cadastro
ADD COLUMN sobreNome VARCHAR(255);