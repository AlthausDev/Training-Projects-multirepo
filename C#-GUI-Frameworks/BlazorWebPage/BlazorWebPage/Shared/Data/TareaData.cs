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
            tareas = new Tarea[] {
                new Tarea("Tarea 1", "Descripción de la tarea 1" , false),
                new Tarea("Tarea 2", "Descripción de la tarea 2", true),
                new Tarea("Tarea 3", "Descripción de la tarea 3", false),
                new Tarea("Tarea 4", "Descripción de la tarea 4", false),
                new Tarea("Tarea 5", "Descripción de la tarea 5", true),
                new Tarea("Tarea 6", "Descripción de la tarea 6", false),
                new Tarea("Tarea 7", "Descripción de la tarea 7", true),
                new Tarea("Tarea 8", "Descripción de la tarea 8", true),
                new Tarea("Tarea 9", "Descripción de la tarea 9", false),
                new Tarea("Tarea 10", "Descripción de la tarea 10", false),
                new Tarea("Tarea 11", "Descripción de la tarea 11" , false),
                new Tarea("Tarea 12", "Descripción de la tarea 12", true),
                new Tarea("Tarea 13", "Descripción de la tarea 13", false),
                new Tarea("Tarea 14", "Descripción de la tarea 14", false),
                new Tarea("Tarea 15", "Descripción de la tarea 15", true),
                new Tarea("Tarea 16", "Descripción de la tarea 16", false),
                new Tarea("Tarea 17", "Descripción de la tarea 17", true),
                new Tarea("Tarea 18", "Descripción de la tarea 18", true),
                new Tarea("Tarea 19", "Descripción de la tarea 19", false),
                new Tarea("Tarea 20", "Descripción de la tarea 20", false)
        };

        }
    }
}
