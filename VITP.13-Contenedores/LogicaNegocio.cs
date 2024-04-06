using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Linq;
using System.Reflection.PortableExecutable;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;
using System.Windows.Input;

namespace Examen5AAS
{
    public class LogicaNegocio : INotifyPropertyChanged
    {

        public event PropertyChangedEventHandler PropertyChanged;

        public ICommand FilterByContenidoCommand { get; private set; }

        private string _contenidoFilterText;
        public string ContenidoFilterText
        {
            get { return _contenidoFilterText; }
            set
            {
                _contenidoFilterText = value;
                OnPropertyChanged(nameof(ContenidoFilterText));
                FilterByContenido();
            }
        }

        private ObservableCollection<Contenedor> _contenedores;
        public ObservableCollection<Contenedor> Contenedores
        {
            get { return _contenedores; }
            set
            {
                _contenedores = value;
                OnPropertyChanged(nameof(Contenedores));
            }
        }

        private int _contenedoresPesoMedio;
        private int _pesoMedio;
        public static List<string> tipoContenidos = new List<string>();


        public int ContenedoresPesoMedio {
            get
            {
                return _contenedoresPesoMedio;
            }
            set
            {
                if (_contenedoresPesoMedio != value)
                {
                    _contenedoresPesoMedio = value;
                    OnPropertyChanged(nameof(ContenedoresPesoMedio));
                }
            }
        }


        public LogicaNegocio()
        {
            FilterByContenidoCommand = new RelayCommand(FilterByContenido);
            Contenedores = new ObservableCollection<Contenedor>();
            Contenedores = GetAllContenedores();

            tipoContenidos = (from c in Contenedores
                              select c.TipoContenido).Distinct().ToList();
        }      

        public void FilterByContenido()
        {
            if (string.IsNullOrWhiteSpace(ContenidoFilterText))
            {               
                Contenedores = GetAllContenedores();
            }
            else
            {        
                _pesoMedio = 0;
                var filteredList = new ObservableCollection<Contenedor>();                
                foreach (var item in GetAllContenedores())
                {
                    if (item.TipoContenido.ToLower() == ContenidoFilterText.ToLower())
                    {
                        filteredList.Add(item);
                        _pesoMedio += item.Peso;
                    }
                }
                ContenedoresPesoMedio = _pesoMedio / filteredList.Count;
                Contenedores = filteredList;
            }
        }

        // Método para notificar cambios en las propiedades
        protected void OnPropertyChanged(string propertyName)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }  


        public static ObservableCollection<Contenedor> GetAllContenedores()
        {
                ObservableCollection<Contenedor> retValue = new ObservableCollection<Contenedor>();

                retValue.Add(new Contenedor("D0976", 72, 53, "IND"));
                retValue.Add(new Contenedor("X9769", 52, 67, "IND"));
                retValue.Add(new Contenedor("P0966", 74, 75, "FRA"));
                retValue.Add(new Contenedor("D0980", 51, 15, "LIQ"));
                retValue.Add(new Contenedor("F76435", 56, 27, "SOL"));
                retValue.Add(new Contenedor("L7272", 77, 35, "FRA"));
                retValue.Add(new Contenedor("K0962", 153, 46, "LIQ"));
                retValue.Add(new Contenedor("J1021", 12, 46, "SOL"));
                retValue.Add(new Contenedor("A4501", 24, 65, "IND"));

            retValue = new ObservableCollection<Contenedor>(retValue.OrderBy(i => i.TipoContenido));
            return retValue;
        }
    }
}