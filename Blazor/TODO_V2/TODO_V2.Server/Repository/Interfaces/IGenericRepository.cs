﻿using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Threading.Tasks;
using TODO_V2.Shared.Models;

namespace TODO_V2.Server.Repository.Interfaces
{
    public interface IGenericRepository<T, Y> where T : BaseModel
    {
        Task<bool> Add(T entity, Y? secondEntity);
        Task<T> Update(T entity, Y? secondEntity);
        Task<bool> Delete(int entityId);
        Task<bool> LogicDelete(int entityId);
        Task<IEnumerable<T>> GetAll(GetRequest<T> request);
        Task<IEnumerable<T>> GetAllLogic(GetRequest<T> request);
        Task<T> GetById(int entityId);
        Task<int> Count();
    }
}
