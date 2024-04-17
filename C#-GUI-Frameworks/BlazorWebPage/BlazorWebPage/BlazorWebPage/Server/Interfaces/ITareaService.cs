using BlazorWebPage.Shared;

namespace BlazorWebPage.Server.Interfaces
{
    public interface ITareaService
    {
        Tarea[] getAll();
        void Post(Tarea[] tareas);
        void Delete(Tarea[] tareas);
        void Put(Tarea[] tareas);
    }
}
