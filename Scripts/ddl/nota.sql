CREATE TABLE notas
(
   id               int(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
   detalle          varchar(255) NOT NULL,
   idIncidencia   int(20) NOT NULL,
   idUsuario int(20) NOT NULL,
   fechaPublicacion TIMESTAMP,
   estado INT
)