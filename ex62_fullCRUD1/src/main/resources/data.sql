INSERT INTO appt(firstName, email, apptDate, apptTime) VALUES
('Frank', 'frank@frank.com', '2020-01-01', '12:00:00'),
('Hao', 'hao@gmail.com', '2020-02-02', '08:15:00'),
('Sue', 'sue@yahoo.ca', '2020-03-03', '14:30:00'),
('Carl', 'carl@yahoo.ca', '2022-12-03', '14:30:00'),
('Sarah', 'sarah@yahoo.ca', '2023-03-03', '14:30:00'),
('Jaspreet', 'jaspreet@outlook.com', '2020-04-04', '10:15:00');
DELETE FROM appt WHERE firstName = 'Frank';
