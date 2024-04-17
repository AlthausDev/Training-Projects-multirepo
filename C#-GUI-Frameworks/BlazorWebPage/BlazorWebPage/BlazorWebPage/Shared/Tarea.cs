using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BlazorWebPage.Shared
{
    public class Tarea
    {
        public string Nombre { get; set; }
        public string Descripcion { get; set; }
        public bool Finalizado { get; set; }

        public Tarea()
        {
        }

        public Tarea(string nombre, string descripcion, bool finalizado)
        {
            Nombre = nombre;
            Descripcion = descripcion;
            Finalizado = finalizado;
        }
    }
}
