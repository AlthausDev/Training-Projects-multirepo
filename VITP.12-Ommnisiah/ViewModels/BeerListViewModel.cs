using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using VITP._12_Ommnisiah.Model;
using VITP._12_Ommnisiah.Utils;

namespace VITP._12_Ommnisiah.ViewModel
{
    public class BeerListViewModel : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged;

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
            // Inicializar la lista de cervezas
            Beers = new ObservableCollection<Beer>();
            LoadBeers();
        }

        // Método para cargar las cervezas desde algún origen de datos
        private void LoadBeers()
        {
            Beers = new ObservableCollection<Beer>(CsvConfig.ReadBeersFromCsv());
        }

        // Método para notificar cambios en las propiedades
        protected void OnPropertyChanged(string propertyName)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}
