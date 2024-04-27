using BlazorWebPage.Server.Repository.Interfaces;
using BlazorWebPage.Shared;
using Dapper;
using Microsoft.Data.SqlClient;
using Microsoft.Extensions.Configuration;
using System.Data;

namespace BlazorWebPage.Server.Repository.Impl
{
    public class TareaRepository : ITareaRepository
    {
        private readonly IConfiguration _configuration;
        private string ConnectionString => _configuration.GetConnectionString("BlazorWebPageDB");

        public TareaRepository(IConfiguration configuration)
        {
            _configuration = configuration;
        }

        public void Add(Tarea entity)
        {
            using (IDbConnection dbConnection = new SqlConnection(ConnectionString))
            {
                string query = @"INSERT INTO Tarea (Nombre, Descripcion, Finalizado) VALUES (@Nombre, @Descripcion, @Finalizado);";
                dbConnection.Execute(query, entity);
            }
        }

        public IEnumerable<Tarea> GetAll()
        {
            using (IDbConnection dbConnection = new SqlConnection(ConnectionString))
            {
                string query = @"SELECT * FROM Tarea;";
                return dbConnection.Query<Tarea>(query);
            }
        }

        public Tarea GetById(int id)
        {
            using (IDbConnection dbConnection = new SqlConnection(ConnectionString))
            {
                string query = @"SELECT * FROM Tarea WHERE Id = @id;";
                return dbConnection.QueryFirstOrDefault<Tarea>(query, new { id });
            }
        }

        public void Remove(Tarea entity)
        {
            using (IDbConnection dbConnection = new SqlConnection(ConnectionString))
            {
                string query = @"DELETE FROM Tarea WHERE Id = @Id;";
                dbConnection.Execute(query, new { entity.Id });
            }
        }

        public void Update(Tarea entity)
        {
            using (IDbConnection dbConnection = new SqlConnection(ConnectionString))
            {
                string query = @"UPDATE Tarea SET Nombre = @Nombre, Descripcion = @Descripcion, Finalizado = @Finalizado WHERE Id = @Id;";
                dbConnection.Execute(query, entity);
            }
        }
    }
}
