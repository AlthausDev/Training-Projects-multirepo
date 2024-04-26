using BlazorWebPage.Server.Repository.Interfaces;
using BlazorWebPage.Shared;
using Dapper;
using Microsoft.Data.SqlClient;
using System.Data;

namespace BlazorWebPage.Server.Repository.Impl
{
    public class UserRepository : IUserRepository
    {



        public void Add(User entity)
        {
            using (IDbConnection dbConnection = new SqlConnection(conexion))
            {
                string query = @"INSERT INTO User VALUES @User;";

                dbConnection.Execute(query);
            }
        }


        public IEnumerable<User> GetAll()
        {
            using (IDbConnection dbConnection = new SqlConnection(conexion))
            {
                string query = @"SELECT * FROM User;";

                return dbConnection.Query<User>(query);
            }
        }

        public User GetById(int id)
        {
            using (IDbConnection dbConnection = new SqlConnection(conexion))
            {
                string query = @"SELECT t
                                 FROM User t
                                 WHERE t.Id == @id;";

                return (User)dbConnection.Query<User>(query);
            }
        }

        public void Remove(User entity)
        {
            using (IDbConnection dbConnection = new SqlConnection(conexion))
            {
                string query = @"DELETE FROM User t
                                 WHERE t.Id = @entity.Id;";

                dbConnection.Execute(query);
            }
        }

        public void Update(User entity)
        {
            using (IDbConnection dbConnection = new SqlConnection(conexion))
            {
                string query = @"UPDATE t FROM user t
                               WHERE t.Id == @entity.Id;";

                dbConnection.Execute(query);
            }
        }
    }
}
