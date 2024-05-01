using BlazorWebPage.Client.Pages;
using BlazorWebPage.Shared;
using Microsoft.AspNetCore.Components.Web;
using Microsoft.AspNetCore.Components;

namespace BlazorWebPage.Client.Shared.Modals
{
    partial class ModalEditUser
    {
        [Parameter]
        public int Id { get; set; }
        private string checkPassword { get; set; } = string.Empty;

        [Parameter]
        public string UserName { get; set; } = string.Empty;

        [Parameter]
        public string Password { get; set; } = string.Empty;

        [Parameter]
        public string? Nombre { get; set; } = string.Empty;

        [Parameter]
        public string? Email { get; set; } = string.Empty;

        private bool IsDisabled = true;
        private string color = "black";

        [Parameter] public EventCallback<MouseEventArgs> Editar { get; set; }
        [Parameter] public EventCallback<MouseEventArgs> Cerrar { get; set; }


        #region Handlers
        protected void OnClickEdit()
        {
            User user = new(UserName, Password, Nombre, Email, DateTimeOffset.Now.ToString("dd-MM-yyyy"));
            Sesion.user = user;
            Editar.InvokeAsync();
        }
        #endregion Handlers
    }
}