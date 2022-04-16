create table categoria(
	codigoc int auto_increment not null,
    nome varchar(45) not null,
    primary key (codigoc)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO categoria (nome) VALUES ('Lazer');
INSERT INTO categoria (nome) VALUES ('Informática');
INSERT INTO categoria (nome) VALUES ('Saúde');
INSERT INTO categoria (nome) VALUES ('Finanças');
