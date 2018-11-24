CREATE TABLE public.agendamento (
    cd_agendamento integer NOT NULL,
    cd_veiculo integer NOT NULL,
    ds_origem character varying(100) NOT NULL,
    ds_destino character varying(100) NOT NULL,
    cd_motorista integer NOT NULL,
    cd_funcionario integer NOT NULL,
    dt_saida date NOT NULL,
    dt_retorno date NOT NULL,
    nr_passageiros integer NOT NULL,
    ds_observacao character varying(255)
);

CREATE TABLE public.funcionario (
    cd_funcionario integer NOT NULL,
    nm_funcionario character varying(100) NOT NULL,
    nr_matricula integer NOT NULL
);

CREATE TABLE public.motorista (
    cd_motorista integer NOT NULL,
    cd_funcionario integer NOT NULL,
    nr_cnh character varying(50) NOT NULL,
    dt_vencimento date NOT NULL
);

CREATE TABLE public.veiculo (
    cd_veiculo integer NOT NULL,
    nr_placa character varying(20) NOT NULL,
    nr_ano integer NOT NULL,
    nr_passageiros integer NOT NULL
);

ALTER TABLE ONLY public.agendamento
    ADD CONSTRAINT pk_agendamento PRIMARY KEY (cd_agendamento);

ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT pk_funcionario PRIMARY KEY (cd_funcionario);

ALTER TABLE ONLY public.motorista
    ADD CONSTRAINT pk_motorista PRIMARY KEY (cd_motorista, cd_funcionario);

ALTER TABLE ONLY public.veiculo
    ADD CONSTRAINT pk_veiculo PRIMARY KEY (cd_veiculo);

ALTER TABLE ONLY public.agendamento
    ADD CONSTRAINT fk_agendamento_motorista FOREIGN KEY (cd_motorista, cd_funcionario) REFERENCES public.motorista(cd_motorista, cd_funcionario);

ALTER TABLE ONLY public.agendamento
    ADD CONSTRAINT fk_agendamento_veiculo FOREIGN KEY (cd_veiculo) REFERENCES public.veiculo(cd_veiculo);

