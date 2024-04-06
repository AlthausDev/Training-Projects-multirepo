using System.Collections.ObjectModel;
using System.Windows;
using System.Windows.Controls;

namespace TesWPFtApp
{  

    /// <summary>
    /// Lógica de interacción para DataGridView.xaml
    /// </summary>
    public partial class DataGridView : Window
    {
        public ObservableCollection<Persona> LstPersonas { get; set; }

        public DataGridView()
        {
            InitializeComponent();
            FillList();
            this.DataContext = this;
        }

        private void FillList()
        {
            LstPersonas =
            [
                new Persona(1, "Hans Landa", DateTime.Parse("01/01/2009")),
                new Persona(2, "Aldo Raine", DateTime.Parse("11/03/1969")),
                new Persona(3, "Shosanna Dreyfuss", DateTime.Parse("19/07/1985")),
                new Persona(4, "Bridget Von Hammersmark", DateTime.Parse("26/09/1955")),
                new Persona(5, "Donny Donowitz", DateTime.Parse("22/12/1996")),
            ];
        }

        private void BtnAddPerson_Click(object sender, RoutedEventArgs e)
        {
            Persona persona = new();

            if (LstPersonas.Count == 0)
            {
                persona.Id = 1;
            }
            else
            {
                persona.Id = LstPersonas.Max(x => x.Id) + 1;
            }

            persona.FechaNacimiento = DateTime.Now;
            DialogoPersona dialogoPersona = new(persona);
            dialogoPersona.ShowDialog();     
            
            if(dialogoPersona != null)
            {
                LstPersonas.Add(persona);
            }

        }
    }    
}
