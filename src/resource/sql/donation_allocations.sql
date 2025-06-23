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
    giftType VARCHAR(255),
    totalGifts INT,
    giftValue DOUBLE,
    numRecipients INT,
    criteria VARCHAR(255),
    recipientList TEXT,
    shippingCost DOUBLE,
    receipt VARCHAR(255),
    feedback TEXT,
    FOREIGN KEY (eventId) REFERENCES Event(eventId),
    FOREIGN KEY (createdBy) REFERENCES User(userId)
);

-- Nếu đã có bảng, thêm các trường mới bằng ALTER TABLE (an toàn khi migrate)
ALTER TABLE donation_allocations 
    ADD COLUMN IF NOT EXISTS recipient VARCHAR(255),
    ADD COLUMN IF NOT EXISTS giftType VARCHAR(255),
    ADD COLUMN IF NOT EXISTS totalGifts INT,
    ADD COLUMN IF NOT EXISTS giftValue DOUBLE,
    ADD COLUMN IF NOT EXISTS numRecipients INT,
    ADD COLUMN IF NOT EXISTS criteria VARCHAR(255),
    ADD COLUMN IF NOT EXISTS recipientList TEXT,
    ADD COLUMN IF NOT EXISTS shippingCost DOUBLE,
    ADD COLUMN IF NOT EXISTS receipt VARCHAR(255),
    ADD COLUMN IF NOT EXISTS feedback TEXT;
