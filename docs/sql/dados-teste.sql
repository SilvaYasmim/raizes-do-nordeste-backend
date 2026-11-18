INSERT INTO loja (nome, regiao, dono, horario_abertura, horario_fechamento, horario_pedidos) 
VALUES 
('Raízes do Nordeste - Matriz Boa Viagem', 'Nordeste', 'Valéria Frederico', '08:00:00', '23:00:00', '22:30:00'),
('Raízes do Nordeste - Shopping Centro', 'Nordeste', 'Felipe Silva', '10:00:00', '23:00:00', '21:30:00');

INSERT INTO produto (nome, preco, categoria) 
VALUES 
('Baião de Dois Completo', 34.90, 'Pratos Principais'),
('Carne de Sol com Macaxeira', 48.00, 'Pratos Principais'),
('Cartola Tradicional', 18.50, 'Sobremesas'),
('Cajuína Artesanal 500ml', 8.00, 'Bebidas');

SELECT * FROM loja

SELECT * FROM produto

INSERT INTO estoque (id_loja, id_produto, quantidade, disponivel) 
VALUES 
(1, 1, 15, true),
(1, 2, 10, true),
(1, 3, 0, false),  -- Produto indisponível para testes
(1, 4, 30, true);

INSERT INTO estoque (id_loja, id_produto, quantidade, disponivel) 
VALUES 
(2, 1, 8, true),
(2, 2, 5, true),
(2, 3, 4, true),  -- Aqui o mesmo produto tem estoque na Loja 2
(2, 4, 12, true);

SELECT 
    l.id_loja,
    l.nome AS nome_loja,
    p.id_produto,
    p.nome AS nome_produto,
    p.preco,
    p.categoria,
    e.quantidade,
    e.disponivel
FROM estoque e
JOIN produto p ON e.id_produto = p.id_produto
JOIN loja l ON e.id_loja = l.id_loja
WHERE l.id_loja = 1;

SELECT 
    l.id_loja,
    l.nome AS nome_loja,
    p.id_produto,
    p.nome AS nome_produto,
    p.preco,
    p.categoria,
    e.quantidade,
    e.disponivel
FROM estoque e
JOIN produto p ON e.id_produto = p.id_produto
JOIN loja l ON e.id_loja = l.id_loja
WHERE l.id_loja = 1;

SELECT * FROM produto
SELECT * FROM loja
SELECT * FROM estoque

INSERT INTO cliente (login, hash, nome, data_nascimento, email, contato, cpf, convidado, pontos_do_cliente) 
VALUES 
('yasmim_silva', '$2a$10$ExemploHashSenhaSegura123', 'Yasmim Silva', '1994-03-15', 'yasmim@email.com', '+351912345678', '12345678901', false, 150);

INSERT INTO cliente (login, hash, nome, data_nascimento, email, contato, cpf, convidado, pontos_do_cliente) 
VALUES 
(NULL, NULL, 'Visitante Balcão', NULL, NULL, NULL, NULL, true, 0);

SELECT * FROM cliente

UPDATE loja SET dono = 'João Exemplo' WHERE id_loja = 1;
UPDATE loja SET dono = 'Ana Fictícia' WHERE id_loja = 2;

UPDATE cliente 
SET nome = 'Maria Teste', 
    login = 'cliente_teste', 
    email = 'maria.teste@email.com', 
    cpf = '00011122233' 
WHERE id_cliente = 1;

INSERT INTO cliente (login, hash, nome, data_nascimento, email, contato, cpf, convidado, pontos_do_cliente) 
VALUES 
('cliente_teste', '$2a$10$ExemploHashSenhaSegura123', 'Maria Teste', '1995-05-20', 'maria.teste@email.com', '+351911111111', '00011122233', false, 100),
(NULL, NULL, 'Visitante Balcão', NULL, NULL, NULL, NULL, true, 0);