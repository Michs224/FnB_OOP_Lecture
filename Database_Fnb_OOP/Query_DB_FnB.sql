CREATE DATABASE DB_FnB;

USE DB_FnB;

-- Tabel Pelanggan
CREATE TABLE Customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    balance DOUBLE NOT NULL
);

-- Tabel Makanan
CREATE TABLE Foods (
    food_id INT PRIMARY KEY AUTO_INCREMENT,
    food_name VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    category VARCHAR(50) NOT NULL
);

-- Tabel Minuman
CREATE TABLE Beverages (
    beverage_id INT PRIMARY KEY AUTO_INCREMENT,
    beverage_name VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    type VARCHAR(50) NOT NULL,
    is_carbonated BOOLEAN NOT NULL
);

-- Tabel Pesanan
CREATE TABLE Orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DOUBLE NOT NULL,
    CONSTRAINT FK_customers_orders FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
    ON DELETE SET NULL
	ON UPDATE CASCADE
);

-- Tabel Detail Pesanan
CREATE TABLE OrderDetails (
    order_item_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT,
    food_id INT,
    beverage_id INT,
    quantity INT NOT NULL,
    subtotal DOUBLE NOT NULL,
    CONSTRAINT FK_orders_orderdetails FOREIGN KEY (order_id) REFERENCES Orders(order_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FK_food_orderdetails FOREIGN KEY (food_id) REFERENCES Foods(food_id)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
    CONSTRAINT FK_beverage_orderdetails FOREIGN KEY (beverage_id) REFERENCES Beverages(beverage_id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);