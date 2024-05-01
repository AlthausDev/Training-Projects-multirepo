using BlazorWebPage.Server.Repository.Interfaces;
using BlazorWebPage.Server.Services.Interfaces;
using BlazorWebPage.Shared;
using static Microsoft.EntityFrameworkCore.DbLoggerCategory;
using System.Data.Common;

namespace BlazorWebPage.Server.Services.Impl
{
    public class UserService : IUserService
    {
        private IUserRepository userRepository;
   
        public UserService(IUserRepository userRepository)
        {
            this.userRepository = userRepository;
        }

        public IEnumerable<User> GetAll()
        {
            return userRepository.GetAll();
        }

        public User GetById(int id)
        {
            return userRepository.GetById(id);
        }

        public void Add(User user)
        {
            userRepository.Add(user);
        }

        public void Update(User user)
        {
            userRepository.Update(user);
        }

        public void Remove(int id)
        {
            userRepository.Remove(id);
        }
    }
}
