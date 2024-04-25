//using BlazorBootstrap;
//using BlazorWebPage.Shared;
//using Microsoft.JSInterop;
//using NUnit.Framework;
//using System.Diagnostics;
//using System.Net.Http.Json;
//using System.Reflection;
//using ToastType = BlazorBootstrap.ToastType;



//namespace BlazorWebPage.Client.Pages
//{
//    public partial class Todo
//    {
//        private List<Tarea> tareas { get; set; } = new List<Tarea>();

//        private Tarea nuevaTarea { get; set; } = new Tarea();
//        public Tarea NuevaTarea  {   
            
//           get
//            {
//                return nuevaTarea;
//            }
//            set
//            {
//                if (nuevaTarea != value)
//                {
//                    nuevaTarea = value;
//                    ValueChangeHandler();
//                }
//            }
//        }

//        private Tarea? selectedTarea { get; set; } = null;
//        public Tarea? SelectedTarea
//        {
//            get
//            {
//                return selectedTarea;
//            }
//            set
//            {
//                if (selectedTarea != value)
//                {
//                    selectedTarea = value;
//                    SelectedChangeHandler();
//                }
//            }
//        }

//        protected bool IsDisabled { get; set; } = true;
//        protected bool IsDisabledEdit { get; set; } = true;

//        private Enum accion = Accion.Espera;
//        private Modal modal = default!;

//        private PieChart pieChart = default!;
//        private PieChartOptions pieChartOptions = default!;
//        private ChartData chartData = default!;
//        private string[]? backgroundColors;

//        private int datasetsCount = 0;
//        private int dataLabelsCount = 0;

//        private Random random = new();

//        List<ToastMessage> messages = new List<ToastMessage>();
//        private void ShowMessage(ToastType toastType, string message) => messages.Add(CreateToastMessage(toastType, message));


//        protected override async Task OnInitializedAsync()
//        {          
//            await getData();


//            backgroundColors = ColorBuilder.CategoricalTwelveColors;

//            chartData = new ChartData { Labels = GetDefaultDataLabels(4), Datasets = GetDefaultDataSets(1) };

//            pieChartOptions = new()
//            {
//                Responsive = true
//            };
//            pieChartOptions.Plugins.Title!.Text = "Tareas";
//            pieChartOptions.Plugins.Title.Display = true;
//        }

//        private async Task<AutoCompleteDataProviderResult<Tarea>> TareasDataProvider(AutoCompleteDataProviderRequest<Tarea> request)
//        {           

//            return await Task.FromResult(request.ApplyTo(tareas.OrderBy(tarea => tarea.Nombre)));
//        }

       

//        private ToastMessage CreateToastMessage(ToastType toastType, string message)
//        {
//            var toastMessage = new ToastMessage();
//            toastMessage.Type = toastType;            
//            toastMessage.Message = message;

//            return toastMessage;
//        }

//        #region Api

//        private async Task getData()
//        {
//            Tarea[]? tareasArray = await Http.GetFromJsonAsync<Tarea[]>("tarea");

//            if (tareasArray is not null)
//            {
//                tareas = tareasArray.ToList();
//            }
//        }

//        private async Task Post()
//        {
//            tareas.Add(NuevaTarea);
//            await Http.PostAsJsonAsync<Tarea[]>("Tarea", tareas.ToArray());
//            await getData();            
//        }

//        private async Task Put()
//        {
//            tareas.Insert(tareas.IndexOf(selectedTarea), NuevaTarea);
//            await Delete();           
//        }

//        private async Task Delete()
//        {
//            if (selectedTarea != null)
//            {
//                tareas.Remove(selectedTarea);
//                await Http.PostAsJsonAsync<Tarea[]>("Tarea", tareas.ToArray());
//                await getData();
//                SelectedTarea = null;

//                if (accion.Equals(Accion.Espera))
//                {
//                    ShowMessage(ToastType.Danger, "Registro eliminado con éxito");
//                }
//            }
//        }

//        private void selectTarea(Tarea tarea)
//        {
//            SelectedTarea = tarea;
//            Console.WriteLine(tarea.Nombre);
//        }

//        private async Task execTarea()
//        {
//            if (accion.Equals(Accion.Crear))
//            {
//                await Post();
//                ShowMessage(ToastType.Success, "Registro agregado con éxito");
//            }
//            else
//            {
//                await Put();
//                ShowMessage(ToastType.Warning, "Registro editado con éxito");
//            }
//            accion = Accion.Espera;
//            await HideModal();
//        }

//        #endregion Api

//        #region Modal

//        private async Task OnClickShowModal(Enum accion)
//        {
//            this.accion = accion;

//            if (accion.Equals(Accion.Editar)) {
//                nuevaTarea = selectedTarea;
//            }
//            await modal.ShowAsync();
//        }

//        private async Task HideModal()
//        {
//            SelectedTarea = null;
//            nuevaTarea = new Tarea();
//            await modal.HideAsync();            
//        }

