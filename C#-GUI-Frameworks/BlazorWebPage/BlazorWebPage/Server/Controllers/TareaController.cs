using BlazorWebPage.Server.Interfaces;
using BlazorWebPage.Shared;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;

namespace BlazorWebPage.Server.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class TareaController : ControllerBase
    {
        private readonly ILogger<TareaController> _logger;
        private readonly ITareaService TareaService;

        public TareaController(ILogger<TareaController> logger, ITareaService tareaService)
        {
            _logger = logger;
            TareaService = tareaService;
        }

        [HttpGet]
        public Tarea[] Get()
        {     
            return TareaService.getAll();
        }

        [HttpPost]
        public void Post(Tarea[] tarea)
        {
            TareaService.Post(tarea);
        }
    }
}
