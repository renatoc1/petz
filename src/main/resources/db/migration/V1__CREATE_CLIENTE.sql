CREATE TABLE `cliente` (
  `ID_CLIENTE` int NOT NULL AUTO_INCREMENT,
  `NOME` varchar(45) DEFAULT NULL,
  `CPF` varchar(45) NOT NULL,
  `IDADE` varchar(45) DEFAULT NULL,
  `SEXO` varchar(45) DEFAULT NULL,
  `PROFISSAO` varchar(45) DEFAULT NULL,
  `TELEFONE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID_CLIENTE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;