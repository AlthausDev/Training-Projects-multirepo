using CsvHelper;
using CsvHelper.Configuration;
using System.IO;
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
using VacasWPF.Model;
using VacasWPF;
using VacasWPF.Utils;

namespace VacasWPF
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public List <Vaca> ListVacas { get; set; }
        private AppDBContext DbContext = new AppDBContext();


        public MainWindow()
        {
            InitializeComponent();
            FillDataGrid();
        }

        public void FillDataGrid()
        {
            this.DataContext = DbContext;
            using (var reader = new StreamReader(CsvConfig.PathFile))
            using (var csv = new CsvReader(reader, CsvConfig.Config))
            {
                ListVacas = new List<Vaca>(csv.GetRecords<Vaca>().ToList());
                ListVacas.ForEach(v => DbContext.ListVacas.Add(v));
            }
        }

        private void BtnEliminar_Click(object sender, RoutedEventArgs e)
        {
            if (DgVacas.SelectedItem != null)
            {
                DbContext.ListVacas.Remove(DgVacas.SelectedItem as Vaca);
                ListVacas.Remove(DgVacas.SelectedItem as Vaca);
                DbContext.SaveChanges();
            }
        }   

        private void btnNueva_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                int newKey = DbContext.ListVacas.Max(v => v.id) + 1;
                Vaca vaca = new Vaca(newKey,
                    "Oviedo",
                    new DateOnly(2008, 4, 2),
                    new DateOnly(2008, 9, 21),
                    123,
                    87,
                    "H",
                    "C");
                DbContext.ListVacas.Add(vaca);
                DbContext.SaveChanges();

                MessageBox.Show("¡Vaca agregada exitosamente!", "Éxito", MessageBoxButton.OK, MessageBoxImage.Information);
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Se produjo un error: {ex.Message}", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }

        private void DgVacas_CellEditEnding(object sender, DataGridCellEditEndingEventArgs e)
        {
            if (e.EditAction == DataGridEditAction.Commit)
            {
                Vaca vacaGrid = e.Row.Item as Vaca;
                if (vacaGrid != null)
                {
                    DbContext.SaveChanges();
                }
            }
        }
    }
}