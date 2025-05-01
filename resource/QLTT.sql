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



-- xóa dữ liêu để tạo mới 
-- DELETE FROM Donation;
-- DELETE FROM Event;
-- DELETE FROM User;
-- DELETE FROM account;
-- DELETE FROM Organization;

-- them du lieu vao bang account
INSERT INTO account (username, password, email, role) VALUES
('admin01', 'admin01', 'qltt.admin1st@gmail.com', 'Admin'),
('admin02', 'admin02', 'qltt.admin2nd@gmail.com', 'Admin'),
('admin03', 'admin03', 'qltt.admin3rd@gmail.com', 'Admin'),

('namnguyen', 'namdz333', 'namnguyenvan@gmail.com', 'User'),
('bchicute', 'bchi1007', 'bchi98@gmail.com', 'User'),
('sydeptrai', 'sy99volunt', 'syleeeee@gmail.com', 'User'),
('khoaloveangiang', 'khoa1001', 'khoa.angiang@gmail.com', 'User'),
('3thuyduong', 'duong123', 'duongthuy@gmail.com', 'User'),
('minh4vuong', 'vuong0minh', 'minh.vuong@yahoo.com', 'User'),
('ngoclan007', 'lanngoc22', 'ngoclan.tran@outlook.com', 'User'),
('hoanganh357', 'anhhoang90', 'hoanganh.le@gmail.com', 'User'),
('xuanmai102', 'maixuan1khong2', 'xuan.mai@gmail.com', 'User'),

('user', 'user01', 'ductuan1992@gmail.com', 'User');

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
('Quỹ StudySchool Vì Trẻ Em', 'vitreem@gmail.com', '0938765432', 'Số 45 Đường Giải Phóng, Phường 4, Quận Tân Bình, TP.HCM'),
('Nhóm Cetray Tình Nguyện', 'tinhnguyenc@cetraygroup.net', '0979123456', 'Số 78 Đường Lê Duẩn, Phường Bến Nghé, Quận 1, Đà Nẵng'),
('CLB Doctorbook Sách và Hành Động', 'sachvahanhdong@doctorbook.edu.vn', '0911223344', 'Số 10 Đường Trần Phú, Phường Máy Chai, Quận Ngô Quyền, Hải Phòng'),
('Trung Tâm ECOwFriend Hỗ Trợ Cộng Đồng', 'hotrocongdong@gmail.com', '0988776655', 'Số 22 Đường 30 Tháng 4, Phường Hưng Lợi, Quận Ninh Kiều, Cần Thơ'),
('Doanh Nghiệp Xanh', 'fxanh@gmail.com', '0944556677', 'Số 33 Đường Đồng Khởi, Phường Phú Hòa, TP. Thủ Dầu Một, Bình Dương'),
('Sáng Kiến Lightest Môi Trường', 'moitruongg@gmail.com', '0922334455', 'Số 55 Đường Phạm Văn Thuận, Phường Tam Hiệp, TP. Biên Hòa, Đồng Nai'),
('Hội StrongWoman - Phụ Nữ Khởi Nghiệp', 'phunukhoinghiep@gmail.com', '0966554433', 'Số 66 Đường Hùng Vương, Phường Phú Nhuận, TP. Huế, Thừa Thiên Huế'),
('Mạng Lưới ILearning', 'giaoducmangluoi@gmail.com', '0955443322', 'Số 77 Đường 2 Tháng 4, Phường Vĩnh Phước, TP. Nha Trang, Khánh Hòa'),
('Tổ Chức Bảo Vệ Động Vật Defender', 'baovedongvat@gmail.com', '0933221100', 'Số 88 Đường Ba Cu, Phường 4, TP. Vũng Tàu, Bà Rịa - Vũng Tàu'),
('Liên Hiệp Thanh niên Forever', 'thanhnienk@gmail.com', '0909876543', 'Số 99 Đường Nguyễn Trãi, Phường Bến Thành, Quận 1, Hà Nội');

-- thêm dữ liệu vào bảng Event
INSERT INTO Event (organizationId, eventname, category, description, targetAmount, currentAmount, dateBegin, dateEnd) VALUES
(1,'Quyên góp từ thiện 1', 'Giáo dục', 'Hỗ trợ trẻ em nghèo đến trường', 50000000, 0, '2025-03-01', '2025-04-01'),
(2,'Hỗ trợ đồng bào miền Trung', 'Cứu trợ', 'Giúp đỡ người dân bị lũ lụt', 70000000, 0,  '2025-02-15', '2025-05-01'),
(3,'Quyên góp xây nhà tình thương', 'Nhà ở', 'Xây nhà cho hộ nghèo', 100000000, 0,  '2025-03-10', '2025-06-01'),
(4,'Chương trình Sách cho Em', 'Giáo dục', 'Quyên góp sách vở cho học sinh vùng cao', 30000000, 0, '2025-04-15', '2025-05-15'),
(5,'Ủng hộ Quỹ Tấm Lòng Vàng', 'Cứu trợ', 'Hỗ trợ người già neo đơn và trẻ em mồ côi', 60000000, 0, '2025-03-20', '2025-06-30'),
(6,'Dự án Nước sạch cho vùng sâu', 'Môi trường', 'Lắp đặt hệ thống lọc nước cho cộng đồng khó khăn', 85000000, 0, '2025-02-28', '2025-07-31'),
(7,'Quyên góp đồ dùng học tập', 'Giáo dục', 'Trao tặng cặp sách, bút vở cho học sinh nghèo', 25000000, 0, '2025-05-01', '2025-06-15'),
(8,'Hỗ trợ y tế cộng đồng', 'Y tế', 'Mua sắm thiết bị y tế cho trạm xá vùng xa', 120000000, 0, '2025-03-25', '2025-08-31'),
(9,'Chương trình Áo ấm mùa đông', 'Cứu trợ', 'Quyên góp áo ấm cho người vô gia cư và người nghèo', 40000000, 0, '2025-09-01', '2025-10-31'),
(10,'Dự án Cải thiện môi trường sống', 'Môi trường', 'Trồng cây xanh và vệ sinh khu dân cư', 55000000, 0, '2025-04-20', '2025-07-20');
 
 -- thêm dữ liệu vào bảng donation
 INSERT INTO Donation (userId, eventId, amount, donationDate, description) VALUES
