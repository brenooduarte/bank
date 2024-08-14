INSERT INTO tb_endereco (logradouro, cep, numero, complemento, estado, cidade)
VALUES
('Rua das Flores', 12345678, 101, 'Apto 202', 'SP', 'São Paulo'),
('Avenida Brasil', 87654321, 200, NULL, 'RJ', 'Rio de Janeiro'),
('Praça da Liberdade', 11223344, 15, 'Bloco B', 'MG', 'Belo Horizonte'),
('Rua XV de Novembro', 55667788, 75, 'Casa 1', 'PR', 'Curitiba');


INSERT INTO tb_cliente (nome, cpf, telefone, id_endereco, id_agencia)
VALUES
('Breno Duarte', '123.456.789-00', '(11) 99999-1111', 1, 1),
('Cainã', '987.654.321-00', '(21) 88888-2222', 2, 1),
('André', '456.789.123-00', '(31) 77777-3333', 3, 1),
('Ana Pereira', '789.123.456-00', '(41) 66666-4444', 4, 1);
