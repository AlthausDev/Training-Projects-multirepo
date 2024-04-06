using System.Collections.ObjectModel;
using System.Windows;
using System.Windows.Controls;

namespace TesWPFtApp
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window {        
       
        public ObservableCollection<Persona> LstPersonas { get; set; }

        public MainWindow()
        {      
            InitializeComponent();
            FillList();
            this.DataContext = this;
        }

        private void FillList() { 
            LstPersonas =
            [
                new Persona(1, "Hans Landa"),
                new Persona(2, "Aldo Raine"),
                new Persona(3, "Shosanna Dreyfuss"),
                new Persona(4, "Bridget Von Hammersmark"),
                new Persona(5, "Donny Donowitz"),
            ]; 
        }

        private void TextBox_TextChanged(object sender, TextChangedEventArgs e)
        {
     
        }

        private void BtnNombre_Click(object sender, RoutedEventArgs e)
        {
            if (!string.IsNullOrEmpty(txtNombre.Text))
            {
                int newId = LstPersonas.Max(p => p.Id) + 1;

                Persona persona = new Persona(newId, txtNombre.Text);
                LstPersonas.Add(persona);
                BtnClear_Click(sender, e);
            }
        }

        private void BtnModificar_Click(object sender, RoutedEventArgs e)
        {
            if (!string.IsNullOrEmpty(txtNombre.Text))
            {
                int index = cbNombre.SelectedIndex;
                Persona persona = LstPersonas[index];

                persona.Name = txtNombre.Text;
                LstPersonas[index] = persona;
               
            }
        }


        private void BtnEliminar_Click(object sender, RoutedEventArgs e)
        {
            LstPersonas.Remove(cbNombre.SelectedItem as Persona);
        }

        private void BtnClear_Click(object sender, RoutedEventArgs e)
        {
            txtNombre.Clear();
        }
    }
}