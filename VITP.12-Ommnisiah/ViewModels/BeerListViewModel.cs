using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;
using VITP._12_Ommnisiah.Model;
using VITP._12_Ommnisiah.Utils;

namespace VITP._12_Ommnisiah.ViewModel
{
    public class BeerListViewModel : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged;

        public ICommand FilterByBrewerCommand { get; private set; }

        private string _brewerFilterText;
        public string BrewerFilterText
        {
            get { return _brewerFilterText; }
            set
            {
                _brewerFilterText = value;
                OnPropertyChanged(nameof(BrewerFilterText));
                FilterByBrewer();
            }
        }

        private ObservableCollection<Beer> _beers;
        public ObservableCollection<Beer> Beers
        {
            get { return _beers; }
            set
            {
                _beers = value;
                OnPropertyChanged(nameof(Beers));
            }
        }


        // Constructor
        public BeerListViewModel()
        {
            FilterByBrewerCommand = new RelayCommand(FilterByBrewer);

            // Inicializar la lista de cervezas
            Beers = new ObservableCollection<Beer>();
            LoadBeers();
        }

        // Método para cargar las cervezas desde algún origen de datos
        private void LoadBeers()
        {
            Beers = new ObservableCollection<Beer>(CsvConfig.ReadBeersFromCsv());
        }

        public void FilterByBrewer()
        {
            if (string.IsNullOrWhiteSpace(BrewerFilterText))
            {
                Beers = new ObservableCollection<Beer>(CsvConfig.ReadBeersFromCsv());
            }
            else
            {
                var filteredList = new ObservableCollection<Beer>(
                    CsvConfig.ReadBeersFromCsv().FindAll(b => b.Brewer.Contains(BrewerFilterText))
                );
                Beers = filteredList;
            }
        }

        // Método para notificar cambios en las propiedades
        protected void OnPropertyChanged(string propertyName)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}
