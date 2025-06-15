-- Thêm các trường mới vào bảng Donation
ALTER TABLE Donation 
ADD COLUMN status VARCHAR(50) DEFAULT 'PENDING',
ADD COLUMN processingNote TEXT,
ADD COLUMN processedDate DATETIME,
ADD COLUMN processedBy INT,
ADD FOREIGN KEY (processedBy) REFERENCES User(userId);

-- Tạo bảng Distribution để lưu thông tin phân phối tiền
CREATE TABLE IF NOT EXISTS Distribution (
    id INT PRIMARY KEY AUTO_INCREMENT,
    donationId INT,
    eventId INT,
    amount DOUBLE,
    distributionDate DATETIME,
    note TEXT,
    distributedBy INT,
    FOREIGN KEY (donationId) REFERENCES Donation(donationId),
    FOREIGN KEY (eventId) REFERENCES Event(eventId),
    FOREIGN KEY (distributedBy) REFERENCES User(userId)
);
