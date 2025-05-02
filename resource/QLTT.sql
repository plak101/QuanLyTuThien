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
    email NVARCHAR(100) unique NOT NULL,
    role ENUM('Admin', 'User') NOT NULL
);
-- Tạo bảng User
CREATE TABLE User (
    accountId int unique,
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
    imageUrl NVARCHAR(255),
    FOREIGN KEY (organizationId) REFERENCES organization(id)
);

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

--
-- Trigger
--

-- 1. trg kiem tra ngay tao event
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



-- 2. trg cap nhat so tien hien tai khi them donation
DELIMITER //

CREATE TRIGGER trg_after_insert_donation
AFTER INSERT ON Donation
FOR EACH ROW
BEGIN
    UPDATE Event
    SET currentAmount = currentAmount + NEW.amount
    WHERE eventId = NEW.eventId;
END;
//

DELIMITER ;


-- xóa dữ liêu để tạo mới 
-- DELETE FROM Donation;
-- DELETE FROM Event;
-- DELETE FROM User;
-- DELETE FROM account;
-- DELETE FROM Organization;

-- them du lieu vao bang account
INSERT INTO account (username, password, email, role) VALUES

('admin01', 'Admin@01', 'qltt.admin1st@gmail.com', 'Admin'),

('admin02', 'Admin@02', 'qltt.admin2nd@gmail.com', 'Admin'),
('admin03', 'Admin@03', 'qltt.admin3rd@gmail.com', 'Admin'),

('namnguyen', 'namDz=33', 'namnguyenvan@gmail.com', 'User'),
('bchicute', 'Bchi@1007', 'bchi98@gmail.com', 'User'),
('sydeptrai', 'sy99VOLUNT', 'syleeeee@gmail.com', 'User'),
('khoaloveangiang', 'Khoa@=1001', 'khoa.angiang@gmail.com', 'User'),
('3thuyduong', 'Duong&123', 'duongthuy@gmail.com', 'User'),
('minh4vuong', 'Vuong0%minH', 'minh.vuong@yahoo.com', 'User'),
('ngoclan007', '=lanNgoc22=', 'ngoclan.tran@outlook.com', 'User'),
('hoanganh357', 'AnhHoang#90', 'hoanganh.le@gmail.com', 'User'),
('xuanmai102', 'MaiXuan1^2', 'xuan.mai@gmail.com', 'User'),

('user', 'User@001', 'ductuan1992@gmail.com', 'User');

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
('Quỹ Vì Trẻ Em Khuyết Tật Việt Nam', 'quyvitreemkhuyettat@gmail.com', '0865019639', 'Số 25 Vũ Trọng Phụng, phường Thanh Xuân Trung, quận Thanh Xuân, Hà Nội'),
('Quỹ Từ tâm Đắk Lắk', 'quytutam@quytutamdaklak.com', '0869654747', '19 Tản Đà, P.Tân Lợi, TP Buôn Ma Thuột, tỉnh Đắk Lắk'),
('Trung tâm Con người và Thiên nhiên', 'contact@nature.org.vn', '02435564001', 'NV 31, Khu đô thị mới Trung Văn, p. Trung Văn, q. Nam Từ Liêm, Hà Nội'),
('Quỹ Hỗ trợ đổi mới giáo dục phổ thông Việt Nam', 'info@vigef.org', '0941959922', 'Phòng 302, tầng 3, số nhà 12-14 phố Lê Thánh Tông, P. Phan Chu Trinh, Q. Hoàn Kiếm, Tp Hà Nội'),
('Quỹ Từ thiện Nâng bước tuổi thơ', 'contact@children-of-vietnam.org', '0903035030', 'Tầng 6, SGR.O1-606, Saigon Royal, 34-35 Bến Vân Đồn, Phường 13, Quận 4, TP. Hồ Chí Minh'),
('Quỹ Từ tâm Biên Hoà', 'quytutambienhoa@gmail.com', '0922334455', 'Số 55 Đường Phạm Văn Thuận, Phường Tam Hiệp, TP. Biên Hòa, Đồng Nai'),
('Quỹ Bảo trợ Trẻ em', 'quytreemvn@molisa.gov.vn', '02438458568', '35 Trần Phú, Ba Đình, Hà Nội, Việt Nam'),
('Hội Chữ thập đỏ Việt Nam', 'international@redcross.org.vn', '02438224030', 'Số 82, Nguyễn Du, Quận Hai Bà Trưng, Hà Nội'),
('MSD United Way Vietnam', 'contact@msdvietnam.org', '02462769056', 'Phòng 1007, toà nhà 17T9 Hoàng Đạo Thuý, quận Thanh Xuân, Hà Nội, Việt Nam'),
('Quỹ Hy vọng', 'hope@quyhyvong.com', '0972776776', 'Tầng 9, Tòa nhà FPT Tower, số 10 Phạm Văn Bạch, Dịch Vọng, Cầu Giấy, Hà Nội');

