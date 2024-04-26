using BlazorWebPage.Server.Repository.Impl;
using BlazorWebPage.Server.Repository.Interfaces;
using BlazorWebPage.Server.Services.Interfaces;
using BlazorWebPage.Shared;
using BlazorWebPage.Shared.Data;
using System.Linq;

namespace BlazorWebPage.Server.Services.Impl
{
    public class UserService : IUserService
    {
        private IUserRepository userRepository;

        public UserService(IUserRepository userRepository)
        {
            this.userRepository = userRepository;
        }

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


        public User GetById(int id)
        {
            return userRepository.GetById(id);
        }

        public IEnumerable<User> GetAll()
        {
            return userRepository.GetAll();
        }

        public void Add(User user)
        {
            userRepository.Add(user);
        }

        public void Update(User user)
        {
            userRepository.Update(user);
        }

        public void Remove(User user)
        {
            userRepository.Remove(user);
        }
    }
}
