CREATE DATABASE studentDB;

CREATE TABLE `studentDB`.`student` (
  `id` int(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `surname` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
    `age` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;