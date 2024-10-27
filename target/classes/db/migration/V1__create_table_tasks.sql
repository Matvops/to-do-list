CREATE TABLE `tasks` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `completed` tinyint(1) DEFAULT '0',
  `day` enum('DOMINGO', 'SEGUNDA', 'TERCA', 'QUARTA', 'QUINTA', 'SEXTA', 'SABADO') NOT NULL,
  `name` varchar(255) NOT NULL,
  `priority` enum('BAIXA','MODERADA','ALTA') DEFAULT 'MODERADA',
  PRIMARY KEY (`id`)
)