//        private string GetRowClass(Tarea tarea)
//        {
//            return tarea == SelectedTarea ? "selected-row" : "";
//        }

//        #endregion Modal


//        protected override async Task OnAfterRenderAsync(bool firstRender)
//        {
//            if (firstRender)
//            {
//                await pieChart.InitializeAsync(chartData, pieChartOptions);
//            }
//            await base.OnAfterRenderAsync(firstRender);
//        }

//        private async Task RandomizeAsync()
//        {
//            if (chartData is null || chartData.Datasets is null || !chartData.Datasets.Any()) return;

//            var newDatasets = new List<IChartDataset>();

//            foreach (var dataset in chartData.Datasets)
//            {
//                if (dataset is PieChartDataset pieChartDataset
//                    && pieChartDataset is not null
//                    && pieChartDataset.Data is not null)
//                {
//                    var count = pieChartDataset.Data.Count;

//                    var newData = new List<double>();
//                    for (var i = 0; i < count; i++)
//                    {
//                        newData.Add(random.Next(0, 100));
//                    }

//                    pieChartDataset.Data = newData;
//                    newDatasets.Add(pieChartDataset);
//                }
//            }

//            chartData.Datasets = newDatasets;

//            await pieChart.UpdateAsync(chartData, pieChartOptions);
//        }

//        private async Task AddDatasetAsync()
//        {
//            if (chartData is null || chartData.Datasets is null) return;

//            var chartDataset = GetRandomPieChartDataset();
//            chartData = await pieChart.AddDatasetAsync(chartData, chartDataset, pieChartOptions);
//        }

//        private async Task AddDataAsync()
//        {
//            if (dataLabelsCount >= 12)
//                return;

//            if (chartData is null || chartData.Datasets is null)
//                return;

//            var data = new List<IChartDatasetData>();
//            foreach (var dataset in chartData.Datasets)
//            {
//                if (dataset is PieChartDataset pieChartDataset)
//                    data.Add(new PieChartDatasetData(pieChartDataset.Label, random.Next(0, 100), backgroundColors![dataLabelsCount]));
//            }

//            chartData = await pieChart.AddDataAsync(chartData, GetNextDataLabel(), data);

//            dataLabelsCount += 1;
//        }

//        #region Data Preparation

//        private List<IChartDataset> GetDefaultDataSets(int numberOfDatasets)
//        {
//            var datasets = new List<IChartDataset>();

//            for (var index = 0; index < numberOfDatasets; index++)
//            {
//                datasets.Add(GetRandomPieChartDataset());
//            }

//            return datasets;
//        }

//        private PieChartDataset GetRandomPieChartDataset()
//        {
//            datasetsCount += 1;
//            return new() { Label = $"Team {datasetsCount}", Data = GetRandomData(), BackgroundColor = GetRandomBackgroundColors() };
//        }

//        private List<double> GetRandomData()
//        {
//            var data = new List<double>();
//            for (var index = 0; index < dataLabelsCount; index++)
//            {
//                data.Add(random.Next(0, 100));
//            }

//            return data;
//        }

//        private List<string> GetRandomBackgroundColors()
//        {
//            var colors = new List<string>();
//            for (var index = 0; index < dataLabelsCount; index++)
//            {
//                colors.Add(backgroundColors![index]);
//            }

//            return colors;
//        }

//        private List<string> GetDefaultDataLabels(int numberOfLabels)
//        {
//            var labels = new List<string>();
//            for (var index = 0; index < numberOfLabels; index++)
//            {
//                labels.Add(GetNextDataLabel());
//                dataLabelsCount += 1;
//            }

//            return labels;
//        }

//        private string GetNextDataLabel() => $"Product {dataLabelsCount + 1}";

//        private string GetNextDataBackgrounfColor() => backgroundColors![dataLabelsCount];

//        #endregion  Data Preparation

//        #region Handlers
//        private void SelectedChangeHandler()
//        {
//            IsDisabledEdit = selectedTarea == null;
//        }

//        private void ValueChangeHandler()
//        {
//            IsDisabled = false;
//            //IsDisabled = (String.IsNullOrWhiteSpace(nuevaTarea.Nombre) || String.IsNullOrWhiteSpace(nuevaTarea.Descripcion));
//        }

//        private async Task OnAutoCompleteChanged(Tarea tarea)
//        {
//            SelectedTarea = tarea;
//            //await JS.InvokeVoidAsync($"searchFuction('{SelectedTarea.Nombre}')");
//            Console.WriteLine($"'{tarea?.Nombre}' selected.");
//        }

//        #endregion Handlers

//        #region Enums
//        public enum Accion
//        {
//            Espera,
//            Crear,
//            Editar
//        }

//        public enum Finalizado
//        {
//            No,
//            Si
//        }

//        #endregion Enums
//    }

//}