-- thêm dữ liệu vào bảng Event
INSERT INTO Event (organizationId, eventname, category, description, targetAmount, currentAmount, dateBegin, dateEnd, imageUrl) VALUES
(1,'Tiếp sức đến trường 2025', 'Giáo dục', 'Hỗ trợ trẻ em nghèo đến trường', 50000000, 0, '2025-06-01', '2026-01-01', '/charity/image/tiepSucDenTruong.png'),
(2,'Ôi, ai cứu gương mặt cho em', 'Cứu trợ', 'Hỗ trợ điều trị, phục hồi cho cháu', 70000000, 0,  '2025-02-15', '2025-12-01','/charity/image/guongMatChoEm.jpeg'),
(3,'Rừng xanh lên 2025', 'Môi trường', 'Trồng cây gây rừng', 100000000,0,  '2025-03-10', '2025-12-31','/charity/image/rungXanhLen.jpg'),
(4,'Lớp học Hạnh phúc', 'Giáo dục', 'Kiến tạo trường học hạnh phúc: Trang bị phòng học theo mô hình STEM', 30000000, 0, '2025-04-20', '2025-05-20','/charity/image/lopHocHanhPhuc.png'),
(5,'Giúp em Lê Nguyễn Gia Bảo', 'Trẻ em', 'Hỗ trợ viện phí điều trị cho bé Lê Nguyễn Gia Bảo', 60000000, 0, '2025-09-20', '2025-11-30','/charity/image/leNguyenGiaBao.jpg'),
(6,'Giúp trò giỏi chữa tật mắt', 'Trẻ em', 'Chung tay để em Đỗ Hữu Dũng có cơ hội được đi khám bệnh, chữa trị mắt', 85000000, 0, '2025-03-31', '2025-10-21','/charity/image/chuTriMat.jpeg'),
(7,'Gom xe đạp, góp tương lai', 'Giáo dục', 'Hỗ trợ xe đạp cho trẻ em bị ảnh hưởng bởi thiên tai bão lũ', 5000000, 0, '2025-09-20', '2025-11-11','/charity/image/xeDap.png'),
(8,'Ghép đôi trăng tròn', 'Trẻ em', 'Góp phần làm trọn vẹn niềm vui rằm tháng 8', 120000000, 0, '2025-03-25', '2025-07-31','/charity/image/ghepDoiTrangTron.png'),
(9,'Cùng em bước qua bão Yagi, vì một Việt Nam kiên cường', 'Cứu trợ', 'Góp phần thực hiện hỗ trợ khắc phục hậu quả bão Yagi', 10000000000, 0, '2024-09-20', '2024-10-31','/charity/image/yagiCuuTro.jpg'),
(10,'Mang Tết Hy vọng đến các em nhỏ vùng cao', 'Trẻ em', 'Hãy chung tay cùng Quỹ mang Tết Hy vọng cho các em', 300000000, 0, '2025-06-20', '2026-01-01','/charity/image/tetHyVong.jpg');
 
 -- thêm dữ liệu vào bảng donation
 INSERT INTO Donation (userId, eventId, amount, donationDate, description) VALUES
