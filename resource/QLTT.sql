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

INSERT INTO chuong_trinh (ten_chuong_trinh, mo_ta, ngay_bat_dau, ngay_ket_thuc, tong_kinh_phi, trang_thai)
VALUES
('Chương trình Mùa hè xanh', 'Tham gia hỗ trợ cộng đồng vùng sâu vùng xa.', '2025-06-01', '2025-08-01', 50000000, 'Đang thực hiện'),
('Chương trình Hiến máu nhân đạo', 'Tổ chức vận động hiến máu cứu người.', '2025-05-15', '2025-05-20', 20000000, 'Đã hoàn thành'),
('Chương trình Tết cho người nghèo', 'Gây quỹ và tặng quà cho hộ nghèo dịp Tết.', '2024-12-01', '2025-01-31', 100000000, 'Đang chuẩn bị'),
('Chương trình Bảo vệ môi trường', 'Tuyên truyền và tổ chức hoạt động bảo vệ môi trường.', '2025-09-01', '2025-11-30', 30000000, 'Chưa bắt đầu');

INSERT INTO nha_tai_tro (ten, dia_chi, so_dien_thoai, email)
VALUES
('Công ty ABC', '123 Đường Lê Lợi, Quận 1, TP.HCM', '0901234567', 'lienhe@abc.com'),
('Tập đoàn XYZ', '456 Đường Nguyễn Trãi, Quận 5, TP.HCM', '0912345678', 'contact@xyz.vn'),
('Nhà tài trợ Thiện Nguyện', '789 Đường Lý Thường Kiệt, Quận 10, TP.HCM', '0923456789', 'support@thiennguyen.org'),
('Công ty TNHH Phúc An', '12 Đường Hoàng Văn Thụ, Quận Tân Bình, TP.HCM', '0934567890', 'phucan@gmail.com'),
('Tập đoàn Bảo Minh', '88 Đường Trường Chinh, Quận Tân Phú, TP.HCM', '0945678901', 'info@baominh.vn');


-- xóa dữ liêu để tạo mới 
-- DELETE FROM Donation;
-- DELETE FROM Event;
-- DELETE FROM User;
-- DELETE FROM account;
-- DELETE FROM Organization;

-- them du lieu vao bang account
INSERT INTO account (username, password, email, role) VALUES
('admin01', 'admin', 'admin01@example.com', 'Admin'),
('user01', 'user', 'user01@example.com', 'User'),
('user02', 'user', 'user02@example.com', 'User'),
('user03', 'user', 'user03@example.com', 'User'),
('1', '1', 'user03@example.com', 'User');

-- thêm dữ liệu vào bảng User
INSERT INTO User (accountId, userName, address, phone, gender, birthDay) VALUES
(1,'Nguyễn Văn Admin', 'Hà Nội', '0123456789', 'Nam', '1990-05-10'),
(2,'Nguyễn Văn A', 'Hà Nội', '0123456789', 'Nam', '1990-05-10'),
(3,'Trần Thị B', 'Hồ Chí Minh', '0987654321', 'Nữ', '1995-08-20'),
(4,'Lê Văn C', 'Đà Nẵng', '0369852147', 'Nam', '1988-12-05');

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