--
-- PostgreSQL database dump
--

-- Dumped from database version 10.4
-- Dumped by pg_dump version 10.4

-- Started on 2018-11-21 18:49:56

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2820 (class 1262 OID 41874)
-- Name: agendamentodb; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE agendamentodb WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';


ALTER DATABASE agendamentodb OWNER TO postgres;

\connect agendamentodb

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2822 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 199 (class 1259 OID 41893)
-- Name: agendamento; Type: TABLE; Schema: public; Owner: postgres
--

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


ALTER TABLE public.agendamento OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 41883)
-- Name: funcionario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.funcionario (
    cd_funcionario integer NOT NULL,
    nm_funcionario character varying(100) NOT NULL,
    nr_matricula integer NOT NULL
);


ALTER TABLE public.funcionario OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 41888)
-- Name: motorista; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.motorista (
    cd_motorista integer NOT NULL,
    cd_funcionario integer NOT NULL,
    nr_cnh character varying(50) NOT NULL,
    dt_vencimento date NOT NULL
);


ALTER TABLE public.motorista OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 41878)
-- Name: veiculo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.veiculo (
    cd_veiculo integer NOT NULL,
    nr_placa character varying(20) NOT NULL,
    nr_ano date NOT NULL,
    nr_passageiros date NOT NULL
);


ALTER TABLE public.veiculo OWNER TO postgres;

--
-- TOC entry 2814 (class 0 OID 41893)
-- Dependencies: 199
-- Data for Name: agendamento; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2812 (class 0 OID 41883)
-- Dependencies: 197
-- Data for Name: funcionario; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2813 (class 0 OID 41888)
-- Dependencies: 198
-- Data for Name: motorista; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2811 (class 0 OID 41878)
-- Dependencies: 196
-- Data for Name: veiculo; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2687 (class 2606 OID 41897)
-- Name: agendamento pk_agendamento; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agendamento
    ADD CONSTRAINT pk_agendamento PRIMARY KEY (cd_agendamento);


--
-- TOC entry 2683 (class 2606 OID 41887)
-- Name: funcionario pk_funcionario; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT pk_funcionario PRIMARY KEY (cd_funcionario);


--
-- TOC entry 2685 (class 2606 OID 41892)
-- Name: motorista pk_motorista; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.motorista
    ADD CONSTRAINT pk_motorista PRIMARY KEY (cd_motorista, cd_funcionario);


--
-- TOC entry 2681 (class 2606 OID 41882)
-- Name: veiculo pk_veiculo; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.veiculo
    ADD CONSTRAINT pk_veiculo PRIMARY KEY (cd_veiculo);


--
-- TOC entry 2689 (class 2606 OID 41903)
-- Name: agendamento fk_agendamento_motorista; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agendamento
    ADD CONSTRAINT fk_agendamento_motorista FOREIGN KEY (cd_motorista, cd_funcionario) REFERENCES public.motorista(cd_motorista, cd_funcionario);


--
-- TOC entry 2688 (class 2606 OID 41898)
-- Name: agendamento fk_agendamento_veiculo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agendamento
    ADD CONSTRAINT fk_agendamento_veiculo FOREIGN KEY (cd_veiculo) REFERENCES public.veiculo(cd_veiculo);


-- Completed on 2018-11-21 18:49:57

--
-- PostgreSQL database dump complete
--

