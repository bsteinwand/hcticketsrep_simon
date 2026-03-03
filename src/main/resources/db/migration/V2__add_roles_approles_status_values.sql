INSERT INTO hctickets.role(
	id, rolename)
	VALUES (1, 'Admin');

INSERT INTO hctickets.role(
	id, rolename)
	VALUES (2, 'Faculty');
	
INSERT INTO hctickets.role(
	id, rolename)
	VALUES (3, 'Technician');	

INSERT INTO hctickets.role(
	id, rolename)
	VALUES (4, 'App. User');	
	

INSERT INTO hctickets.applicationrole(
	id, rolename)
	VALUES (1, 'Admin');

INSERT INTO hctickets.applicationrole(
	id, rolename)
	VALUES (2, 'Faculty');
	
INSERT INTO hctickets.applicationrole(
	id, rolename)
	VALUES (3, 'Technician');
	
INSERT INTO hctickets.applicationrole(
	id, rolename)
	VALUES (4, 'App. User');
		
		
INSERT INTO hctickets.status(
	id, code, value, statustype, itemorder, active)
	VALUES (1, 'Active', 'Active', 'Application', 1, TRUE);

INSERT INTO hctickets.status(
	id, code, value, statustype, itemorder, active)
	VALUES (2, 'Inactive', 'Inactive', 'Application', 2, TRUE);	
	
INSERT INTO hctickets.status(
	id, code, value, statustype, itemorder, active)
	VALUES (3, 'Incomplete', 'Incomplete', 'Task', 1, TRUE);	

INSERT INTO hctickets.status(
	id, code, value, statustype, itemorder, active)
	VALUES (4, 'Complete', 'Complete', 'Task', 2, TRUE);	

INSERT INTO hctickets.status(
	id, code, value, statustype, itemorder, active)
	VALUES (5, 'Canceled', 'Canceled', 'Task', 3, TRUE);	
	
INSERT INTO hctickets.status(
	id, code, value, statustype, itemorder, active)
	VALUES (6, 'Open', 'Open', 'Ticket', 1, TRUE);

INSERT INTO hctickets.status(
	id, code, value, statustype, itemorder, active)
	VALUES (7, 'Closed', 'Closed', 'Ticket', 2, TRUE);

INSERT INTO hctickets.status(
	id, code, value, statustype, itemorder, active)
	VALUES (8, 'Canceled', 'Canceled', 'Ticket', 3, TRUE);

INSERT INTO hctickets.status(
	id, code, value, statustype, itemorder, active)
	VALUES (9, 'Active', 'Active', 'User', 1, TRUE);

INSERT INTO hctickets.status(
	id, code, value, statustype, itemorder, active)
	VALUES (10, 'Inactive', 'Inactive', 'User', 2, TRUE);	

INSERT INTO hctickets.status(
	id, code, value, statustype, itemorder, active)
	VALUES (11, 'Active', 'Active', 'UserApplicationRole', 1, TRUE);

INSERT INTO hctickets.status(
	id, code, value, statustype, itemorder, active)
	VALUES (12, 'Inactive', 'Inactive', 'UserApplicationRole', 2, TRUE);		
