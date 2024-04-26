using BlazorWebPage.Server.Services.Interfaces;
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

        //[HttpGet]
        //public User[] GetAll()
        //{           
        //    return (User[]) UserService.GetAll();
        //}

        //[HttpPost]
        //public void Post(User user)
        //{           
        //    UserService.Add(user);
        //}

        //[HttpPut]
        //public void Put(User user)
        //{
        //    UserService.Update(user);
        //}

        //[HttpDelete]
        //public void Delete(User user)
        //{
        //    UserService.Remove(user);
        //}

        [HttpGet]
        public User[] GetAll()
        {
            return UserService.getAll();          

        }

        [HttpPost]
        public void Post(User[] user)
        {
            UserService.Post(user);

        }

        [HttpDelete]
        public void Delete(User[] user)
        {
            UserService.Delete(user);
        }

        [HttpPut]
        public void Put(User[] user)
        {
            UserService.Put(user);
        }
    }
}
