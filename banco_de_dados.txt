-- Cria o banco de dados
CREATE DATABASE IF NOT EXISTS metro;
USE metro;

-- Remove tabelas existentes na ordem correta
DROP TABLE IF EXISTS tarefas_realizadas;
DROP TABLE IF EXISTS tarefas;
DROP TABLE IF EXISTS metroviarios;
DROP TABLE IF EXISTS administradores;

-- Tabela de metroviários
CREATE TABLE metroviarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(30) NOT NULL,
    registro VARCHAR(8) UNIQUE NOT NULL,
    pontuacao_total INT DEFAULT 0,
    data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    CHECK (registro REGEXP '^R[0-9]{5}-[0-9]$'),
    CHECK (email LIKE '%@metrosp.com.br'),
    CHECK (senha REGEXP '.*[!@#$%^&*(),.?":{}|<>].*')
);

-- Tabela de administradores
CREATE TABLE administradores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(30) NOT NULL,
    registro VARCHAR(8) UNIQUE NOT NULL,
    data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    CHECK (email LIKE '%@metrosp.com.br'),
    CHECK (senha REGEXP '.*[!@#$%^&*(),.?":{}|<>].*'),
    CHECK (registro REGEXP '^R[0-9]{5}-[0-9]$')
);

-- Tabela de tarefas com pontuações
CREATE TABLE tarefas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT,
    valor_pontuacao INT NOT NULL DEFAULT 0,
    data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de registro de tarefas realizadas
CREATE TABLE tarefas_realizadas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_metroviario INT NOT NULL,
    id_tarefa INT NOT NULL,
    pontuacao_obtida INT NOT NULL DEFAULT 0,
    data_realizacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_metroviario) REFERENCES metroviarios(id),
    FOREIGN KEY (id_tarefa) REFERENCES tarefas(id)
);

-- Função para calcular pontuação total
DELIMITER //
CREATE FUNCTION calcular_pontuacao_total(p_id_metroviario INT) 
RETURNS INT
READS SQL DATA
BEGIN
    DECLARE total INT;
    SELECT COALESCE(SUM(pontuacao_obtida), 0) INTO total
    FROM tarefas_realizadas
    WHERE id_metroviario = p_id_metroviario;
    RETURN total;
END //
DELIMITER ;

-- Procedure para atualizar pontuação
DELIMITER //
CREATE PROCEDURE atualizar_pontuacao_metroviario(IN p_id_metroviario INT)
BEGIN
    UPDATE metroviarios
    SET pontuacao_total = calcular_pontuacao_total(p_id_metroviario)
    WHERE id = p_id_metroviario;
END //
DELIMITER ;

-- Trigger para chamar a procedure após inserção
DELIMITER //
CREATE TRIGGER after_tarefa_realizada
AFTER INSERT ON tarefas_realizadas
FOR EACH ROW
BEGIN
    CALL atualizar_pontuacao_metroviario(NEW.id_metroviario);
END //
DELIMITER ;

-- Dados iniciais
INSERT INTO metroviarios (nome, email, senha, registro) VALUES
("Nikolas", "nikolas@metrosp.com.br", "Segur@99", "R12345-6"),
("Guilherme Calderan", "guilherme15@metrosp.com.br", "Metrô#2024", "R01758-2"),
("Marcos", "marcos@metrosp.com.br", "Test#Senha", "R22222-2"),
("Guilherme Nunes", "guilhermeswing@metrosp.com.br", "G@mes456", "R33333-3"),
("Guilherme 43", "guiherme43@metrosp.com.br", "4343!", "R43434-3"),
("Metroviario", "metroviario@metrosp.com.br", "Metro!", "R00000-1");

INSERT INTO administradores (nome, email, senha, registro) VALUES
("Demetrius", "demetrius@metrosp.com.br", "Adm!9999", "R99999-9"),
("Admin", "admin@metrosp.com.br", "Admin!", "R00000-0");

-- Inserir tarefas do jogo
INSERT INTO tarefas (titulo, valor_pontuacao) VALUES
('Posicionar reversora em frente - início', 1),
('Posicionar chave CBCT em RM', 1),
('Acionar PA inicial', 1),
('Fechar portas pela botoeira lateral', 1),
('Informar CCO sobre porta não fechada', 2),
('Acionar PA após identificar problema', 1),
('Pegar equipamentos (cinturão, adesivo, chave)', 3),
('Verificar soleira da porta', 1),
('Verificar dispositivo de emergência', 1),
('Informar CCO sobre verificações', 2),
('Isolar porta no painel externo', 3),
('Fechar e travar porta manualmente', 2),
('Colocar adesivo na porta', 2),
('Verificar luz de portas externas', 2),
('Informar CCO após isolamento', 2),
('Acionar PA final', 1),
('Inserir chave de serviço', 1),
('Posicionar chave CBCT em AM - final', 1),
('Posicionar reversora em frente - final', 1);