CREATE DATABASE `jdbc_pao` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE `bicycle` (
  `bike_id` varchar(45) NOT NULL,
  `bike_type` varchar(45) DEFAULT NULL,
  `propulsion` varchar(45) DEFAULT NULL,
  `v_id` int NOT NULL,
  PRIMARY KEY (`bike_id`),
  UNIQUE KEY `bike_id_UNIQUE` (`bike_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `car` (
  `v_id` int NOT NULL AUTO_INCREMENT,
  `car_id` varchar(50) NOT NULL,
  `car_type` varchar(20) DEFAULT NULL,
  `engine` varchar(50) DEFAULT NULL,
  `fuel_consumption` double DEFAULT NULL,
  `seats` int DEFAULT NULL,
  PRIMARY KEY (`car_id`),
  UNIQUE KEY `v_id_UNIQUE` (`v_id`),
  UNIQUE KEY `car_id_UNIQUE` (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `contract` (
  `id` int NOT NULL,
  `con_id` varchar(45) NOT NULL,
  `customer_id` int NOT NULL,
  `veh_id` int NOT NULL,
  `date` date DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `fee` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `con_id_UNIQUE` (`con_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `customer_id_UNIQUE` (`customer_id`),
  UNIQUE KEY `veh_id_UNIQUE` (`veh_id`),
  CONSTRAINT `contract_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `contract_ibfk_2` FOREIGN KEY (`veh_id`) REFERENCES `vehicle` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `customer` (
  `id` int NOT NULL,
  `customer_id` varchar(45) NOT NULL,
  `fullName` varchar(50) DEFAULT NULL,
  `phoneNumber` varchar(45) DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `street_number` int DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `county` varchar(45) DEFAULT NULL,
  `postal` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `motorcycle` (
  `moto_id` varchar(45) NOT NULL,
  `moto_type` varchar(45) DEFAULT NULL,
  `engine` varchar(45) DEFAULT NULL,
  `no_wheels` int DEFAULT NULL,
  `v_id` int NOT NULL,
  PRIMARY KEY (`moto_id`),
  UNIQUE KEY `moto_id_UNIQUE` (`moto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `review` (
  `rev_id` varchar(45) NOT NULL,
  `body` varchar(200) DEFAULT NULL,
  `rating` int DEFAULT NULL,
  `satisfied` tinyint DEFAULT NULL,
  `c_id` int NOT NULL,
  PRIMARY KEY (`c_id`),
  UNIQUE KEY `rev_id_UNIQUE` (`rev_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `vehicle` (
  `id` int NOT NULL AUTO_INCREMENT,
  `veh_id` varchar(50) DEFAULT NULL,
  `Make` varchar(45) DEFAULT NULL,
  `Model` varchar(45) DEFAULT NULL,
  `Base` varchar(45) DEFAULT NULL,
  `Year` int DEFAULT NULL,
  `Color` varchar(45) DEFAULT NULL,
  `Horsepower` int DEFAULT NULL,
  `TopSpeed` int DEFAULT NULL,
  `Price` double DEFAULT NULL,
  `PricePerDay` double DEFAULT NULL,
  `Available` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `veh_id_UNIQUE` (`veh_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