(1, 1, 2000000, '2025-03-02 10:00:00', 'Ủng hộ giáo dục'),
(2, 1, 10000000, '2025-03-15 14:30:00', 'Góp sức nhỏ cho tương lai'),
(3, 1, 3000000, '2025-03-28 18:00:00', 'Tấm lòng vàng'),
(4, 1, 500000, '2025-04-01 09:45:00', 'Chúc chương trình thành công'),
(5, 2, 1500000, '2025-02-18 11:15:00', 'Hướng về miền Trung'),
(6, 2, 8000000, '2025-03-10 16:45:00', 'Chia sẻ khó khăn'),
(7, 2, 2500000, '2025-04-20 20:00:00', 'Cùng nhau vượt qua lũ'),
(8, 2, 700000, '2025-04-30 12:30:00', 'Mong mọi người bình an'),
(9, 3, 5000000, '2025-03-12 08:30:00', 'Xây dựng mái ấm'),
(10, 3, 12000000, '2025-04-05 15:00:00', 'Góp phần an cư'),
(11, 3, 4000000, '2025-05-15 19:30:00', 'Tấm lòng nhân ái'),
(12, 3, 900000, '2025-05-30 11:00:00', 'Chúc dự án thành công'),
(13, 4, 1800000, '2025-04-18 13:45:00', 'Sách cho em đến trường'),
(1, 4, 6500000, '2025-05-05 17:15:00', 'Chắp cánh ước mơ'),
(2, 4, 2200000, '2025-05-10 21:00:00', 'Hỗ trợ tri thức'),
(3, 4, 600000, '2025-05-14 10:15:00', 'Cùng em đến tương lai'),
(4, 5, 3500000, '2025-03-22 09:00:00', 'Ủng hộ người khó khăn'),
(5, 5, 9500000, '2025-04-10 14:30:00', 'Tấm lòng sẻ chia'),
(6, 5, 3000000, '2025-05-20 18:00:00', 'Gửi chút ấm áp'),
(7, 5, 800000, '2025-05-25 11:30:00', 'Chúc quỹ phát triển'),
(8, 6, 7000000, '2025-03-05 10:30:00', 'Vì một môi trường xanh'),
(9, 6, 15000000, '2025-04-25 15:00:00', 'Nước sạch cho mọi nhà'),
(10, 6, 5500000, '2025-06-15 19:30:00', 'Hỗ trợ cộng đồng'),
(11, 6, 1200000, '2025-07-01 09:00:00', 'Chúc dự án thành công tốt đẹp'),
(12, 7, 1200000, '2025-05-03 14:15:00', 'Đồ dùng học tập cho em'),
(13, 7, 4500000, '2025-05-15 17:45:00', 'Chắp cánh tương lai'),
(1, 7, 1500000, '2025-06-01 21:30:00', 'Hỗ trợ học sinh nghèo'),
(2, 7, 400000, '2025-06-10 12:00:00', 'Mong các em học tốt'),
(3, 8, 9000000, '2025-03-28 08:45:00', 'Hỗ trợ y tế vùng xa'),
(4, 8, 20000000, '2025-04-15 16:15:00', 'Vì sức khỏe cộng đồng'),
(5, 8, 7000000, '2025-06-30 20:45:00', 'Gửi tấm lòng đến trạm xá'),
(6, 8, 1800000, '2025-08-01 11:15:00', 'Chúc mọi người khỏe mạnh'),
(7, 9, 2500000, '2025-09-05 13:00:00', 'Áo ấm cho mùa đông'),
(8, 9, 10000000, '2025-09-20 17:30:00', 'Sưởi ấm trái tim'),
(9, 9, 3500000, '2025-10-15 21:00:00', 'Chia sẻ hơi ấm'),
(10, 9, 900000, '2025-10-30 12:30:00', 'Mong một mùa đông không lạnh'),
(11, 10, 4500000, '2025-04-22 09:30:00', 'Vì môi trường xanh sạch'),
(12, 10, 11000000, '2025-05-10 14:00:00', 'Góp sức bảo vệ hành tinh'),
(13, 10, 3800000, '2025-06-20 18:30:00', 'Cùng nhau hành động'),
(1, 10, 1100000, '2025-07-15 10:00:00', 'Hướng tới tương lai bền vững');


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