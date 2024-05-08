using BlazorBootstrap;
using BlazorWebPage.Shared;
using System.Net.Http.Json;
using ToastType = BlazorBootstrap.ToastType;


namespace BlazorWebPage.Client.Pages
{
    public partial class Index
    {

        public List<User> Usuarios { get; set; } = new List<User>();
        private User NewUser { get; set; } = new();

        List<ToastMessage> messages = new List<ToastMessage>();

        private LineChart lineChart = default!;
        private LineChartOptions lineChartOptions = default!;
        private ChartData chartData = default!;



        protected override async Task OnInitializedAsync()
        {
            await getData();
            await InitializeGraph();
        }


        #region ApiOperations
        private async Task getData()
        {
            User[]? usuariosArray = await Http.GetFromJsonAsync<User[]>("user");

            if (usuariosArray is not null)
            {
                Usuarios = usuariosArray.ToList();                
            }
            Console.WriteLine(Usuarios.Count);
        }

        private async Task Post()
        {
            Usuarios.Add(NewUser);
            await Http.PostAsJsonAsync("User", Usuarios.ToArray());
            await getData();
        }

        private async Task Put()
        {
            //Usuarios.Insert(Usuarios.IndexOf(selectedUser), newUser);
            await Delete();
        }

        private async Task Delete()
        {            //if (selectedUser != null)
            //{
            //    Usuarios.Remove(selectedUser);
            //    await Http.PostAsJsonAsync<User[]>("User", Usuarios.ToArray());
            //    await getData();
            //    SelectedUser = null;

            //    if (accion.Equals(Accion.Espera))
            //    {
            //        ShowMessage(ToastType.Danger, "Registro eliminado con éxito");
            //    }
            //}

        }
        #endregion ApiOperations

        #region Toast
        private ToastMessage CreateToastMessage(ToastType toastType, string message)
        {
            var toastMessage = new ToastMessage();
            toastMessage.Type = toastType;
            toastMessage.Message = message;

            return toastMessage;
        }
        #endregion Toast

        #region LineChart

        protected async Task InitializeGraph()
        {
            var colors = ColorBuilder.CategoricalTwelveColors;

            var labels = new List<string> { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Deciembre" };
            var datasets = new List<IChartDataset>();

            var dataset = new LineChartDataset
            {
                Label = "Registros",
                Data = new List<double> { 7265791, 5899643, 6317759, 6315641, 5338211, 8496306, 7568556, 8538933, 8274297, 8657298, 7548388, 7764845 },
                BackgroundColor = new List<string> { colors[7] },
                BorderColor = new List<string> { colors[7] },
                BorderWidth = new List<double> { 2 },
                HoverBorderWidth = new List<double> { 4 },
                PointBackgroundColor = new List<string> { colors[7] },
                PointRadius = new List<int> { 3 },
                PointHoverRadius = new List<int> { 4 },

                Datalabels = new() { Align = "end", Anchor = "end" }
            };
            datasets.Add(dataset);


            chartData = new ChartData
            {
                Labels = labels,
                Datasets = datasets
            };

            lineChartOptions = new()
            {
                Responsive = true,
                Interaction = new Interaction { Mode = InteractionMode.Index }
            };

            lineChartOptions.Scales.X!.Title!.Text = "Meses";
            lineChartOptions.Scales.X.Title.Display = true;

            lineChartOptions.Scales.Y!.Title!.Text = "Usuarios Registrados";
            lineChartOptions.Scales.Y.Title.Display = true;

            // datalabels
            lineChartOptions.Plugins.Datalabels.Color = "white";

            if (true)
            {
                await lineChart.InitializeAsync(chartData: chartData, chartOptions: lineChartOptions, plugins: new string[] { "ChartDataLabels" });
            }
            await base.OnAfterRenderAsync(true);
        }
        #endregion LineChart
    }
}
