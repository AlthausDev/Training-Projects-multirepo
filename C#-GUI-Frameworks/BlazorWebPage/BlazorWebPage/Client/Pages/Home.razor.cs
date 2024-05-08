using BlazorBootstrap;
using BlazorWebPage.Client.Shared.Modals;
using BlazorWebPage.Shared;
using Microsoft.AspNetCore.Components;
using Microsoft.AspNetCore.Components.Web;
using Microsoft.IdentityModel.Tokens;
using System.Diagnostics;
using System.Net.Http.Json;
using ToastType = BlazorBootstrap.ToastType;

namespace BlazorWebPage.Client.Pages
{
    public partial class Home
    {
        private Modal modal = default!;
        private string? message = string.Empty;

        public List<User> Usuarios { get; set; } = new List<User>();
        public static User NewUser { get; set; } = new();

        private double[] DataArray = new double[12];

        List<ToastMessage> messages = new();
        private LineChart lineChart = default!;
        private LineChartOptions lineChartOptions = default!;
        private ChartData chartData = default!;


        protected override async Task OnInitializedAsync()
        {
            await getData();
            await InitializeGraph();
        }

        #region Modal

        #region Login
        private async Task Login()
        {
            var parameters = new Dictionary<string, object>();
            parameters.Add("Login", EventCallback.Factory.Create<MouseEventArgs>(this, IniciarSesion));
            parameters.Add("Cerrar", EventCallback.Factory.Create<MouseEventArgs>(this, HideModal));
            await modal.ShowAsync<ModalLogin>(title: "Logearse", parameters: parameters);
        }

        private async Task IniciarSesion()
        {           
            foreach (User user in Usuarios)
            {
                if (user.UserName == NewUser.UserName && user.Password == NewUser.Password)
                {                   
                    NavManager.NavigateTo($"/Sesion/{user.Id}", true);
                    break;
                }
            }

            await HideModal();
            ShowMessage(ToastType.Danger, "Inicio de sesión fallido");
        }

        #endregion Login

        #region Registro

        private async Task Registro()
        {
            var parameters = new Dictionary<string, object>();
            parameters.Add("Registrar", EventCallback.Factory.Create<MouseEventArgs>(this, NuevoUsuario));
            parameters.Add("Cerrar", EventCallback.Factory.Create<MouseEventArgs>(this, HideModal));
            await modal.ShowAsync<ModalRegistro>(title: "Registrarse", parameters: parameters);
        }

        private async Task NuevoUsuario()
        {
            bool existe = false;

            foreach(User user in Usuarios)
            {
                if (user.UserName == NewUser.UserName || (user.Email == NewUser.Email && !NewUser.Email.IsNullOrEmpty()))
                { 
                    existe = true;
                    ShowMessage(ToastType.Danger, "El nombre de usuario o email ya está registrado");
                    await HideModal();
                    break;
                }
            }

            if(!existe)
            {
                await Post();               
                ShowMessage(ToastType.Success, "Registro realizado con éxito");        
                await IniciarSesion();                
           
            }
        }
        #endregion Registro              

        private async Task HideModal()
        {
            NewUser = new User();
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
                foreach (User user in Usuarios)
                {
                    getUsersMoth(user);
                }
            }
        }

        private async Task Post()
        {            
                Usuarios.Add(NewUser);
                HttpResponseMessage httpResponseMessage = await Http.PostAsJsonAsync("user", NewUser);
                Console.WriteLine(httpResponseMessage);
                await getData();           
        }

        #endregion ApiOperations

        #region AuxMetods
        private double getUsersMoth(User user)
        {
            int index = user.FechaRegistro.IndexOf("/");

            string mes = user.FechaRegistro[..index];
            double mesDouble = Double.Parse(mes);

            FillDataArray(mesDouble);
            return mesDouble;
        }

        private void FillDataArray(double mes)
        {
            int position = (int)(mes - 1);
            DataArray[position]++;

        }

        private void ImprimirUsuarios()
        {
            foreach (var usuario in Usuarios)
            {
                Console.WriteLine(usuario.ToString());
            }
        }

        private bool CheckFormat(string word)
        {
            if (string.IsNullOrWhiteSpace(word) || word.Length < 3)
            {                
                ShowMessage(ToastType.Warning, "El Nombre de usuario y la contraseña deben tener al menos 3 carácteres");
                return false;
            }

            return true;
        }
        #endregion AuxMetods

        #region Toast
        private void ShowMessage(ToastType toastType, string message) => messages.Add(CreateToastMessage(toastType, message));

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
