create table categoria(
	codigoc int auto_increment not null,
    nome varchar(45) not null,
    primary key (codigoc)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table tipolançamento(
	codigotl int auto_increment not null,
    receita int not null,
    despesa int not null,
    primary key (codigotl)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table pessoa(
	codigop int auto_increment not null,
    nome varchar(45) not null,
    ativo boolean not null,
    primary key (codigop)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table lancamento(
	codigol int auto_increment not null,
	descricao varchar(45) not null,
    datavencimento date not null,
    datapagamento date not null,
    valor decimal(10) not null,
    observacao varchar(50) not null,
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
    logradouro varchar(60) not null,
    numero varchar(20) not null,
    complemento varchar(50) not null,
    bairro varchar(30) not null,
    cep varchar(10) not null,
    cidade varchar(25) not null,
    estado varchar(25) not null,
    codigope int,
    primary key (codigoe),
    foreign key (codigope) references pessoa (codigop)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table permissao(
	codigope int auto_increment not null,
    descricao varchar(45) not null,
    primary key (codigope)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table usuario (
	codigoU int auto_increment not null,
    nome varchar(45) not null,
    email varchar(45) not null,
    senha varchar(45) not null,
    codigopeu int,
    primary key (codigoU),
    foreign key (codigopeu) references permissao (codigope)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


