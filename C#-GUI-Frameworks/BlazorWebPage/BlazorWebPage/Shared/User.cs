using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Globalization;
using System.ComponentModel.DataAnnotations;


namespace BlazorWebPage.Shared
{
    public class User
    {
        public long Id { get; set; }

        [Required]
        public string UserName { get; set; } = string.Empty;

        [Required]
        public string Password { get; set; } = string.Empty;
        public string? Nombre { get; set; } = string.Empty;
        public string? Email { get; set; } = string.Empty;       

        private const string DateTimeFormatString = "dd-MM-yyyy";
        private DateTimeOffset eventTimeField = DateTimeOffset.Now;

        public string FechaRegistro
        {
            get { return eventTimeField.ToString(DateTimeFormatString); }
            set { eventTimeField = DateTimeOffset.Parse(value); }
        } 


        public User()
        {          
        }

        public User(long id, string userName, string password)
        {
            Id = id;
            UserName = userName;
            Password = password;           
        }

        public User(long id, string userName, string password, string? nombre, string? email) : this(id, userName, password)
        {
            Nombre = nombre;
            Email = email;
        }


        public User(long id, string userName, string password, string? nombre, string? email, string fechaRegistro) : this(id, userName, password)
        {
            Nombre = nombre;
            Email = email;
            FechaRegistro = fechaRegistro;
        }

        public override string ToString()
        {
            Console.WriteLine($"Id: {Id}" +
                $"\nUsername: {UserName}" +
                $"\nContraseña: {Password}" +
                $"\nNombre: {Nombre}" +
                $"\nEmail: {Email}" +
                $"\nFecha de Registro: {FechaRegistro}");  

            return "";
            
        }
    }
}