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
        private readonly ILogger<UserController> _logger;
        private readonly IUserService UserService;

        private readonly IConfiguration configuration;

        public UserController(ILogger<UserController> logger, IUserService userService, IConfiguration configuration)
        {
            _logger = logger;
            UserService = userService;

            this.configuration = configuration;

        }              

        [HttpGet]
        public List<User> Get()
        {
            return (List<User>)UserService.GetAll();
        }


        [HttpGet("{id}")]
        [AllowAnonymous]        
        public User GetUser(int Id)
        {
            User user = UserService.GetById(Id);
            Console.WriteLine(Login(user));

            return user;
        }

        [AllowAnonymous]
        [HttpPost]
        public IActionResult Login([FromBody] User user)
        {
            IActionResult response = Unauthorized();

            var tokenString = BuildToken(user);
            response = Ok(new { token = tokenString });

            Debug.WriteLine(response.ToString());
            return response;
        }

        public string BuildToken(User user)
        {
            //byte[] TokenKey = Encoding.UTF8.GetBytes(configuration["Key"]);

            var securityKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(configuration["JWT:Key"]));
            var credentials = new SigningCredentials(securityKey, SecurityAlgorithms.HmacSha256);

            var principal = new ClaimsPrincipal(new ClaimsIdentity(null, "Basic"));
            var isAuthenticated = principal.Identity.IsAuthenticated;

            Claim[] Claims =
            [
                new(ClaimTypes.NameIdentifier, user.Id.ToString()),
                new(ClaimTypes.Name, user.UserName),
                new Claim(ClaimTypes.Role, "user")
            ];

            var token = new JwtSecurityToken(configuration["JWT:Issuer"],
                configuration["JWT:Issuer"],
                Claims,
                expires: DateTime.Now.AddMinutes(120),
                signingCredentials: credentials);



            return new JwtSecurityTokenHandler().WriteToken(token);

            //SecurityTokenDescriptor TokenDescriptor = new()
            //{
            //    Expires = DateTime.UtcNow.AddHours(configuration.GetValue<int>("ExpirationHours")),
            //    SigningCredentials = new SigningCredentials(new SymmetricSecurityKey(TokenKey),
            //                                            SecurityAlgorithms.HmacSha256Signature),
            //    Subject = new ClaimsIdentity(Claims),
            //};

            //JwtSecurityTokenHandler tokenHandler = new();
            //SecurityToken Token = tokenHandler.CreateToken(TokenDescriptor);

            //return tokenHandler.WriteToken(Token);
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
