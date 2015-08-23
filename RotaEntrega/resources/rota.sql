USE ROTA;

CREATE TABLE IF NOT EXISTS TRECHO (
  TRECHO_ID INT(10) NOT NULL AUTO_INCREMENT,
  ORIGEM VARCHAR(15) NOT NULL,
  DESTINO VARCHAR(15) NOT NULL,
  DISTANCIA FLOAT(10) NOT NULL,
  PRIMARY KEY (TRECHO_ID),
  UNIQUE (ORIGEM, DESTINO)
);