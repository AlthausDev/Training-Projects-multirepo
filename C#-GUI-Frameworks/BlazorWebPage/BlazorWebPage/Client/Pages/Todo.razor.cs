using BlazorWebPage.Shared;
using Syncfusion.Blazor.Buttons;
using Syncfusion.Blazor.Inputs;
using Syncfusion.Blazor.Popups;
using System.Diagnostics;
using System.Net.Http.Json;

namespace BlazorWebPage.Client.Pages
{
    public partial class Todo
    {   

        private List<Tarea> tareas = new List<Tarea>();
        private Tarea nuevaTarea = new Tarea();

        private bool isVisible { get; set; } = false;
        protected bool IsDisabled { get; set; } = true;


        protected override async Task OnInitializedAsync()
        {
            await getData();
        }


        private async Task getData()
        {
            Tarea[]? tareasArray = await Http.GetFromJsonAsync<Tarea[]>("./tarea");

            if (tareasArray is not null)
            {
                tareas = tareasArray.ToList();
            }
        }

        private async void Post()
        {
            tareas.Add(nuevaTarea);
            await Http.PostAsJsonAsync<Tarea[]>("Tarea", tareas.ToArray());
            await getData();
        }

        private void addTarea()
        {
            Post();
            closePopup();
        }


        private void mostrarPopup()
        {
            isVisible = true;
        }

        private void closePopup()
        {
            isVisible = false;
            nuevaTarea = new Tarea();
        }

        private void ValueChangeHandler(ChangedEventArgs args)
        {
            IsDisabled = (String.IsNullOrWhiteSpace(nuevaTarea.Nombre) || String.IsNullOrWhiteSpace(nuevaTarea.Descripcion));
        }

        public enum Finalizado
        {
            No,
            Si
        }
    }

}
