﻿using Dapper;
using Microsoft.Data.SqlClient;
using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Data;
using System.Threading.Tasks;
using TODO_V2.Server.Repository.Interfaces;
using TODO_V2.Shared.Models;

namespace TODO_V2.Server.Repository.Impl
{
    public class TaskRepository : ITaskRepository
    {
        private readonly IConfiguration _configuration;
        private string ConnectionString => _configuration.GetConnectionString("TODO_V2DB");

        public TaskRepository(IConfiguration configuration)
        {
            _configuration = configuration;
        }

        public async Task<bool> Add(TaskItem taskItem, object? secondEntity)
        {
            try
            {
                using (IDbConnection dbConnection = new SqlConnection(ConnectionString))
                {
                    await dbConnection.ExecuteAsync(@"
                        INSERT INTO Tasks (CategoryId, UserId, Name, State, ExpirationDate) 
                        VALUES (@CategoryId, @UserId, @Name, @State, @ExpirationDate)", taskItem);
                }
                return true;
            }
            catch (Exception ex)
            {                
                Console.WriteLine($"Error al agregar tarea: {ex.Message}");
                return false;
            }
        }

        public async Task<int> Count()
        {
            using (IDbConnection dbConnection = new SqlConnection(ConnectionString))
            {
                return await dbConnection.ExecuteScalarAsync<int>("SELECT COUNT(*) FROM Tasks");
            }
        }

        public async Task<bool> Delete(int entityId)
        {
            try
            {
                using (IDbConnection dbConnection = new SqlConnection(ConnectionString))
                {
                    await dbConnection.ExecuteAsync("DELETE FROM Tasks WHERE Id = @Id", new { Id = entityId });
                }
                return true;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error al eliminar tarea: {ex.Message}");
                return false;
            }
        }

        public async Task<IEnumerable<TaskItem>> GetAll(GetRequest<TaskItem> request)
        {
            using (IDbConnection dbConnection = new SqlConnection(ConnectionString))
            {
                return await dbConnection.QueryAsync<TaskItem>("SELECT * FROM Tasks");
            }
        }

        public async Task<IEnumerable<TaskItem>> GetAllLogic(GetRequest<TaskItem> request)
        {
            using (IDbConnection dbConnection = new SqlConnection(ConnectionString))
            {
                return await dbConnection.QueryAsync<TaskItem>("SELECT * FROM Tasks WHERE IsDeleted = 0");
            }
        }

        public async Task<TaskItem> GetById(int entityId)
        {
            using (IDbConnection dbConnection = new SqlConnection(ConnectionString))
            {
                return await dbConnection.QueryFirstOrDefaultAsync<TaskItem>("SELECT * FROM Tasks WHERE Id = @Id", new { Id = entityId });
            }
        }

        public async Task<bool> LogicDelete(int entityId)
        {
            try
            {
                using (IDbConnection dbConnection = new SqlConnection(ConnectionString))
                {
                    await dbConnection.ExecuteAsync("UPDATE Tasks SET IsDeleted = 1 WHERE Id = @Id", new { Id = entityId });
                }
                return true;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error al eliminar lógicamente tarea: {ex.Message}");
                return false;
            }
        }

        public async Task<TaskItem> Update(TaskItem taskItem, object? secondEntity)
        {
            try
            {
                using (IDbConnection dbConnection = new SqlConnection(ConnectionString))
                {
                    await dbConnection.ExecuteAsync(@"
                        UPDATE Tasks 
                        SET CategoryId = @CategoryId, UserId = @UserId, Name = @Name, 
                            State = @State, ExpirationDate = @ExpirationDate, UpdatedAt = GETDATE(), UpdatedBy = @UpdatedBy
                        WHERE Id = @Id", new
                    {
                        taskItem.CategoryId,
                        taskItem.UserId,
                        taskItem.Name,
                        taskItem.State,
                        taskItem.ExpirationDate,
                        taskItem.UpdatedBy,
                        taskItem.Id
                    });
                }
                return taskItem;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error al actualizar tarea: {ex.Message}");
                return null;
            }
        }

        public async Task<IEnumerable<TaskItem>> GetTasksByUserId(int userId)
        {
            using (IDbConnection dbConnection = new SqlConnection(ConnectionString))
            {
                return await dbConnection.QueryAsync<TaskItem>("SELECT * FROM Tasks WHERE UserId = @UserId", new { UserId = userId });
            }
        }
    }
}
