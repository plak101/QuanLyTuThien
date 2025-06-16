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
    FOREIGN KEY (eventId) REFERENCES Event(eventId),
    FOREIGN KEY (createdBy) REFERENCES User(userId)
);
