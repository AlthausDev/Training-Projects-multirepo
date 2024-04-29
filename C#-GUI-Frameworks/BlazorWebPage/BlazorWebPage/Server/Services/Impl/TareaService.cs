using BlazorWebPage.Server.Repository.Interfaces;
using BlazorWebPage.Server.Services.Interfaces;
using BlazorWebPage.Shared;

namespace BlazorWebPage.Server.Services.Impl
{
    public class TareaService : ITareaService
    {
        private ITareaRepository tareaRepository;

        public TareaService(ITareaRepository tareaRepository)
        {
            this.tareaRepository = tareaRepository;
        }

        public IEnumerable<Tarea> GetAll()
        {
            return tareaRepository.GetAll();
        }

        public Tarea GetById(int id)
        {
            return tareaRepository.GetById(id);
        }

        public void Add(Tarea tarea)
        {
            tareaRepository.Add(tarea);
        }

        public void Update(Tarea tarea)
        {
            tareaRepository.Update(tarea);
        }

        public void Remove(int id)
        {
            tareaRepository.Remove(id);
        }

    }
}
