using Microsoft.AspNetCore.Components;
using Microsoft.AspNetCore.Components.Web;

namespace BlazorWebPage.Client.Shared
{
    partial class ModalLogin
    {
        //private string checkPassword { get; set; } = string.Empty;
        private string UserName { get; set; } = string.Empty;
        private string Password { get; set; } = string.Empty;

        private bool IsDisabled = true;
        private string color = "black";

        [Parameter] public EventCallback<MouseEventArgs> Login { get; set; }
        [Parameter] public EventCallback<MouseEventArgs> Cerrar { get; set; }


        #region Handlers
        protected void OnClickRegistro()
        {
            //User user = new(Id, UserName, Password, Nombre, Email);
            //Home.NewUser = user;
            Login.InvokeAsync();
        }

        private void ValueChangeHandler()
        {
            IsDisabled = (String.IsNullOrWhiteSpace(UserName) || String.IsNullOrWhiteSpace(Password));
        }

        //private bool CheckPasswordHandler()
        //{

        //    if (!Password.Equals(checkPassword))
        //    {
        //        color = "red";
        //        return true;
        //    }
        //    else
        //    {
        //        color = "black";
        //        return false;
        //    }

        //}
        #endregion Handlers
    }
}

