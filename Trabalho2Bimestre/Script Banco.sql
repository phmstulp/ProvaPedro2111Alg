
CREATE TABLE public.pessoa (
                id INTEGER NOT NULL,
                nome VARCHAR(60) NOT NULL,
                CONSTRAINT pessoa_pk PRIMARY KEY (id)
);


CREATE TABLE public.produto (
                cd_produto INTEGER NOT NULL,
                descricao VARCHAR NOT NULL,
                vl_unitario NUMERIC(15,2) NOT NULL,
                CONSTRAINT produto_pk PRIMARY KEY (cd_produto)
);


CREATE TABLE public.empresa (
                id INTEGER NOT NULL,
                cnpj VARCHAR(18) NOT NULL,
                telefone INTEGER NOT NULL,
                endereco VARCHAR(100) NOT NULL,
                ie VARCHAR(9) NOT NULL,
                CONSTRAINT empresa_pk PRIMARY KEY (id)
);


CREATE TABLE public.cliente (
                id INTEGER NOT NULL,
                cpf VARCHAR(14) NOT NULL,
                dtnascimento DATE NOT NULL,
                telefone INTEGER NOT NULL,
                endereco VARCHAR(100) NOT NULL,
                rg VARCHAR(14) NOT NULL,
                CONSTRAINT cliente_pk PRIMARY KEY (id)
);


CREATE TABLE public.pedido (
                cd_pedido INTEGER NOT NULL,
                vl_total NUMERIC(15,2) NOT NULL,
                pago BOOLEAN NOT NULL,
                id_cliente INTEGER NOT NULL,
                id_empresa INTEGER NOT NULL,
                CONSTRAINT pedido_pk PRIMARY KEY (cd_pedido)
);


CREATE TABLE public.itempedido (
                cd_itempedido INTEGER NOT NULL,
                cd_pedido INTEGER NOT NULL,
                cd_produto INTEGER NOT NULL,
                quantidade INTEGER NOT NULL,
                vl_total NUMERIC(15,2) NOT NULL,
                CONSTRAINT itempedido_pk PRIMARY KEY (cd_itempedido)
);


ALTER TABLE public.cliente ADD CONSTRAINT pessoa_cliente_fk
FOREIGN KEY (id)
REFERENCES public.pessoa (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.empresa ADD CONSTRAINT pessoa_empresa_fk
FOREIGN KEY (id)
REFERENCES public.pessoa (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.itempedido ADD CONSTRAINT produto_itempedido_fk
FOREIGN KEY (cd_produto)
REFERENCES public.produto (cd_produto)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.pedido ADD CONSTRAINT empresa_pedido_fk
FOREIGN KEY (id_empresa)
REFERENCES public.empresa (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.pedido ADD CONSTRAINT cliente_pedido_fk
FOREIGN KEY (id_cliente)
REFERENCES public.cliente (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.itempedido ADD CONSTRAINT pedido_itempedido_fk
FOREIGN KEY (cd_pedido)
REFERENCES public.pedido (cd_pedido)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
