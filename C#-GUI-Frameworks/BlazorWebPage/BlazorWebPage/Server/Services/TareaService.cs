using BlazorWebPage.Server.Interfaces;
using BlazorWebPage.Shared;
using BlazorWebPage.Shared.Data;
using System.Diagnostics;

namespace BlazorWebPage.Server.Services
{
    public class TareaService : ITareaService
    {

        public Tarea[] getAll()
        {
            return TareaData.tareas;
        }

        public void Post(Tarea[] tareas)
        {
            TareaData.tareas = tareas;
        }

        public void Put(Tarea[] tareas)
        {
            TareaData.tareas = tareas; 
        }
        public void Delete(Tarea[] tareas)
        {
            TareaData.tareas = tareas; 
        }
    }
}
