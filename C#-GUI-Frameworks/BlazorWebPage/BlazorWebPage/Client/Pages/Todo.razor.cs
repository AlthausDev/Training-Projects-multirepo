using BlazorBootstrap;
using BlazorWebPage.Shared;
using Microsoft.JSInterop;
using Syncfusion.Blazor.Buttons;
using Syncfusion.Blazor.Inputs;
using Syncfusion.Blazor.Popups;
using System.Diagnostics;
using System.Net.Http.Json;
using System.Reflection;



namespace BlazorWebPage.Client.Pages
{
    public partial class Todo
    {
        private List<Tarea> tareas { get; set; } = new List<Tarea>();
        private Tarea nuevaTarea = new Tarea();

        private Tarea? selectedTarea { get; set; } = null;
        public Tarea? SelectedTarea
        {
            get
            {
                return selectedTarea;
            }
            set
            {
                if (selectedTarea != value)
                {
                    selectedTarea = value;
                    SelectedChangeHandler();
                }
            }
        }

        protected bool IsDisabled { get; set; } = false;
        protected bool IsDisabledEdit { get; set; } = true;

        private Enum? accion;

        private Modal modal = default!;
               
        protected override async Task OnInitializedAsync()
        {          
            await getData();
        }

        private async Task<AutoCompleteDataProviderResult<Tarea>> TareasDataProvider(AutoCompleteDataProviderRequest<Tarea> request)
        {           

            return await Task.FromResult(request.ApplyTo(tareas.OrderBy(tarea => tarea.Nombre)));
        }

        private async Task OnAutoCompleteChanged(Tarea tarea)
        {
            SelectedTarea = tarea;            
            //await JS.InvokeVoidAsync($"searchFuction('{SelectedTarea.Nombre}')");
            Console.WriteLine($"'{tarea?.Nombre}' selected.");
        }

        private async Task getData()
        {
            Tarea[]? tareasArray = await Http.GetFromJsonAsync<Tarea[]>("tarea");

            if (tareasArray is not null)
            {
                tareas = tareasArray.ToList();
            }
        }

        private async Task Post()
        {
            tareas.Add(nuevaTarea);
            await Http.PostAsJsonAsync<Tarea[]>("Tarea", tareas.ToArray());
            await getData();
        }

        private async Task Put()
        {
            tareas.Insert(tareas.IndexOf(selectedTarea), nuevaTarea);
            await Delete();
        }

        private async Task Delete()
        {
            if (selectedTarea != null)
            {
                tareas.Remove(selectedTarea);
                await Http.PostAsJsonAsync<Tarea[]>("Tarea", tareas.ToArray());
                await getData();
                SelectedTarea = null;
            }
        }

        private void selectTarea(Tarea tarea)
        {
            SelectedTarea = tarea;
            Console.WriteLine(tarea.Nombre);
        }

        private async Task execTarea()
        {
            if (accion.Equals(Accion.Crear))
            {
                await Post();
            }
            else
            {
                await Put();
            }
            await HideModal();
        }      


        private async Task OnClickShowModal(Enum accion)
        {
            this.accion = accion;

            if (accion.Equals(Accion.Editar)) { 
                nuevaTarea = selectedTarea;
            }
            await modal.ShowAsync();
        }

        private async Task HideModal()
        {
            SelectedTarea = null;
            nuevaTarea = new Tarea();
            await modal.HideAsync();            
        }

        private string GetRowClass(Tarea tarea)
        {
            return tarea == SelectedTarea ? "selected-row" : "";
        }
        private void SelectedChangeHandler()
        {
            IsDisabledEdit = selectedTarea == null;
        }

        private void ValueChangeHandler(ChangedEventArgs args)
        {
            IsDisabled = (String.IsNullOrWhiteSpace(nuevaTarea.Nombre) || String.IsNullOrWhiteSpace(nuevaTarea.Descripcion));
        }

        public enum Accion
        {
            Crear,
            Editar
        }

        public enum Finalizado
        {
            No,
            Si
        }
    }

}
