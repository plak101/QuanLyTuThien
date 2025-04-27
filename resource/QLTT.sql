DROP DATABASE IF EXISTS QLTT;
CREATE DATABASE QLTT;
USE QLTT;

-- Xóa bảng nếu đã tồn tại
DROP TABLE IF EXISTS Donation;
DROP TABLE IF EXISTS Event;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS organization;
-- tạo bảng tài khoản
CREATE TABLE account (
	id INT PRIMARY KEY AUTO_INCREMENT,
    username NVARCHAR(50) NOT NULL UNIQUE,
    password NVARCHAR(100) NOT NULL,
    email NVARCHAR(100) NOT NULL,
    role ENUM('Admin', 'User') NOT NULL
);
-- Tạo bảng User
CREATE TABLE User (
    accountId int ,
    userId INT PRIMARY KEY AUTO_INCREMENT,
    userName NVARCHAR(100) NOT NULL,
    address NVARCHAR(255),
    phone NVARCHAR(10),
    gender ENUM('Nam', 'Nữ'),
    birthday DATE,
    FOREIGN KEY (accountId) REFERENCES account(id) ON DELETE CASCADE
);

-- Tao bang to chuc
CREATE TABLE Organization (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    hotline VARCHAR(20) NOT NULL,
    address VARCHAR(255) NOT NULL
);
-- Tạo bảng Event
CREATE TABLE Event (
    eventId INT PRIMARY KEY AUTO_INCREMENT,
    organizationId INT NOT NULL,
    eventName NVARCHAR(255) NOT NULL,
    category NVARCHAR(100),
    targetAmount BIGINT NOT NULL,
    currentAmount BIGINT NOT NULL DEFAULT 0,
    dateBegin DATE NOT NULL,
    dateEnd DATE NOT NULL,
    description TEXT,
    FOREIGN KEY (organizationId) REFERENCES organization(id)
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
    donationDate DATETIME NOT NULL, 
    description TEXT,
    FOREIGN KEY (userId) REFERENCES User(userId) ON DELETE CASCADE, 
    FOREIGN KEY (eventId) REFERENCES Event(eventId) ON DELETE CASCADE
);
-- Tao bảng nhà tài trợ
CREATE TABLE nha_tai_tro(
	id INT PRIMARY KEY AUTO_INCREMENT,
    ten NVARCHAR(100) NOT NULL,
    dia_chi NVARCHAR(255),
    so_dien_thoai NVARCHAR(20),
    email NVARCHAR(100)
);

CREATE TABLE chuong_trinh (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten_chuong_trinh NVARCHAR(255) NOT NULL,
    mo_ta TEXT,
    ngay_bat_dau DATE,
    ngay_ket_thuc DATE,
    tong_kinh_phi DOUBLE DEFAULT 0,
    trang_thai NVARCHAR(50)
);
drop table chuong_trinh;
drop table nha_tai_tro;




-- xóa dữ liêu để tạo mới 
-- DELETE FROM Donation;
-- DELETE FROM Event;
-- DELETE FROM User;
-- DELETE FROM account;
-- DELETE FROM Organization;

-- them du lieu vao bang account
INSERT INTO account (username, password, email, role) VALUES
('admin01', 'khoi_Admin01', 'qltt.admin1st@gmail.com', 'Admin'),
('admin02', 'ngan_Admin02', 'qltt.admin2nd@gmail.com', 'Admin'),
('admin03', 'khoa_Admin03', 'qltt.admin3rd@gmail.com', 'Admin'),

('namnguyen', 'namdz333', 'namnguyenvan@gmail.com', 'User'),
('bchicute', 'bchi1007', 'bchi98@gmail.com', 'User'),
('sydeptrai', 'sy99volunt', 'syleeeee@gmail.com', 'User'),
('khoaloveangiang', 'khoa1001', 'khoa.angiang@gmail.com', 'User'),
('3thuyduong', 'duong123', 'duongthuy@gmail.com', 'User'),
('minh4vuong', 'vuong0minh', 'minh.vuong@yahoo.com', 'User'),
('ngoclan007', 'lanngoc22', 'ngoclan.tran@outlook.com', 'User'),
('hoanganh357', 'anhhoang90', 'hoanganh.le@gmail.com', 'User'),
('xuanmai102', 'maixuan1khong2', 'xuan.mai@gmail.com', 'User'),
('ductuan69', 'tuan.duc', 'ductuan1992@gmail.com', 'User');

