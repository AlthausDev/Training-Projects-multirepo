using CsvHelper.Configuration.Attributes;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VITP._10_VacasWPF.Model
{
    internal class Vaca
    {

        #region propiedades
        [Key]
        public int Id { get; set; }


        [Format("dd/MM/yyyy")]
        public DateOnly FechaNacimientoDate { get => _fechaNaciminetoDate; set => _fechaNaciminetoDate = value; }
        public DateOnly FechaDestete { get => _fechaDestete; set => _fechaDestete = value; }
        public int Alzada { get => _alzada; set => _alzada = value; }
        public int Peso { get => _peso; set => _peso = value; }
        public string Sexo { get => _sexo; set => _sexo = value; }
        public string Tipo { get => _tipo; set => _tipo = value; }
        public string NomMunicipio { get => _nomMunicipio; set => _nomMunicipio = value; }

        #endregion

        #region Variables miembro privadas
        private string _nomMunicipio;
        private DateOnly _fechaNaciminetoDate;
        private DateOnly _fechaDestete;
        private int _alzada;
        private int _peso;
        private String _sexo;
        private String _tipo;
        #endregion

        #region Constructores
        public Vaca()
        {
        }

        public Vaca(int id, DateOnly fechaNaciminetoDate, DateOnly fechaDestete, int alzada, 
            int peso, string sexo, string tipo, string nomMunicipio)
        {
            Id = id;
            FechaNacimientoDate = fechaNaciminetoDate;
            FechaDestete = fechaDestete;
            Alzada = alzada;
            Peso = peso;
            Sexo = sexo;
            Tipo = tipo;
            NomMunicipio = nomMunicipio;
        }

        public Vaca(int id, string nomMunicipio, DateOnly fechaNaciminetoDate, DateOnly fechaDestete, 
            int alzada, int peso, string sexo, string tipo)
        {
            Id = id;
            _nomMunicipio = nomMunicipio;
            _fechaNaciminetoDate = fechaNaciminetoDate;
            _fechaDestete = fechaDestete;
            _alzada = alzada;
            _peso = peso;
            _sexo = sexo;
            _tipo = tipo;
        }


        #endregion
    }
}
