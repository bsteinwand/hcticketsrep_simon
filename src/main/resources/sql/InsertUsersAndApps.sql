
INSERT INTO hctickets.user 
(FirstName, LastName, Email, SMSNumber, CreationDate, InactiveDate, Status, Salt, PasswordHash, NotificationPreference, RoleId)
VALUES
('Bryon', 'Steinwand', 'bryon.steinwand@helenacollege.edu', '104064399925', NOW(), NULL, 'Active',
 'f3a9c7d2e8414b39a6f2c1a98b5d77c', '', 'Email', 1);

INSERT INTO hctickets.user
(FirstName, LastName, Email, SMSNumber, CreationDate, InactiveDate, Status, Salt, PasswordHash, NotificationPreference, RoleId)
VALUES
('Brian', 'Cooper', 'brian.cooper@example.com', '15555550102', NOW(), NULL, 'Active',
 '9b2f4d1ae3c84fa0b8d4a7c6f192e0ab', '', 'Email', 2);

INSERT INTO hctickets.user
(FirstName, LastName, Email, SMSNumber, CreationDate, InactiveDate, Status, Salt, PasswordHash, NotificationPreference, RoleId)
VALUES
('Chloe', 'Reed', 'chloe.reed@example.com', '15555550103', NOW(), NULL, 'Active',
 '7c1e9a54d0bb4c5f9a38e2d4f61a9b03', '', 'Email', 2);

INSERT INTO hctickets.user
(FirstName, LastName, Email, SMSNumber, CreationDate, InactiveDate, Status, Salt, PasswordHash, NotificationPreference, RoleId)
VALUES
('Daniel', 'Nguyen', 'daniel.nguyen@example.com', '15555550104', NOW(), NULL, 'Active',
 '2e4b7a9c3f1d4683b7a2d9e5c4f0a1b2', '', 'Email', 3);

INSERT INTO hctickets.user 
(FirstName, LastName, Email, SMSNumber, CreationDate, InactiveDate, Status, Salt, PasswordHash, NotificationPreference, RoleId)
VALUES
('Eva', 'Simmons', 'eva.simmons@example.com', '15555550105', NOW(), NULL, 'Active',
 'a9c4e1b27f3d4a88b2e7c9f01d5a6c3e', '', 'Email', 3);

INSERT INTO hctickets.user
(FirstName, LastName, Email, SMSNumber, CreationDate, InactiveDate, Status, Salt, PasswordHash, NotificationPreference, RoleId)
VALUES
('Felix', 'Turner', 'felix.turner@example.com', '15555550106', NOW(), NULL, 'Active',
 '5d7e9a1c2b4f4d8e9c0a3b7f2e6d1c8a', '', 'Email', 3);

INSERT INTO hctickets.user
(FirstName, LastName, Email, SMSNumber, CreationDate, InactiveDate, Status, Salt, PasswordHash, NotificationPreference, RoleId)
VALUES
('Grace', 'Hernandez', 'grace.hernandez@example.com', '15555550107', NOW(), NULL, 'Active',
 'c1d4e7f92a3b4c5d8e9f0a1b2c3d4e5f', '', 'Email', 3);

INSERT INTO hctickets.user
(FirstName, LastName, Email, SMSNumber, CreationDate, InactiveDate, Status, Salt, PasswordHash, NotificationPreference, RoleId)
VALUES
('Henry', 'Brooks', 'henry.brooks@example.com', '15555550108', NOW(), NULL, 'Active',
 '8e2f1c3b4d5a6e7f9b0c1d2e3f4a5b6c', '', 'Email', 4);

INSERT INTO hctickets.user
(FirstName, LastName, Email, SMSNumber, CreationDate, InactiveDate, Status, Salt, PasswordHash, NotificationPreference, RoleId)
VALUES
('Isabel', 'Price', 'isabel.price@example.com', '15555550109', NOW(), NULL, 'Active',
 '3a5c7e9f1b2d4c6e8a0b1c2d3e4f5a6b', '', 'Email', 4);

INSERT INTO hctickets.user
(FirstName, LastName, Email, SMSNumber, CreationDate, InactiveDate, Status, Salt, PasswordHash, NotificationPreference, RoleId)
VALUES
('Jacob', 'Foster', 'jacob.foster@example.com', '15555550110', NOW(), NULL, 'Active',
 'd0e1f2a3b4c5d6e7f8091a2b3c4d5e6f', '', 'Email', 4);
 
 
 
INSERT INTO hctickets.application(id, appname, description, creationdate, status)
	VALUES (1, 'HC Tickets', 'HC Help Desk App', NOW(), 'Active');

INSERT INTO hctickets.application(id, appname, description, creationdate, status)
	VALUES (2, 'HC CRM', 'HC CRM', NOW(), 'Active');	
	

