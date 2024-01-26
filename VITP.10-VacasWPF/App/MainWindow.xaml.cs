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

namespace VITP._10_VacasWPF.App
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
            using StreamReader reader = new StreamReader(Utils.CsvConfig.PathFile);
            using (var csv = new CsvReader(reader, Utils.CsvConfig.Config))
            {
                ListVacas = new List<Vaca>(csv.GetRecords<Vaca>().ToList());
                ListVacas.ForEach(v => DbContext.ListVacas.Add(v));
            };
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            int newKey = DbContext.ListVacas.Max(V => V.Id) + 1;
            Vaca vaca = new Vaca(newKey, "Oviedo", new DateOnly(2020,6,13), 
                new DateOnly(2020, 8, 13), 34, 50, "H", "C");
            ListVacas.Add(vaca);
            DbContext.ListVacas.Add(vaca);
            DbContext.SaveChanges();
        }

        private void BtnEliminar_Click(object sender, RoutedEventArgs e)
        {
            if (DgVacas.SelectedItem != null)
            {
                DbContext.ListVacas.Remove(DgVacas.SelectedItem as Vaca);
                ListVacas.Remove(DgVacas.SelectedItem as Vaca); // Remueve la vaca de la lista
                DbContext.SaveChanges();
            }
        }
    }
}