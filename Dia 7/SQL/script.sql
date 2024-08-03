CREATE DATABASE IF NOT EXISTS bjewy0x6rprm9meqk6cm;

USE bjewy0x6rprm9meqk6cm;

-- Tabla para Partidos
CREATE TABLE partidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    equipo_local VARCHAR(50) NOT NULL,
    equipo_visitante VARCHAR(50) NOT NULL,
    fecha DATE NOT NULL,
    cestas_local INT DEFAULT 0,
    cestas_visitante INT DEFAULT 0,
    finalizado VARCHAR(3) DEFAULT 'No', -- Cambiado a VARCHAR
    jornada INT,
    ronda VARCHAR(20)
);


-- Insertar partidos no finalizados
INSERT INTO partidos (equipo_local, equipo_visitante, fecha, jornada, finalizado) VALUES
('Dragones de Fuego', 'Leones de Acero', '2024-07-15', 1, 'No'),
('Tiburones del Atlántico', 'Cóndores del Desierto', '2024-07-16', 1, 'No'),
('Ratas del Asfalto', 'Águilas de la Ciudad', '2024-07-17', 1, 'No'),
('Unicornos de Niebla', 'Gladiadores del Sol', '2024-07-18', 1, 'No'),
('Ninjas del Valle', 'Saurios del Pantano', '2024-07-19', 1, 'No');

-- Insertar partidos finalizados
INSERT INTO partidos (equipo_local, equipo_visitante, fecha, cestas_local, cestas_visitante, finalizado) VALUES
('Dragones de Fuego', 'Leones de Acero', '2024-07-15', 102, 98, 'Sí'),
('Tiburones del Atlántico', 'Cóndores del Desierto', '2024-07-16', 88, 92, 'Sí'),
('Ratas del Asfalto', 'Águilas de la Ciudad', '2024-07-17', 110, 105, 'Sí'),
('Unicornos de Niebla', 'Gladiadores del Sol', '2024-07-18', 95, 95, 'Sí'),
('Ninjas del Valle', 'Saurios del Pantano', '2024-07-19', 89, 85, 'Sí');

-- Inserciones para partidos de PlayOffs
-- No finalizados
INSERT INTO partidos (equipo_local, equipo_visitante, fecha, ronda, finalizado) VALUES
('Guerreros del Ártico', 'Tiburones del Atlántico', '2024-08-01', 'Cuartos', 'No'),
('Fantasmas del Noroeste', 'Águilas de la Ciudad', '2024-08-02', 'Cuartos', 'No');

-- Finalizados
INSERT INTO partidos (equipo_local, equipo_visitante, fecha, cestas_local, cestas_visitante, finalizado, ronda) VALUES
('Guerreros del Ártico', 'Tiburones del Atlántico', '2024-08-01', 101, 97, 'Sí', 'Cuartos'),
('Fantasmas del Noroeste', 'Águilas de la Ciudad', '2024-08-02', 99, 91, 'Sí', 'Cuartos');
