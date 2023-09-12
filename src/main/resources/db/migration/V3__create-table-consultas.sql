create table consultas(
    id bigint not null auto_increment,
    medico bigint not null,
    paciente bigint not null,
    dia integer not null,
    mes integer not null,
    ano integer not null,
    hora integer not null,
    minutos integer not null,
    ativo tinyint not null,
    done tinyint not null,

    PRIMARY KEY (id)
);
