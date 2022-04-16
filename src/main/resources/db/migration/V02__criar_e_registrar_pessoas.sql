create table pessoa(
	codigop int auto_increment not null,
    nome varchar(45) not null,
    logradouro varchar(60) not null,
    numero varchar(20) not null,
    complemento varchar(50) null,
    bairro varchar(30) not null,
    cep varchar(10) not null,
    cidade varchar(25) not null,
    estado varchar(25) not null,
    ativo boolean not null,
    primary key (codigop)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO pessoa(nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('Douglas', 'Rua Florianópolis', '1930', 'kitnet 05', 'Parque Independência', '85884000', 'Medianeira', 'Paraná', true);
INSERT INTO pessoa(nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('Jordinho', 'Rua Bahia', '1930', 'Casa azul', 'Conda', '85884000', 'Medianeira', 'Paraná', true);
INSERT INTO pessoa(nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('Priscila', 'Avenida Brasília', '5', 'apto 302', 'Centro', '85884000', 'Medianeira', 'Paraná', true);