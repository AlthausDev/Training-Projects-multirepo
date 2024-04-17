using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BlazorWebPage.Shared.Data
{
    public static class TareaData
    {
        public static Tarea[] tareas { get; set; }            

        static TareaData()
        {
            cargarDatos();
        }

        private static void cargarDatos()
        {
            Tarea tarea1 = new Tarea("Tarea 1", "Descripción de la tarea 1" , false);
            Tarea tarea2 = new Tarea("Tarea 2", "Descripción de la tarea 2" , true);
            Tarea tarea3 = new Tarea("Tarea 3", "Descripción de la tarea 3" , false);
            Tarea tarea4 = new Tarea("Tarea 4", "Descripción de la tarea 4" , false);
            Tarea tarea5 = new Tarea("Tarea 5", "Descripción de la tarea 5" , true);

            tareas = new Tarea[] { tarea1, tarea2, tarea3, tarea4, tarea5 };
        }
    }
}
