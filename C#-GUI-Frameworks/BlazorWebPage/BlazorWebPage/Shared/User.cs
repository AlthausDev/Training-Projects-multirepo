using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Globalization;


namespace BlazorWebPage.Shared
{
    public class User
    {
        public long Id { get; set; }
        public string UserName { get; set; } = string.Empty;
        public string Password { get; set; } = string.Empty;
        public string? Nombre { get; set; } = string.Empty;
        public string? Email { get; set; } = string.Empty;
        //public DateOnly FechaRegistro { get; private set; }

        //public DateOnly FechaRegistro
        //{
        //    get => FechaRegistro;
        //    set => DateOnly.FromDateTime(DateTime.Now);
        //}
        private const string DateTimeOffsetFormatString = "yyyy-MM-ddTHH:mm:sszzz";
        private DateTimeOffset eventTimeField;

        [System.Xml.Serialization.XmlElementAttribute(Form = System.Xml.Schema.XmlSchemaForm.Unqualified, Order = 0)]
        public string eventTime
        {
            get { return eventTimeField.ToString(DateTimeOffsetFormatString); }
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

        public User(long id, string userName, string password, string? nombre, string? email, DateOnly fechaRegistro) : this(id, userName, password)
        {
            Nombre = nombre;
            Email = email;
            //FechaRegistro = fechaRegistro;
        }
    }
}