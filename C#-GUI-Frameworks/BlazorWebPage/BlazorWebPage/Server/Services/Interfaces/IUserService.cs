using BlazorWebPage.Shared;

namespace BlazorWebPage.Server.Services.Interfaces
{
    public interface IUserService
    {
        User[] getAll();
        User GetUser(string username);
        void Post(User[] tareas);
        void Delete(User[] tareas);
        void Put(User[] tareas);


        User GetById(int id);
        IEnumerable<User> GetAll();
        void Add(User tarea);
        void Update(User tarea);
        void Remove(User tarea);
    }
}
