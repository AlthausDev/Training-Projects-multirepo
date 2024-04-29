using BlazorWebPage.Server.Services.Interfaces;
using BlazorWebPage.Shared;
using Microsoft.AspNetCore.Mvc;

namespace BlazorWebPage.Server.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class UserController : ControllerBase
    {
        private readonly ILogger<UserController> _logger;
        private readonly IUserService UserService;

        public UserController(ILogger<UserController> logger, IUserService userService)
        {
            _logger = logger;
            UserService = userService;
        }

        [HttpGet]
        public List<User> Get()
        {
            return (List<User>)UserService.GetAll();
        }

        [HttpPost]
        public void Post(User user)
        {
            Console.WriteLine(user.ToString());

            UserService.Add(user);
        }

        [HttpPut]
        public void Put(User user)
        {
            UserService.Update(user);
        }

        [HttpDelete("/delete/{id}")]
        public void Delete(int id)
        {
            UserService.Remove(id);
        }
    }
}
