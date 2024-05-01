using BlazorBootstrap;
using BlazorWebPage.Client.Shared.Modals;
using BlazorWebPage.Shared;
using Microsoft.AspNetCore.Components;
using Microsoft.AspNetCore.Components.Web;
using System.Net.Http.Json;

namespace BlazorWebPage.Client.Pages
{
    partial class Sesion
    {
        [Parameter]
        public string Id { get; set; }
        private string fecha;

        public static User user = new();

        private IEnumerable<User> Usuarios = default!;
        private Modal modal = default!;

        protected override async Task OnInitializedAsync()
        {
            await getData();
            fecha = DateFormat();
            if (user.UserName.Equals("Admin"))
            {
                ShowTable();
            }
        }


        #region ApiOperations

        private async Task getAllData()
        {
            User[]? usuariosArray = await Http.GetFromJsonAsync<User[]>("user");

            if (usuariosArray is not null)
            {
                Usuarios = usuariosArray.ToList();              
            }
        }

        private async Task getData()
        {
            try {
                user = await Http.GetFromJsonAsync<User>($"user/{Id}");
                Console.WriteLine(user);                
            }
            catch (Exception ex)
            {
                Console.WriteLine("\nMessage ---\n{0}", ex.Message);
                Console.WriteLine(
                    "\nHelpLink ---\n{0}", ex.HelpLink);
                Console.WriteLine("\nSource ---\n{0}", ex.Source);
                Console.WriteLine(
                    "\nStackTrace ---\n{0}", ex.StackTrace);
                Console.WriteLine(
                    "\nTargetSite ---\n{0}", ex.TargetSite);
            }
        }

        private async Task Put(User user)
        {
            await Http.PutAsJsonAsync("User", user);
        }


        private async Task Delete()
        {
            HttpResponseMessage httpResponseMessage = await Http.DeleteAsync($"/Delete/{user.Id}");
            Console.WriteLine(httpResponseMessage);

            NavManager.NavigateTo("/", true);
        }
        #endregion


        #region EditModal

        private async Task EditModal()
        {
            var parameters = new Dictionary<string, object>();
            parameters.Add("Id", user.Id);
            parameters.Add("UserName", user.UserName);
            parameters.Add("Password", user.Password);
            parameters.Add("Nombre", user.Nombre);
            parameters.Add("Email", user.Email);
            parameters.Add("Login", EventCallback.Factory.Create<MouseEventArgs>(this, EditarDatosAsync));
            parameters.Add("Cerrar", EventCallback.Factory.Create<MouseEventArgs>(this, Cancel));
            await modal.ShowAsync<ModalEditUser>(title: "Editar", parameters: parameters);
        }


        private async Task EditarDatosAsync()
        {
            await Put(user);
        }

        private async Task Cancel()
        {
            await modal.HideAsync();
        }


        #endregion

        #region Grid
        //private async Task<GridDataProviderResult<User>> UsersDataProvider(GridDataProviderResult<User> request)
        //{
        //    return await Task.FromResult(request.ApplyTo(Usuarios.OrderBy(user => user.Id)));
        //}

        private void ShowTable()
        {
            getAllData();
        }
        #endregion

        #region Aux
        private string DateFormat()
        {
           int index = user.FechaRegistro.IndexOf(" ");
           return user.FechaRegistro[..index];
        }
        #endregion
       
    }
}
