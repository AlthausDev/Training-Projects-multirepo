using BlazorWebPage.Server.Repository.Interfaces;
using BlazorWebPage.Shared;
using System.Threading.Tasks;

namespace BlazorWebPage.Server.Services.Interfaces
{
    public interface ITareaService
    {
        Tarea GetById(int id);
        IEnumerable<Tarea> GetAll();
        void Add(Tarea tarea);
        void Update(Tarea tarea);
        void Remove(Tarea tarea);
    }
}
