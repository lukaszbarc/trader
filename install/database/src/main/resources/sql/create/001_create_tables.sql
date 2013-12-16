CREATE TABLE day
(
    identity INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    date DATE NOT NULL
);
CREATE TABLE stock
(
    identity INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    symbol VARCHAR(10) NOT NULL
);
CREATE TABLE stockdata
(
    identity INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    day_id INT NOT NULL,
    stock_id INT NOT NULL,
    open DECIMAL(9,2) NOT NULL,
    max DECIMAL(9,2) NOT NULL,
    min DECIMAL(9,2) NOT NULL,
    close DECIMAL(9,2) NOT NULL,
    turnover INT NOT NULL
);
CREATE UNIQUE INDEX unique_date ON day ( date );
CREATE UNIQUE INDEX unique_symbol ON stock ( symbol );

ALTER TABLE stockdata ADD FOREIGN KEY ( stock_id ) REFERENCES stock ( identity );
ALTER TABLE stockdata ADD FOREIGN KEY ( day_id ) REFERENCES day ( identity );
