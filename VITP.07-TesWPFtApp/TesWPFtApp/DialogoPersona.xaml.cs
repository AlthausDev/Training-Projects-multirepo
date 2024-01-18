using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace TesWPFtApp
{
       
    /// <summary>
    /// Lógica de interacción para DialogoPersona.xaml
    /// </summary>
    public partial class DialogoPersona : Window
    {
        private Persona Persona { get; set; }

        public DialogoPersona(Persona persona)
        {
            InitializeComponent();
            this.Persona = persona;
            this.DataContext = Persona;
        }

        private void BtnCancelar_Click(object sender, RoutedEventArgs e)
        {
            Persona.Id = -1;
            this.Close();
        }

        private void BtnAceptar_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }
    }
}
