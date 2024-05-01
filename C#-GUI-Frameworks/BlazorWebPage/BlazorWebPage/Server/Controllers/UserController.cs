using BlazorWebPage.Server.Services.Interfaces;
using BlazorWebPage.Shared;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;

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
            Debug.WriteLine("Se ejecuta el primero");
            return (List<User>)UserService.GetAll();
        }

        [HttpGet ("{id}")]       
        public User GetUser(int Id)
        {
            Debug.WriteLine("Se ejecuta el Segundo");
            return UserService.GetById(Id);
        }

        [HttpPost]
        public void Post(User user)
        {
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