-- thêm dữ liệu vào bảng User
INSERT INTO User (accountId, userName, address, phone, gender, birthDay) VALUES
(1,'Ngô Thành Khôi', 'Bình Thuận', '0915194857', 'Nam', '2005-05-03'),
(2,'Trần Thanh Ngân', 'Hà Nội', '0123456789', 'Nam', '2005-05-10'),
(3,'Phạm Lê Anh Khoa', 'An Giang', '0987654321', 'Nữ', '2005-01-10'),

(4,'Nguyễn Văn Nam', 'Hải Phòng', '0912345678', 'Nam', '1993-11-25'),
(5,'Trần Thị Bích Chi', 'Bình Dương', '0336987412', 'Nữ', '2000-03-15'),
(6,'Bùi Phạm Sỹ Thịnh', 'Cần Thơ', '0777123456', 'Nam', '1985-07-01'),
(7,'Lê Đỗ Anh Khoa', 'An Giang', '0888555222', 'Nam', '1997-09-30'),
(8,'Đậu Thuỳ Dương', 'Huế', '0555999111', 'Nữ', '1992-04-18'),
(9,'Trịnh Minh Vương', 'Nha Trang', '0963214785', 'Nam', '1989-01-03'),
(10,'Phạm Thị Ngọc Lan', 'Hà Nam', '0398765432', 'Nữ', '1998-06-12'),
(11,'Võ Hoàng Anh', 'Thái Bình', '0707418529', 'Nam', '1994-12-22'),
(12,'Trần Vũ Xuân Mai', 'Vũng Tàu', '0869321654', 'Nữ', '1991-02-08'),
(13,'Ngô Đức Tuấn', 'Lạng Sơn', '0941789632', 'Nam', '1996-10-27');

-- Them du lieu vao bang to chuc
INSERT INTO Organization (name, email, hotline, address) VALUES
('Tổ chức A', 'contact@orga.com', '0901234567', '123 Đường ABC, Hà Nội'),
('Tổ chức B', 'info@orgb.com', '0912345678', '456 Đường XYZ, TP HCM'),
('Tổ chức C', 'support@orgc.com', '0987654321', '789 Đường LMN, Đà Nẵng');

-- thêm dữ liệu vào bảng Event
INSERT INTO Event (organizationId, eventname, category, description, targetAmount, currentAmount, dateBegin, dateEnd) VALUES
(1,'Quyên góp từ thiện 1', 'Giáo dục', 'Hỗ trợ trẻ em nghèo đến trường', 50000000, 1000000, '2025-03-01', '2025-04-01'),
(2,'Hỗ trợ đồng bào miền Trung', 'Cứu trợ', 'Giúp đỡ người dân bị lũ lụt', 70000000,200000,  '2025-02-15', '2025-05-01'),
(3,'Quyên góp xây nhà tình thương', 'Nhà ở', 'Xây nhà cho hộ nghèo', 100000000,300000,  '2025-03-10', '2025-06-01'),
(1,'Quyên góp xây nhà tình thương 3', 'Nhà ở', 'Xây nhà cho hộ nghèo', 100000000,300000,  '2025-03-10', '2025-06-01');
 
 -- thêm dữ liệu vào bảng donation
 INSERT INTO Donation (userId, eventId, amount, donationDate, description) VALUES
(1, 1, 5000000, '2025-03-05 09:30:00', 'Chuc ban may man'),
(2, 1, 2000000, '2025-03-07 14:45:00', ''),
(3, 2, 10000000, '2025-03-10 11:20:00', ''),
(1, 3, 8000000, '2025-03-12 08:15:00', ''),
(2, 2, 5000000, '2025-03-15 16:50:00', ''),
(3, 3, 15000000, '2025-03-20 19:00:00', '');


-- Xem dữ liệu trong bảng Event
SELECT * FROM account;
SELECT * FROM User;
SELECT * FROM Event;
SELECT * FROM Donation;
SELECT * FROM organization;
-- donation list
-- SELECT donationId, eventName, userName, amount, donationDate
-- FROM donation d
-- join user u on d.userId= u.userId
-- join event e on e.eventId = d.eventId
-- order by donationId asc;

-- SELECT donationId, eventName, userName, amount, donationDate
-- FROM qltt.donation d
-- join user u on d.userId= u.userId
-- join event e on e.eventId = d.eventId
-- where d.userId =1
-- order by donationId asc;

-- get donationByUserId
-- SELECT *
-- FROM qltt.donation d
-- where d.userId = ?
-- order by donationId asc;

-- SELECT * FROM event
--  WHERE dateEnd > CURRENT_DATE;
--  
--  
-- SELECT * FROM account WHERE username LIKE 'admin01' AND password LIKE 'admin';

-- SELECT a.* 
-- FROM account a
-- JOIN User u ON a.id = u.accountId
-- WHERE u.userId = ?;