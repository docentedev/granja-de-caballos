CREATE TABLE tipos_caballo (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(1000),
    PRIMARY KEY (id),
    UNIQUE KEY uk_tipos_caballo_nombre (nombre)
);

CREATE TABLE caballos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    edad INT,
    tipo_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    KEY idx_caballos_tipo_id (tipo_id),
    CONSTRAINT fk_caballos_tipo FOREIGN KEY (tipo_id) REFERENCES tipos_caballo (id)
);
