CREATE TABLE `car` (
                       `car_id` int NOT NULL AUTO_INCREMENT,
                       `frame_number` varchar(255) NOT NULL,
                       `brand` varchar(255) NOT NULL,
                       `model` varchar(255) NOT NULL,
                       `colour` varchar(255) NOT NULL,
                       `gear_type` varchar(255) NOT NULL,
                       `price` decimal(10,2) NOT NULL,
                       `registration_fee` decimal(10,2) NOT NULL,
                       `emission` decimal(10,2) NOT NULL,
                       `acquisition_date` date NOT NULL,
                       `fuel_type` varchar(255) NOT NULL,
                       PRIMARY KEY (`car_id`),
                       UNIQUE KEY `frame_number` (`frame_number`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `car_buyer` (
                             `buyer_id` int NOT NULL AUTO_INCREMENT,
                             `company_name` varchar(255) NOT NULL,
                             `company_number` varchar(50) NOT NULL,
                             `company_email` varchar(255) NOT NULL,
                             PRIMARY KEY (`buyer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `car_sale` (
                            `sale_id` int NOT NULL AUTO_INCREMENT,
                            `purchase_date` date DEFAULT NULL,
                            `transport_date` date DEFAULT NULL,
                            `transport_type` varchar(255) DEFAULT NULL,
                            `buyer_id` int NOT NULL,
                            `car_id` int NOT NULL,
                            PRIMARY KEY (`sale_id`),
                            UNIQUE KEY `buyer_id_UNIQUE` (`buyer_id`),
                            UNIQUE KEY `car_id_UNIQUE` (`car_id`),
                            CONSTRAINT `fk_buyer_id` FOREIGN KEY (`buyer_id`) REFERENCES `car_buyer` (`buyer_id`),
                            CONSTRAINT `fk_sale_car` FOREIGN KEY (`car_id`) REFERENCES `car` (`car_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `car_status` (
                              `car_status_id` int NOT NULL AUTO_INCREMENT,
                              `car_status_type` varchar(255) DEFAULT NULL,
                              `car_status_date` date DEFAULT NULL,
                              `car_id` int DEFAULT NULL,
                              PRIMARY KEY (`car_status_id`),
                              UNIQUE KEY `car_id_UNIQUE` (`car_id`),
                              CONSTRAINT `fk_carstatus_car` FOREIGN KEY (`car_id`) REFERENCES `car` (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `rental_agreement` (
                                    `contract_id` int NOT NULL AUTO_INCREMENT,
                                    `employee_id` int DEFAULT NULL,
                                    `car_id` int DEFAULT NULL,
                                    `contract_number` varchar(255) NOT NULL,
                                    `pickup_location` varchar(255) NOT NULL,
                                    `contract_start_date` date NOT NULL,
                                    `contract_end_date` date NOT NULL,
                                    `contract_type` varchar(255) NOT NULL,
                                    `contract_monthly_fee` double NOT NULL,
                                    `mileage_per_month` double NOT NULL,
                                    PRIMARY KEY (`contract_id`),
                                    UNIQUE KEY `car_id_UNIQUE` (`car_id`),
                                    KEY `EmployeeID` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
CREATE TABLE `damage_report` (
                                 `damage_report_id` int NOT NULL AUTO_INCREMENT,
                                 `contract_id` int NOT NULL,
                                 `paint_damage` tinyint(1) DEFAULT '0',
                                 `rim_damage` tinyint(1) DEFAULT '0',
                                 `windshield_damage` tinyint(1) DEFAULT '0',
                                 `mileage` int DEFAULT '0',
                                 `report_date` date DEFAULT NULL,
                                 `damage_report_done` tinyint(1) DEFAULT '0',
                                 PRIMARY KEY (`damage_report_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `employee` (
                            `employee_id` int NOT NULL AUTO_INCREMENT,
                            `firstname` varchar(255) NOT NULL,
                            `lastname` varchar(255) NOT NULL,
                            `user_type` varchar(255) NOT NULL,
                            `username` varchar(255) NOT NULL,
                            `password` varchar(255) NOT NULL,
                            PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
