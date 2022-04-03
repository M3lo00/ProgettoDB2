use dbproj;

INSERT INTO user (email, username, password) VALUES ('prova@prova.prova', 'prova', 'prova');
INSERT INTO user (email, username, password) VALUES ('giu@giu.giu', 'giu', 'giu');

INSERT INTO employee (username, email, password) VALUES ('giu1', 'giu1@giu1.giu1', 'giu1');
INSERT INTO employee (username, email, password) VALUES ('b', 'b@b.b', 'b');

INSERT INTO package (name, refEmployee, sms, minute, mGiga, extraMinute, extraMGiga, extraSMS, price12M) VALUES ('Mobile Advanced', 1, 200, 1000, 100, 0.01, 0.12, 0.1, 9.99);
INSERT INTO package (name, refEmployee, sms, minute, mGiga, extraMinute, extraMGiga, extraSMS, fixedPhone, fGiga, extraFGiga, price12M) VALUES ('All Inclusive', 1, 200, 1000, 100, 0.01, 0.12, 0.1, 1, 2000, 0.05, 24.98);
INSERT INTO package (name, refEmployee, fixedPhone, fGiga, extraFGiga, price12M) VALUES ('Home Basic', 2, 1, 500, 0.15, 15);

INSERT INTO optservice (name, monthly, refEmployee) VALUES ('Prova1', 4.99, 1);
INSERT INTO optservice (name, monthly, refEmployee) VALUES ('Prova2', 9.99, 2);
INSERT INTO optservice (name, monthly, refEmployee) VALUES ('Prova Bundle Pack 1', 9.99, 1);

INSERT INTO ownoptservice (refPack, refOptService) VALUES (2,3);

