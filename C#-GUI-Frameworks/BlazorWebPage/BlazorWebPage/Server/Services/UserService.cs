using BlazorWebPage.Server.Interfaces;
using BlazorWebPage.Shared;
using BlazorWebPage.Shared.Data;
using System.Linq;

namespace BlazorWebPage.Server.Services
{
    public class UserService : IUserService
    {
        public User[] getAll()
        {
            return UserData.users;
        }

        public User GetUser(string username)
        {
            return (User)(from User user in UserData.users
                         where user.UserName == username
                         select user);
        }

        public void Post(User[] usuarios)
        {
            UserData.users = usuarios;    
        }

        public void Put(User[] usuarios)
        {
            UserData.users = usuarios;
        }

        public void Delete(User[] usuarios)
        {
            UserData.users = usuarios;
        }
    }
}
