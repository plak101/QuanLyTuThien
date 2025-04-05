DROP DATABASE IF EXISTS QLTT;
CREATE DATABASE QLTT;
USE QLTT;

-- Xóa bảng nếu đã tồn tại
DROP TABLE IF EXISTS Donation;
DROP TABLE IF EXISTS Event;
DROP TABLE IF EXISTS User;

-- Tạo bảng User
CREATE TABLE User (
    userId INT PRIMARY KEY AUTO_INCREMENT,
    userName NVARCHAR(100) NOT NULL,
    address NVARCHAR(255),
    phone NVARCHAR(10),
    gender ENUM('Nam', 'Nữ'),
    birthday DATE
);

-- Tạo bảng Event

CREATE TABLE Event (
    eventId INT PRIMARY KEY AUTO_INCREMENT,
    eventName NVARCHAR(255) NOT NULL,
    category NVARCHAR(100),
    targetAmount BIGINT NOT NULL,
    currentAmount BIGINT NOT NULL DEFAULT 0,
    dateBegin DATE NOT NULL,
    dateEnd DATE NOT NULL,
    description TEXT
);

DELIMITER //
CREATE TRIGGER before_insert_event
BEFORE INSERT ON Event
FOR EACH ROW
BEGIN
    IF NEW.dateBegin >= NEW.dateEnd THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'dateBegin phải nhỏ hơn dateEnd';
    END IF;
END;
//
DELIMITER ;

-- Tạo bảng Donation
CREATE TABLE Donation (
    donationId INT PRIMARY KEY AUTO_INCREMENT, 
    userId INT NOT NULL, 
    eventId INT NOT NULL, 
    amount BIGINT NOT NULL CHECK (amount>0), 
    donationDate DATE NOT NULL, 
    FOREIGN KEY (userId) REFERENCES User(userId) ON DELETE CASCADE, 
    FOREIGN KEY (eventId) REFERENCES Event(eventId) ON DELETE CASCADE
);
-- Tao bang to chuc
CREATE TABLE Organization (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    hotline VARCHAR(20) NOT NULL,
    address VARCHAR(255) NOT NULL
);

-- tao bang su kien da ban giao
CREATE TABLE AssignedEvent (
    id INT PRIMARY KEY AUTO_INCREMENT,
    eventId int NOT NULL,
    organizationId INT NOT NULL,
    assignedDate DATE NOT NULL,
	FOREIGN KEY (eventId) REFERENCES Event(eventId) ON DELETE CASCADE,
    FOREIGN KEY (organizationId) REFERENCES Organization(id) ON DELETE CASCADE
);


-- xóa dữ liêu để tạo mới 
DELETE FROM Donation;
DELETE FROM Event;
DELETE FROM User;
DELETE FROM AssignedEvent;
DELETE FROM Organization;


-- thêm dữ liệu vào bảng User
INSERT INTO User (userName, address, phone, gender, birthDay) VALUES
('Nguyễn Văn A', 'Hà Nội', '0123456789', 'Nam', '1990-05-10'),
('Trần Thị B', 'Hồ Chí Minh', '0987654321', 'Nữ', '1995-08-20'),
('Lê Văn C', 'Đà Nẵng', '0369852147', 'Nam', '1988-12-05');

-- thêm dữ liệu vào bảng Event
INSERT INTO Event (eventName, category, description, targetAmount, currentAmount, dateBegin, dateEnd) VALUES
('Quyên góp từ thiện 1', 'Giáo dục', 'Hỗ trợ trẻ em nghèo đến trường', 50000000, 1000000, '2025-03-01', '2025-04-01'),
('Hỗ trợ đồng bào miền Trung', 'Cứu trợ', 'Giúp đỡ người dân bị lũ lụt', 70000000,200000,  '2025-02-15', '2025-05-01'),
('Quyên góp xây nhà tình thương', 'Nhà ở', 'Xây nhà cho hộ nghèo', 100000000,300000,  '2025-03-10', '2025-06-01');
INSERT INTO Event (eventName, category, description, targetAmount, currentAmount, dateBegin, dateEnd) VALUES
('Quyên góp từ thiện 2', 'Giáo dục', 'Hỗ trợ trẻ em nghèo đến trường', 50000000, 1000000, '2025-03-01', '2025-04-01');
 
 
 -- thêm dữ liệu vào bảng donation
 INSERT INTO Donation (userId, eventId, amount, donationDate) VALUES
(1, 1, 5000000, '2025-03-05'),
(2, 1, 2000000, '2025-03-07'),
(3, 2, 10000000, '2025-03-10'),
(1, 3, 8000000, '2025-03-12'),
(2, 2, 5000000, '2025-03-15'),
(3, 3, 15000000, '2025-03-20');

-- Them du lieu vao bang to chuc
INSERT INTO Organization (name, email, hotline, address) VALUES
('Tổ chức A', 'contact@orga.com', '0901234567', '123 Đường ABC, Hà Nội'),
('Tổ chức B', 'info@orgb.com', '0912345678', '456 Đường XYZ, TP HCM'),
('Tổ chức C', 'support@orgc.com', '0987654321', '789 Đường LMN, Đà Nẵng');

-- Them du lieu vao bang su kien da ban giao
INSERT INTO AssignedEvent (organizationId, assignedDate) VALUES
(1, '2024-04-01'),
(2, '2024-04-02'),
(3, '2024-04-03'),
(1, '2024-04-04'); -- Tổ chức A nhận thêm một sự kiện khác

-- Xem dữ liệu trong bảng Event
SELECT * FROM Event;
SELECT * FROM User;
SELECT * FROM Donation;
SELECT * FROM organization;
SELECT * FROM assignedevent;
-- donation list
SELECT donationId, eventName, userName, amount, donationDate
FROM donation d
join user u on d.userId= u.userId
join event e on e.eventId = d.eventId
order by donationId asc;

SELECT donationId, eventName, userName, amount, donationDate
FROM qltt.donation d
join user u on d.userId= u.userId
join event e on e.eventId = d.eventId
where d.userId =1
order by donationId asc;

-- get donationByUserId
-- SELECT *
-- FROM qltt.donation d
-- where d.userId = ?
-- order by donationId asc;

INSERT INTO Event (eventName, category, description, targetAmount, currentAmount, dateBegin, dateEnd) VALUES
('Quyên góp từ thiện 4', 'Giáo dục', 'Hỗ trợ trẻ em nghèo đến trường', 50000000, 1000000, '2025-03-01', '2025-04-01');

INSERT INTO Event (eventName, category, description, targetAmount, currentAmount, dateBegin, dateEnd) VALUES
('Quyên góp từ thiện 67', 'Giáo dục', 'Hỗ trợ trẻ em nghèo đến trường', 50000000, 1000000, '2024-03-01', '2024-04-01');
SELECT * FROM event
 WHERE dateEnd > CURRENT_DATE