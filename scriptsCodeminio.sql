BEGIN;

INSERT INTO usuario(
            id, ativo, 
            cpf, email, login, nome, senha)
    VALUES (nextval('id_seq_usuario'), true, 
            '70875454429', 'admin@admin.com', 'admin', 'Admin da silva', '$2a$10$2EI/2Va3/d2JnoPG4Q39wOCOTtpWKJAiq9cRbSaEsjj.MbGHcrUHO');
INSERT INTO usuario(
            id, ativo, 
            cpf, email, login, nome, senha)
    VALUES (nextval('id_seq_usuario'), true, 
            '21453055487', 'funcionario@funcionario.com', 'funcionario', 'funcionario da silva', '$2a$10$2EI/2Va3/d2JnoPG4Q39wOCOTtpWKJAiq9cRbSaEsjj.MbGHcrUHO');

INSERT INTO role(
            id, nome_role)
    VALUES (1, 'ROLE_FUNCIONARIO');

INSERT INTO role(
            id, nome_role)
    VALUES (2, 'ROLE_MORADOR');

INSERT INTO role(
            id, nome_role)
    VALUES (3, 'ROLE_ADMIN');

INSERT INTO usuario_role(usuario_id, role_id)
	VALUES ((select id from usuario where login = 'funcionario'),1);
INSERT INTO usuario_role(usuario_id, role_id)
	VALUES ((select id from usuario where login = 'admin'),3);
COMMIT;
ROLLBACK;
