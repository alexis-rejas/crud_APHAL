CREATE DATABASE IF NOT EXISTS vallegrande_db;

USE vallegrande_db;

CREATE TABLE IF NOT EXISTS usuarios (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    nombre    VARCHAR(100) NOT NULL,
    correo    VARCHAR(100) NOT NULL,
    telefono  VARCHAR(20)  NOT NULL,
    asunto    VARCHAR(100) NOT NULL,
    mensaje   TEXT         NOT NULL
);
