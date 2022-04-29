create table categoria(
	codigoc int auto_increment not null,
    nome varchar(45) not null,
    primary key (codigoc)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO categoria (nome) values ('Lazer');
INSERT INTO categoria (nome) values ('Alimentação');
INSERT INTO categoria (nome) values ('Supermercado');
INSERT INTO categoria (nome) values ('Farmácia');
INSERT INTO categoria (nome) values ('Outros');