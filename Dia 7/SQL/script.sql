CREATE DATABASE IF NOT EXISTS bjewy0x6rprm9meqk6cm;

USE bjewy0x6rprm9meqk6cm;

CREATE TABLE partidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    equipo_local VARCHAR(255) NOT NULL,
    equipo_visitante VARCHAR(255) NOT NULL,
    fecha DATE NOT NULL,
    cestas_local INT DEFAULT 0,
    cestas_visitante INT DEFAULT 0,
    finalizado BOOLEAN DEFAULT FALSE,
    jornada INT NULL,
    ronda VARCHAR(255) NULL
);
