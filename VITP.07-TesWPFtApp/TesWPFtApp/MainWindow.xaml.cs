using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

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
            fillList();
            this.DataContext = this;
        }

        private void fillList() { 
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

        private void btnNombre_Click(object sender, RoutedEventArgs e)
        {
            if (!string.IsNullOrEmpty(txtNombre.Text))
            {
                int newId = LstPersonas.Max(p => p.Id) + 1;

                Persona persona = new Persona(newId, txtNombre.Text);
                LstPersonas.Add(persona);
                btnClear_Click(sender, e);
            }
        }

        private void btnModificar_Click(object sender, RoutedEventArgs e)
        {
            if (!string.IsNullOrEmpty(txtNombre.Text))
            {
                int index = cbNombre.SelectedIndex;
                Persona persona = LstPersonas[index];

                persona.Name = txtNombre.Text;
                LstPersonas[index] = persona;
               
            }
        }


        private void btnEliminar_Click(object sender, RoutedEventArgs e)
        {
            LstPersonas.Remove(cbNombre.SelectedItem as Persona);
        }

        private void btnClear_Click(object sender, RoutedEventArgs e)
        {
            txtNombre.Clear();
        }
    }
}