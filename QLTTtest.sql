create	database QLTT
CREATE TABLE qltt.User (
    userId INT PRIMARY KEY AUTO_INCREMENT,
    userName VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(15),
    gender ENUM('Male', 'Female', 'Other'),
    birthDay DATE
);
CREATE TABLE qltt.Event (
    eventId INT PRIMARY KEY AUTO_INCREMENT, 
    eventName VARCHAR(255) NOT NULL, 
    category VARCHAR(100), 
    description TEXT, 
    targetAmount BIGINT NOT NULL, 
    currentAmount BIGINT NOT NULL DEFAULT 0
);
CREATE TABLE qltt.Donation (
    donationId INT PRIMARY KEY AUTO_INCREMENT, 
    userId INT NOT NULL, 
    eventId INT NOT NULL, 
    amount BIGINT NOT NULL, 
    donationDate DATE NOT NULL, 
    FOREIGN KEY (userId) REFERENCES User(userid) ON DELETE CASCADE, 
    FOREIGN KEY (eventId) REFERENCES Event(eventId) ON DELETE CASCADE
);