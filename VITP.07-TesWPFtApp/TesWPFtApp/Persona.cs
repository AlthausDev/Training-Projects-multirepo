using System.ComponentModel;
using System.Data;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace TesWPFtApp
{
    //public enum Genero { Hombre, Mujer }
    public class Persona : INotifyPropertyChanged, IDataErrorInfo
    {
        //public Genero Genero { get; set; }
        public int Id { get; set; }

        private string name;
        private DateTime fechaNacimiento;              


        public string Name
        {
            get => name;
            set
            {
                if (name != value)
                {
                    name = value;
                    OnPropertyChanged(nameof(Name));
                }
            }
        }

        public DateTime FechaNacimiento
        {
            get => fechaNacimiento;
            set
            {
                if(fechaNacimiento != value)
                {
                    fechaNacimiento = value;
                    OnPropertyChanged(fechaNacimiento.ToString("dd/MM/yyyy"));
                }
            }
        }

        public Persona()
        {
        }

        public Persona(int id, string name)
        {
            Id = id;
            this.name = name;
            this.fechaNacimiento = DateTime.Now;
        }

        public Persona(int id, string nombre, DateTime fechaNacimiento)
        {
            Id = id;
            Name = nombre;
            this.fechaNacimiento = fechaNacimiento;
        }

        public string Error { 
            get { 
                return string.Empty; 
            } 
        }

        public string this[string columnName]
        {
            get
            {
                string result = string.Empty;
                if (columnName.Equals("Name") && string.IsNullOrEmpty(name))
                {
                    result = "Debe introducir un nombre";
                }
                if (columnName.Equals("FechaNacimiento") && fechaNacimiento >= new DateTime((DateTime.Now.Year-18), 1, 1))
                {
                    result = "La fecha de nacimiento debe ser anterior al 1/1/2010";
                }
                return result;
            }
        }

        public event PropertyChangedEventHandler? PropertyChanged;
        protected virtual void OnPropertyChanged(string propertyName)
        {
            PropertyChanged?.Invoke(this,
                new PropertyChangedEventArgs(propertyName));
        }
    }
}
