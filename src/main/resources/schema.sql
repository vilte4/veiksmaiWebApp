DROP TABLE IF EXISTS Vartotojas;

CREATE TABLE Veiksmai (
                          id INT AUTO_INCREMENT  PRIMARY KEY,
                          veiksmas VARCHAR(250) NOT NULL,
                          vartotojoID int NOT NULL,
                          data VARCHAR(250) NOT NULL
);

CREATE TABLE Vartotojas (
                        id INT AUTO_INCREMENT  PRIMARY KEY,
                        vardas VARCHAR(250) NOT NULL,
                        telNr VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS Veiksmai;

