DROP TABLE IF EXISTS Images;
CREATE TABLE Images
(
    image_id   VARCHAR(36) PRIMARY KEY,
    image_data BYTEA
);
DROP TABLE IF EXISTS Users;
CREATE TABLE Users
(
    username   VARCHAR(64) PRIMARY KEY,
    password   VARCHAR(32)  NOT NULL,
    first_name VARCHAR(128) NOT NULL,
    last_name  VARCHAR(128) NOT NULL,
    email      VARCHAR(256) NOT NULL,
    birthday   DATE,
    visible    BOOLEAN,
    image      VARCHAR(36),
    FOREIGN KEY (image) REFERENCES Images (image_id)
);
DROP TABLE IF EXISTS Roles;
CREATE TABLE Roles
(
    role     VARCHAR(32) NOT NULL,
    username VARCHAR(64) NOT NULL,
    FOREIGN KEY (username) REFERENCES Users (username)
);
DROP TABLE IF EXISTS AnimalTypes;
CREATE TABLE AnimalTypes
(
    type_name VARCHAR(64) PRIMARY KEY
);

DROP TABLE IF EXISTS Animals;
CREATE TABLE Animals
(
    animal_id          VARCHAR(36) PRIMARY KEY,
    animal_name        VARCHAR(64)   NOT NULL,
    animal_description VARCHAR(1024) NOT NULL,
    animal_age         INTEGER       NOT NULL,
    animal_type        VARCHAR(16)   NOT NULL,
    animal_owner       VARCHAR(64) NULL,
    image              VARCHAR(36),
    visible            BOOLEAN,
    FOREIGN KEY (animal_owner) REFERENCES Users (username),
    FOREIGN KEY (image) REFERENCES Images (image_id),
    FOREIGN KEY (animal_type) REFERENCES AnimalTypes (type_name)
);