(1, 1, 2000000, '2025-03-02 10:00:00', 'Ủng hộ Tiếp sức đến trường 2025'),
(2, 1, 10000000, '2025-03-15 14:30:00', 'Góp sức nhỏ cho Tiếp sức đến trường 2025'),
(3, 1, 3000000, '2025-03-28 18:00:00', 'Tấm lòng vàng cho Tiếp sức đến trường 2025'),
(4, 1, 500000, '2025-04-01 09:45:00', 'Chúc Tiếp sức đến trường 2025 thành công'),
(5, 2, 1500000, '2025-02-18 11:15:00', 'Ủng hộ Ôi, ai cứu gương mặt cho em'),
(6, 2, 8000000, '2025-03-10 16:45:00', 'Chia sẻ khó khăn cùng Ôi, ai cứu gương mặt cho em'),
(7, 2, 2500000, '2025-04-20 20:00:00', 'Cùng nhau giúp đỡ Ôi, ai cứu gương mặt cho em'),
(8, 2, 700000, '2025-04-30 12:30:00', 'Mong em bé của Ôi, ai cứu gương mặt cho em sớm bình an'),
(9, 3, 5000000, '2025-03-12 08:30:00', 'Góp phần vào Rừng xanh lên 2025'),
(10, 3, 12000000, '2025-04-05 15:00:00', 'Ủng hộ dự án Rừng xanh lên 2025'),
(11, 3, 4000000, '2025-05-15 19:30:00', 'Tấm lòng nhân ái cho Rừng xanh lên 2025'),
(12, 3, 900000, '2025-05-30 11:00:00', 'Chúc Rừng xanh lên 2025 thành công tốt đẹp'),
(13, 4, 1800000, '2025-04-18 13:45:00', 'Ủng hộ Lớp học Hạnh phúc'),
(1, 4, 6500000, '2025-05-05 17:15:00', 'Chắp cánh ước mơ cho Lớp học Hạnh phúc'),
(2, 4, 2200000, '2025-05-10 21:00:00', 'Hỗ trợ tri thức cho Lớp học Hạnh phúc'),
(3, 4, 600000, '2025-05-14 10:15:00', 'Cùng Lớp học Hạnh phúc kiến tạo tương lai'),
(4, 5, 3500000, '2025-03-22 09:00:00', 'Ủng hộ bé Lê Nguyễn Gia Bảo'),
(5, 5, 9500000, '2025-04-10 14:30:00', 'Tấm lòng sẻ chia cùng bé Lê Nguyễn Gia Bảo'),
(6, 5, 3000000, '2025-05-20 18:00:00', 'Gửi chút ấm áp đến bé Lê Nguyễn Gia Bảo'),
(7, 5, 800000, '2025-05-25 11:30:00', 'Chúc bé Lê Nguyễn Gia Bảo mau chóng khỏe lại'),
(8, 6, 7000000, '2025-03-05 10:30:00', 'Chung tay giúp đỡ trò giỏi chữa tật mắt'),
(9, 6, 15000000, '2025-04-25 15:00:00', 'Vì đôi mắt sáng của em Đỗ Hữu Dũng'),
(10, 6, 5500000, '2025-06-15 19:30:00', 'Hỗ trợ em Đỗ Hữu Dũng chữa trị mắt'),
(11, 6, 1200000, '2025-07-01 09:00:00', 'Chúc em Đỗ Hữu Dũng sớm có đôi mắt khỏe mạnh'),
(12, 7, 1200000, '2025-05-03 14:15:00', 'Góp xe đạp cho Gom xe đạp, góp tương lai'),
(13, 7, 4500000, '2025-05-15 17:45:00', 'Chắp cánh tương lai cùng Gom xe đạp, góp tương lai'),
(1, 7, 1500000, '2025-06-01 21:30:00', 'Hỗ trợ các em nhỏ của Gom xe đạp, góp tương lai'),
(2, 7, 400000, '2025-06-10 12:00:00', 'Mong các em có thêm phương tiện đến trường'),
(3, 8, 9000000, '2025-03-28 08:45:00', 'Góp phần làm trọn vẹn Ghép đôi trăng tròn'),
(4, 8, 20000000, '2025-04-15 16:15:00', 'Vì niềm vui rằm tháng 8 của các em'),
(5, 8, 7000000, '2025-06-30 20:45:00', 'Gửi tấm lòng đến Ghép đôi trăng tròn'),
(6, 8, 1800000, '2025-08-01 11:15:00', 'Chúc Ghép đôi trăng tròn thành công tốt đẹp'),
(7, 9, 2500000, '2025-09-05 13:00:00', 'Ủng hộ Cùng em bước qua bão Yagi'),
(8, 9, 10000000, '2025-09-20 17:30:00', 'Sưởi ấm trái tim Việt Nam kiên cường'),
(9, 9, 3500000, '2025-10-15 21:00:00', 'Chia sẻ hơi ấm cùng Cùng em bước qua bão Yagi'),
(10, 9, 900000, '2025-10-30 12:30:00', 'Mong Việt Nam sớm vượt qua khó khăn'),
(11, 10, 4500000, '2025-04-22 09:30:00', 'Góp sức cho Mang Tết Hy vọng đến các em nhỏ vùng cao'),
(12, 10, 11000000, '2025-05-10 14:00:00', 'Mang Tết ấm áp đến các em nhỏ'),
(13, 10, 3800000, '2025-06-20 18:30:00', 'Cùng nhau mang Tết Hy vọng'),
(1, 10, 1100000, '2025-07-15 10:00:00', 'Hướng tới một cái Tết trọn vẹn cho các em');


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



SELECT count(*) FROM user WHERE phone='0865019639'