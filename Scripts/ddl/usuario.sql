CREATE TABLE usuarios
(
   legajo       int(10),
   nombre       varchar(255),
   apellido     varchar(255),
   contrasena   varchar(255),
   dni          varchar(8),
   telefono     varchar(20),
   estado       int(1) DEFAULT 1,
   correo       varchar(255)
)