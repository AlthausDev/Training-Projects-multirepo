using BlazorWebPage.Server.Repository.Interfaces;
using BlazorWebPage.Shared;
using Dapper;
using Microsoft.Data.SqlClient;
using System.Data;

namespace BlazorWebPage.Server.Repository.Impl
{
    public class TareaRepository : ITareaRepository
    {


        public void Add(Tarea entity)
        {
            using (IDbConnection dbConnection = new SqlConnection(conexion))
            {
                string query = @"INSERT INTO Tarea VALUES @Tarea;";

                dbConnection.Execute(query);
            }
        }


        public IEnumerable<Tarea> GetAll()
        {
            using (IDbConnection dbConnection = new SqlConnection(conexion))
            {
                string query = @"SELECT * FROM Tarea;";

                return dbConnection.Query<Tarea>(query);
            }
        }

        public Tarea GetById(int id)
        {
            using (IDbConnection dbConnection = new SqlConnection(conexion))
            {
                string query = @"SELECT t
                                 FROM Tarea t
                                 WHERE t.Id == @id;";

                return (Tarea)dbConnection.Query<Tarea>(query);
            }
        }

        public void Remove(Tarea entity)
        {
            using (IDbConnection dbConnection = new SqlConnection(conexion))
            {
                string query = @"DELETE FROM Tarea t
                                 WHERE t.Id = @entity.Id;";

                dbConnection.Execute(query);
            }
        }     

        public void Update(Tarea entity)
        {
            using (IDbConnection dbConnection = new SqlConnection(conexion))
            {
                string query = @"UPDATE t FROM tarea t
                               WHERE t.Id == @entity.Id;";

               dbConnection.Execute(query);
            }
        }
    }
}
