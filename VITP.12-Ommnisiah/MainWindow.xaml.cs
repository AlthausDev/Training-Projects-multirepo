using System.Windows;
using VITP._12_Ommnisiah.Pages;
using VITP._12_Ommnisiah.ViewModel;

namespace VITP._12_Ommnisiah
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            InitializeMainWindow();      
        }

        private void InitializeMainWindow()
        {
            try
            {         
                // Establecer el DataContext de la ventana
                DataContext = new MainWindowViewModel();

                // Navegar a la página predeterminada
                MainFrame.Navigate(new Page1());
            }
            catch (Exception ex)
            {
                // Manejar cualquier excepción que ocurra durante la inicialización
                MessageBox.Show($"Error al inicializar la ventana principal: {ex.Message}", 
                    "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                Close(); // Cerrar la ventana en caso de error grave
            }
        }
    }
}