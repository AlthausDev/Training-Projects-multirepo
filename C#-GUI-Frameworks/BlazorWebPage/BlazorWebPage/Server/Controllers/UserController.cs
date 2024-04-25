using BlazorWebPage.Server.Interfaces;
using BlazorWebPage.Shared;
using Microsoft.AspNetCore.Mvc;

namespace BlazorWebPage.Server.Controllers
{
    [ApiController]
    [Route("[Controller]")]
    public class UserController : ControllerBase
    {
        private readonly ILogger<UserController> Logger;
        private readonly IUserService UserService;

        public UserController(ILogger<UserController> logger, IUserService userService)
        {
            Logger = logger;
            UserService = userService;
        }

        [HttpGet]
        public User[] GetAll()
        {
            return UserService.getAll();
        }

        //[HttpGet]
        //public User Get(string username)
        //{
        //    return UserService.GetUser(username);
        //}

        [HttpPost]
        public void Post(User[] user)
        {
            UserService.Post(user);
        }

        [HttpPut]
        public void Put(User[] user)
        {
            UserService.Put(user);
        }

        [HttpDelete]
        public void Delete(User[] user)
        {
            UserService.Delete(user);
        }
    }
}
