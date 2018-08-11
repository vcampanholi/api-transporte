CREATE SEQUENCE SEQ_CLIENTES AS BIGINT START WITH 1 INCREMENT BY 1;CREATE TABLE CLIENTES (  ID BIGINT GENERATED BY DEFAULT AS SEQUENCE SEQ_CLIENTES PRIMARY KEY,  NOME VARCHAR(100) NOT NULL,  DOCUMENTO VARCHAR(14) NOT NULL,  TELEFONE BIGINT,  EMAIL VARCHAR(100),  OBSERVACAO VARCHAR(255));CREATE SEQUENCE SEQ_CLIENTES_LINHA AS BIGINT START WITH 1 INCREMENT BY 1;CREATE TABLE CLIENTES_LINHA (  ID BIGINT GENERATED BY DEFAULT AS SEQUENCE SEQ_CLIENTES_LINHA PRIMARY KEY,  I_CLIENTE BIGINT,  TIPO_TRANSPORTE INT,  FOREIGN KEY (I_CLIENTE) REFERENCES CLIENTES(ID));CREATE SEQUENCE SEQ_LINHA_ONIBUS AS BIGINT START WITH 1 INCREMENT BY 1;CREATE TABLE LINHA_ONIBUS (  ID BIGINT GENERATED BY DEFAULT AS SEQUENCE SEQ_LINHA_ONIBUS PRIMARY KEY,  NOME VARCHAR(255),  CODIGO VARCHAR(12));CREATE SEQUENCE SEQ_LINHA_LOTACAO AS BIGINT START WITH 1 INCREMENT BY 1;CREATE TABLE LINHA_LOTACAO (  ID BIGINT GENERATED BY DEFAULT AS SEQUENCE SEQ_LINHA_LOTACAO PRIMARY KEY,  NOME VARCHAR(255),  CODIGO VARCHAR(12));