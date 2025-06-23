USE QLTT;

DROP TABLE IF EXISTS Distribution;
DROP TABLE IF EXISTS donation_allocations;

-- Thêm các trường mới vào bảng Donation
ALTER TABLE Donation 
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

-- Tạo bảng donation_allocations để lưu thông tin phân bổ tiền cho sự kiện
CREATE TABLE IF NOT EXISTS donation_allocations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    eventId INT NOT NULL,
    amount DOUBLE NOT NULL,
    purpose VARCHAR(500),
    status VARCHAR(50) NOT NULL DEFAULT 'PENDING',
    allocationDate DATE NOT NULL,
    usedAmount DOUBLE DEFAULT 0,
    evidenceUrl VARCHAR(500),
    createdBy INT NOT NULL,
    recipient VARCHAR(255),
    numRecipients INT,
    criteria VARCHAR(500),
    recipientList TEXT,
    giftType VARCHAR(255),
    giftValue VARCHAR(255),
    totalGifts INT,
    shippingCost DOUBLE,
    receipt VARCHAR(500),
    feedback TEXT,
    FOREIGN KEY (eventId) REFERENCES Event(eventId),
    FOREIGN KEY (createdBy) REFERENCES User(userId)
);
