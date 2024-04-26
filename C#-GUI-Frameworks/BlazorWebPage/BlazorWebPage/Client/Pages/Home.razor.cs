using BlazorBootstrap;
using BlazorWebPage.Shared;
using Microsoft.AspNetCore.Components.Web;
using Microsoft.AspNetCore.Components;
using Smart.Blazor;
using System.Net.Http.Json;
using ToastType = BlazorBootstrap.ToastType;
using BlazorWebPage.Client.Shared;

namespace BlazorWebPage.Client.Pages
{
    public partial class Home
    {
        private Modal modal = default!;
        private string? message;

        public List<User> Usuarios { get; set; } = new List<User>();     
        public static User NewUser { get; set; } = new();


        private double[] DataArray = new double[12]; 
        List<ToastMessage> messages = new();        
        private void ShowMessage(ToastType toastType, string message) => messages.Add(CreateToastMessage(toastType, message));


        private LineChart lineChart = default!;
        private LineChartOptions lineChartOptions = default!;
        private ChartData chartData = default!;


        protected override async Task OnInitializedAsync()
        {
            await getData();
            await InitializeGraph();
        }

        #region Modal
        private async Task Registro()
        {
            var parameters = new Dictionary<string, object>();
            parameters.Add("Id", getNewId());
            parameters.Add("Registrar", EventCallback.Factory.Create<MouseEventArgs>(this, NuevoUsuario));
            parameters.Add("Cerrar", EventCallback.Factory.Create<MouseEventArgs>(this, HideModal));
            await modal.ShowAsync<ModalRegistro>(title: "Registrarse", parameters: parameters);
        }

        private async Task NuevoUsuario(MouseEventArgs e)
        {            
            await Post();
            ShowMessage(ToastType.Success, "Registro agregado con éxito");
            await HideModal();
        }  

        private void ImprimirUsuarios()
        {
            foreach (var usuario in Usuarios)
            {
                Console.WriteLine(usuario.ToString());
            }
        }

        private long getNewId()
        {
            long newId = 0;

            foreach (User user in Usuarios)
            {                
                if(user.Id >= newId)
                {                    
                    newId = user.Id+1;                 
                }
            }
            return newId;
        }

        private async Task HideModal()
        {           
            await modal.HideAsync();
        }

        #endregion Modal

        #region ApiOperations
        private async Task getData()
        {
            User[]? usuariosArray = await Http.GetFromJsonAsync<User[]>("user");

            if (usuariosArray is not null)
            {
                Usuarios = usuariosArray.ToList();                
            }          

            foreach (User user in Usuarios)
            {
                getUsersMoth(user);
            }
        }
       

        private async Task Post()
        {
            Usuarios.Add(NewUser);
            await Http.PostAsJsonAsync("User", Usuarios.ToArray());
            await getData();
        }

        public async Task registroAsync(User user)
        {
            NewUser = user;
            await Post();
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

        #region AuxMetods
        private double getUsersMoth(User user)
        {
            int index = user.FechaRegistro.IndexOf("-");

            string mes = user.FechaRegistro.Substring(index + 1, index);
            double mesDouble = Double.Parse(mes);

            FillDataArray(mesDouble);

            return mesDouble;
        }

        private void FillDataArray(double mes)
        {
            int position = (int)(mes - 1);
            DataArray[position]++;

        }
        #endregion AuxMetods

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
                Data = DataArray.ToList(),

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
