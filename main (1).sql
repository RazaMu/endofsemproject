CREATE DATABASE ForestManagement;

USE ForestManagement;

CREATE TABLE Visitors (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE PaymentHistory (
    id INT PRIMARY KEY AUTO_INCREMENT,
    visitor_id INT,
    payment_type VARCHAR(50),
    amount DECIMAL(10, 2),
    payment_date DATE,
    FOREIGN KEY (visitor_id) REFERENCES Visitors(id)
);
