create table categoria(
	codigoc int auto_increment not null,
    nome varchar(45) null,
    primary key (codigoc)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table tipolançamento(
	codigotl int auto_increment not null,
    receita int,
    despesa int,
    primary key (codigotl)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table pessoa(
	codigop int auto_increment not null,
    nome varchar(45) null,
    ativo boolean null,
    primary key (codigop)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table lancamento(
	codigol int auto_increment not null,
	descricao varchar(45) null,
    datavencimento date null,
    datapagamento date null,
    valor decimal(10) null,
    observacao varchar(50) null,
    codigocl int,
    codigotll int,
    codigopl int,
    primary key (codigol),
    foreign key (codigocl) references categoria (codigoc),
    foreign key (codigotll) references tipolançamento (codigotl),
    foreign key (codigopl) references pessoa (codigop)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table endereço(
	codigoe int auto_increment not null,
    logradouro varchar(60) null,
    numero varchar(20) null,
    complemento varchar(50) null,
    bairro varchar(30) null,
    cep varchar(10) null,
    cidade varchar(25) null,
    estado varchar(25) null,
    codigope int,
    primary key (codigoe),
    foreign key (codigope) references pessoa (codigop)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table permissao(
	codigope int auto_increment not null,
    descricao varchar(45) null,
    primary key (codigope)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table usuario (
	codigoU int auto_increment not null,
    nome varchar(45) null,
    email varchar(45) null,
    senha varchar(45) null,
    codigopeu int,
    primary key (codigoU),
    foreign key (codigopeu) references permissao (codigope)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


