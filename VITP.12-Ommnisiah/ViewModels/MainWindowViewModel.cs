using System;
using System.ComponentModel;
using System.Windows;
using System.Windows.Input;
using VITP._12_Ommnisiah.Pages;

namespace VITP._12_Ommnisiah.ViewModel
{
    public class MainWindowViewModel : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged;

        // Comandos para cambiar de página
        public ICommand NavigateToPage1Command { get; }
        public ICommand NavigateToPage2Command { get; }

        public MainWindowViewModel()
        {
            // Inicializar los comandos
            NavigateToPage1Command = new RelayCommand(NavigateToPage1);
            NavigateToPage2Command = new RelayCommand(NavigateToPage2);
        }

        private void NavigateToPage1()
        {
            MainWindow mainWindow = Application.Current.MainWindow as MainWindow;
            if (mainWindow != null)
            {
                mainWindow.MainFrame.Navigate(new Page1());
            }
        }

        private void NavigateToPage2()
        {
            MainWindow mainWindow = Application.Current.MainWindow as MainWindow;
            if (mainWindow != null)
            {
                mainWindow.MainFrame.Navigate(new Page2());
            }
        }
    }
}
