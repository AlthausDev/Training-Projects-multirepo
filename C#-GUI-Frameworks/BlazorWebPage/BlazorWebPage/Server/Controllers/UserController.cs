using BlazorWebPage.Server.Services.Interfaces;
using BlazorWebPage.Shared;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.IdentityModel.Tokens;
using System.Diagnostics;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;

namespace BlazorWebPage.Server.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class UserController : ControllerBase
    {
        private readonly ILogger<UserController> Logger;
        private readonly IUserService UserService;
        private readonly IConfiguration Configuration;

        public UserController(ILogger<UserController> logger, IUserService userService, IConfiguration configuration)
        {
            Logger = logger;
            UserService = userService;
            Configuration = configuration;
        }

        [HttpGet]
        public ActionResult<List<User>> Get()
        {
            List<User> users = UserService.GetAll().ToList();
            if (users == null || !users.Any())
            {
                return NoContent();
            }
            return Ok(users);
        }

        [HttpGet("{id}")]
        [AllowAnonymous]
        public ActionResult<User> GetUser(int id)
        {
            User user = UserService.GetById(id);
            if (user == null)
            {
                return NotFound();
            }

            return Ok(user);
        }

        [AllowAnonymous]
        [HttpPost("login")]
        public IActionResult Login([FromBody] User user)
        {
            if (user == null || string.IsNullOrWhiteSpace(user.UserName) || string.IsNullOrWhiteSpace(user.Password))
            {
                return BadRequest("Invalid user data.");
            }

            User validUser = UserService.GetAll().FirstOrDefault(u => u.UserName == user.UserName && u.Password == user.Password);
            if (validUser == null)
            {
                return Unauthorized("Invalid username or password.");
            }

            string tokenString = BuildToken(validUser);
            return Ok(new { token = tokenString });
        }

        private string BuildToken(User user)
        {
            SymmetricSecurityKey securityKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(Configuration["JWT:Key"]));
            SigningCredentials credentials = new SigningCredentials(securityKey, SecurityAlgorithms.HmacSha256);

            List<Claim> claims = new List<Claim>
            {
                new Claim(ClaimTypes.NameIdentifier, user.Id.ToString()),
                new Claim(ClaimTypes.Name, user.UserName),
                new Claim(ClaimTypes.Role, "user")
            };

            JwtSecurityToken token = new JwtSecurityToken(
                issuer: Configuration["JWT:Issuer"],
                audience: Configuration["JWT:Issuer"],
                claims: claims,
                expires: DateTime.Now.AddMinutes(120),
                signingCredentials: credentials);

            return new JwtSecurityTokenHandler().WriteToken(token);
        }

        [HttpPost]
        public IActionResult Post([FromBody] User user)
        {
            if (user == null || string.IsNullOrEmpty(user.UserName) || string.IsNullOrEmpty(user.Password))
            {
                return BadRequest("User data is null or invalid.");
            }

            UserService.Add(user);
            return CreatedAtAction(nameof(GetUser), new { id = user.Id }, user);
        }

        [HttpPut]
        public IActionResult Put([FromBody] User user)
        {
            if (user == null || user.Id <= 0)
            {
                return BadRequest("User data is invalid.");
            }

            User existingUser = UserService.GetById(user.Id);
            if (existingUser == null)
            {
                return NotFound();
            }

            UserService.Update(user);
            return NoContent();
        }

        [HttpDelete("{id}")]
        public IActionResult Delete(int id)
        {
            User user = UserService.GetById(id);
            if (user == null)
            {
                return NotFound();
            }

            UserService.Remove(id);
            return NoContent();
        }
    }
}
