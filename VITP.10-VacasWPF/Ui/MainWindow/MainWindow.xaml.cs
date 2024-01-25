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
using VITP._10_VacasWPF.Model;

namespace VITP._10_VacasWPF.Ui.MainWindow
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private List <Vaca> ListVacas { get; set; }
        private AppDBContext DbContext = new();


        public MainWindow()
        {
            InitializeComponent();
            FillDataGrid();
        }

        private void FillDataGrid()
        {
            this.DataContext = DbContext;
            using StreamReader reader = new StreamReader(Utils.CsvConfig.PathFile);
            using (var csv = new CsvReader(reader, Utils.CsvConfig.Config))
            {
                ListVacas = new List<Vaca>(csv.GetRecords<Vaca>().ToList());
                ListVacas.ForEach(v => DbContext.Vacas.Add(v));
            };
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            int newKey = DbContext.Vacas.Max(V => V.Id) + 1;
            Vaca vaca = new Vaca(newKey, "Oviedo", new DateOnly(2020,6,13), 
                new DateOnly(2020, 8, 13), 34, 50, "H", "C");
            DbContext.Vacas.Add(vaca);
            DbContext.SaveChanges();
        }

        private void BtnEliminar_Click(object sender, RoutedEventArgs e)
        {
            DbContext.Vacas.Remove(DgVacas.SelectedItem as Vaca);
            DbContext.SaveChanges();
        }
    }
}