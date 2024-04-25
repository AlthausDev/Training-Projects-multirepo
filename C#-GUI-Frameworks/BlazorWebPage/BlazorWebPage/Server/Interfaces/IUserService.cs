using BlazorWebPage.Shared;

namespace BlazorWebPage.Server.Interfaces
{
    public interface IUserService
    {
        User[] getAll();
        User GetUser(string username);
        void Post(User[] tareas);
        void Delete(User[] tareas);
        void Put(User[] tareas);
    }
}
