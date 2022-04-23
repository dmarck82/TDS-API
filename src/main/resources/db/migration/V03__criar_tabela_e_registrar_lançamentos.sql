create table lancamento (
    codigol int auto_increment not null,
    descricao varchar(50) not null,
    data_vencimento date not null,
    data_pagamento date,
    valor decimal(10,2) not null,
    observacao varchar(100),
    tipo varchar(20) not null,
    codigo_categoria int(20) not null,
    codigo_pessoa int(20) not null,
    primary key (codigol),
    foreign key (codigo_categoria) references categoria(codigoc),
    foreign key (codigo_pessoa) references pessoa(codigop)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

insert into lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Salario Mensal', '2017-06-10', null, 6500.00, 'Receita', 'RECEITA', 1, 1);
insert into lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Bahamas', '2017-02-10', '2017-02-10', 100.32, 'Despesa', 'DESPESA', 2, 2);
insert into lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Supermercado', '2017-02-10', '2017-02-10', 257.32, 'Despesa', 'DESPESA', 2, 3);