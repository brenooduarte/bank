-- Inserir endereços
INSERT INTO tb_endereco (logradouro, cep, numero, complemento, estado, cidade) VALUES
('Rua A', 58000001, 100, 'Apto 1', 'PB', 'João Pessoa'),
('Rua B', 58000002, 200, 'Casa', 'PB', 'Campina Grande'),
('Rua C', 58000003, 300, 'Apto 2', 'PB', 'Patos');

-- Inserir agências
INSERT INTO tb_agencia (nome, telefone, taxa_juros, id_endereco) VALUES
('Agência Centro', '83987654321', 2.5, 1),
('Agência Sul', '83987654322', 3.0, 2),
('Agência Norte', '83987654323', 1.5, 3);

-- Inserir clientes
INSERT INTO tb_cliente (nome, cpf, telefone, id_endereco) VALUES
('João Silva', '12345678901', '83912345678', 1),
('Maria Souza', '23456789012', '83923456789', 2),
('Carlos Oliveira', '34567890123', '83934567890', 3);

-- Relacionar clientes com agências
INSERT INTO tb_cliente_agencia (id_cliente, id_agencia) VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 3);

-- Inserir contas bancárias
INSERT INTO tb_conta_bancaria (numero, saldo, tipo_conta, id_agencia, id_cliente) VALUES
('0001', 1000.00, 'CORRENTE', 1, 1),
('0002', 2000.00, 'POUPANCA', 2, 2),
('0003', 3000.00, 'CORRENTE', 3, 3);

-- Inserir operações
INSERT INTO tb_operacao (data_hora_movimento, tipo_operacao, taxa_operacao, id_conta_origem) VALUES
('2024-08-08 10:00:00', 'DEPOSITO', 0.00, 1),
('2024-08-08 11:00:00', 'SAQUE', 0.00, 2),
('2024-08-08 12:00:00', 'TRANSFERENCIA', 1.00, 1),
('2024-08-08 13:00:00', 'TRANSFERENCIA', 1.00, 3);

-- Inserir transferências
INSERT INTO tb_transferencia (id_operacao, id_conta_destino) VALUES
(3, 2),
(4, 1);
