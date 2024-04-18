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

        private bool isVisible { get; set; } = false;
        private bool isVisibleEdit { get; set; } = false;

        protected bool IsDisabled { get; set; } = true;
        protected bool IsDisabledEdit { get; set; } = true;

        private Enum? accion;

        protected override async Task OnInitializedAsync()
        {          
            await getData();
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

        private async Task addTarea()
        {
            await Post();
            closePopup();
        }

        private async Task editTarea()
        {
            await Put();
            closePopup();
        }

        private void selectTarea(Tarea tarea)
        {
            SelectedTarea = tarea;
            Console.WriteLine(tarea.Nombre);
        }

        private void mostrarPopup()
        {
            isVisible = true;
        }

        //private async Task mostrarPopup(Enum accion)
        //{
        //    this.accion = accion;
        //    isVisible = true;
        //    await ProcesarBtn();
        //    closePopup();
        //}

        //private async Task ProcesarBtn()
        //{
        //    if (accion.Equals(Accion.Crear))
        //    {
        //        await addTarea();
        //    }
        //    else
        //    {
        //        nuevaTarea = selectedTarea;
        //        await editTarea();
        //    }

        //    await closePopup();
        //}

        private void mostrarPopupEdit()
        {
            nuevaTarea = selectedTarea;
            isVisibleEdit = true;
        }

        private void closePopup()
        {
            isVisible = false;
            isVisibleEdit = false;
            SelectedTarea = null;
            nuevaTarea = new Tarea();
        }

        //private void closePopup()
        //{
        //    isVisible = false;
        //    isVisibleEdit = false;
        //    SelectedTarea = null;
        //    nuevaTarea = new Tarea();
        //}

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
