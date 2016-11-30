INSERT INTO Groups (name) VALUES ('Transport');
INSERT INTO Groups (name) VALUES ('House');
INSERT INTO Groups (name) VALUES ('Food');

INSERT INTO Products (name, groupId) VALUES ('Bread', 3);
INSERT INTO Products (name) VALUES ('Lamp');

INSERT INTO Descriptions (product_id, descr) VALUES (1, 'White');
INSERT INTO Descriptions (product_id, descr) VALUES (2, 'Osram');

INSERT INTO Movements (product_id, group_id, summ) VALUES (1, 3, 8.70);
INSERT INTO Movements (product_id, group_id, summ) VALUES (2, 2, 12.45)