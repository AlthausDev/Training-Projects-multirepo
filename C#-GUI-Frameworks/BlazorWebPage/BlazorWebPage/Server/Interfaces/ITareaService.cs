using BlazorWebPage.Shared;

namespace BlazorWebPage.Server.Interfaces
{
    public interface ITareaService
    {
        Tarea[] getAll();
        void Post(Tarea[] tareas);
    }
}
