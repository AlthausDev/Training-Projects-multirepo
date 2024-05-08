DELETE FROM Usuarios;
DBCC CHECKIDENT ('Usuarios', RESEED, 0);
GO


INSERT INTO Usuarios (UserName, Password) VALUES ('john_doe', 'password123');
INSERT INTO Usuarios (UserName, Password, Nombre, Email, FechaRegistro) VALUES('jane_smith', 'abc123', 'Jane Smith', 'jane@example.com', '01-01-2024');
INSERT INTO Usuarios (UserName, Password, Nombre, Email, FechaRegistro) VALUES('admin', 'adminpass','Admin User','admin@example.com', '01-10-2024');
INSERT INTO Usuarios (UserName, Password, Nombre, Email, FechaRegistro) VALUES('testuser', 'test123','Test User','test@example.com', '01-02-2024');
INSERT INTO Usuarios (UserName, Password) VALUES('newuser', 'newpass');
INSERT INTO Usuarios (UserName, Password, Nombre, Email, FechaRegistro) VALUES('user123', 'userpass','User 123','user123@example.com','01-09-2024');
INSERT INTO Usuarios (UserName, Password, Nombre, Email, FechaRegistro) VALUES('admin2', 'password','Admin 2','admin2@example.com','01-03-2024');
INSERT INTO Usuarios (UserName, Password, Nombre, Email, FechaRegistro) VALUES('guest', 'welcome123','guest 2','guest@example.com','01-05-2024');
INSERT INTO Usuarios (UserName, Password, Nombre, Email, FechaRegistro) VALUES('developer', 'devpass','Developer User','dev@example.com','01-02-2024');
INSERT INTO Usuarios (UserName, Password, Nombre, Email, FechaRegistro) VALUES('manager', 'managerpass','Manager User','manager@example.com','01-03-2024');
INSERT INTO Usuarios (UserName, Password, Nombre, Email, FechaRegistro) VALUES('admin3', 'pass123','Admin 3','admin3@example.com','01-06-2024');
INSERT INTO Usuarios (UserName, Password) VALUES('user456', 'user456pass');
INSERT INTO Usuarios (UserName, Password, Nombre, Email, FechaRegistro) VALUES('guest2', 'guestpass','Guest User','guest@example.com','01-08-2024');
INSERT INTO Usuarios (UserName, Password, Nombre, Email, FechaRegistro) VALUES('testuser2', 'testing123','testing 123','testing123@example.com','01-12-2024');
INSERT INTO Usuarios (UserName, Password, Nombre, Email, FechaRegistro) VALUES('user789', 'userpass789','User 789','user789@example.com','01-06-2024');
INSERT INTO Usuarios (UserName, Password, Nombre, Email, FechaRegistro) VALUES('developer2', 'devpass2','Developer 2','dev2@example.com','01-07-2024');
INSERT INTO Usuarios (UserName, Password, Nombre, Email, FechaRegistro) VALUES('admin4', 'adminpass123','Admin 4','admin4@example.com','01-10-2024');
INSERT INTO Usuarios (UserName, Password, Nombre, Email, FechaRegistro) VALUES('user10', 'userpass10','user 10','user10@example.com','01-05-2024');
INSERT INTO Usuarios (UserName, Password, Nombre, Email, FechaRegistro) VALUES('guest3', 'guestpass3','Guest 3','guest3@example.com','01-11-2024');
INSERT INTO Usuarios (UserName, Password, Nombre, Email, FechaRegistro) VALUES('tester', 'testpass','Tester User','tester@example.com','01-11-2024');
