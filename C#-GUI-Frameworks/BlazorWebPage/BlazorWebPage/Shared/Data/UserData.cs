using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace BlazorWebPage.Shared.Data
{
    public class UserData
    {
        public static User[] users { get; set; }
        static UserData()
        {
            cargarDatos();
        }

        private static void cargarDatos()
        {
            users = new User[] {
                new User(1,"john_doe", "password123"),
                new User(2,"jane_smith", "abc123", "Jane Smith", "jane@example.com", new DateOnly(2024, 1, 1)),
                new User(3,"admin", "adminpass","Admin User","admin@example.com", new DateOnly(2024, 1, 1)),
                new User(4,"testuser", "test123","Test User","test@example.com", new DateOnly(2024, 2, 1)),
                new User(5,"newuser", "newpass"),
                new User(6,"user123", "userpass","User 123","user123@example.com",new DateOnly(2024, 9, 1)),
                new User(7,"admin2", "password","Admin 2","admin2@example.com",new DateOnly(2024, 3, 1)),
                new User(8,"guest", "welcome123"),
                new User(9,"developer", "devpass","Developer User","dev@example.com",new DateOnly(2024, 2, 1)),
                new User(10,"manager", "managerpass","Manager User","manager@example.com",new DateOnly(2024, 3, 1)),
                new User(11,"admin3", "pass123","Admin 3","admin3@example.com",new DateOnly(2024, 6, 1)),
                new User(12,"user456", "user456pass"),
                new User(13,"guest2", "guestpass","Guest User","guest@example.com",new DateOnly(2024, 8, 1)),
                new User(14,"testuser2", "testing123"),
                new User(15,"user789", "userpass789","User 789","user789@example.com",new DateOnly(2024, 6, 1)),
                new User(16,"developer2", "devpass2","Developer 2","dev2@example.com",new DateOnly(2024, 7, 1)),
                new User(17,"admin4", "adminpass123","Admin 4","admin4@example.com",new DateOnly(2024, 10, 1)),
                new User(18,"user10", "userpass10"),
                new User(19,"guest3", "guestpass3","Guest 3","guest3@example.com",new DateOnly(2024, 11, 1)),
                new User(20,"tester", "testpass","Tester User","tester@example.com",new DateOnly(2024, 12, 1))
            };

        }

    }
}
