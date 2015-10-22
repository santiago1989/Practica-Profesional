CREATE TABLE incidencias
(
   id                    int(20),
   `idOwner`             int(20),
   `idResponsable`       int(20),
   `idEstado`            int(20),
   `idPrioridad`         int(20),
   `idTipoIncidencia`    int(20),
   titulo                varchar(255),
   detalle               varchar(255),
   `fechaCreacion`       timestamp DEFAULT 'CURRENT_TIMESTAMP',
   `fechaModificacion`   timestamp DEFAULT '0000-00-00 00:00:00'
)