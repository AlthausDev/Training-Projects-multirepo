using CsvHelper.Configuration.Attributes;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VITP._12_Ommnisiah.Model
{
    public class Beer : IEditableObject, INotifyPropertyChanged
    {
        [Key]
        [Index(4)] // Id
        public long Id { get; set; }

        [Index(2)] // Abv
        public string Abv { get; set; }

        [Index(5)] // Name
        public string Name { get; set; }

        [Index(6)] // Style
        public string Style { get; set; }

        [Index(11)] // Brewer
        public string Brewer { get; set; }

        [Index(12)] // City
        public string City { get; set; }

        [Index(13)] // State
        public string State { get; set; }

        [Index(14)] // Label
        public string Label { get; set; }


        private Beer backupCopy;
        private bool inEdit;

        public Beer()
        {
        }

        public Beer(int id, string abv, string name, string style, string brewer, string city, string state, string label)
        {
            Id = id;
            Abv = abv;
            Name = name;
            Style = style;
            Brewer = brewer;
            City = city;
            State = state;
            Label = label;
        }

        public event PropertyChangedEventHandler? PropertyChanged;

        public void BeginEdit()
        {
            if (!inEdit)
            {
                // Crear una copia de seguridad del objeto actual
                backupCopy = this.MemberwiseClone() as Beer;
                inEdit = true;
            }
        }

        public void CancelEdit()
        {
            if (inEdit)
            {
                // Restaurar el objeto desde la copia de seguridad
                if (backupCopy != null)
                {
                    this.Id = backupCopy.Id;
                    this.Abv = backupCopy.Abv;
                    this.Name = backupCopy.Name;
                    this.Style = backupCopy.Style;
                    this.Brewer = backupCopy.Brewer;
                    this.City = backupCopy.City;
                    this.State = backupCopy.State;
                    this.Label = backupCopy.Label;
                }
                inEdit = false;
            }
        }

        public void EndEdit()
        {
            if (inEdit)
            {
                // Eliminar la copia de seguridad ya que se ha confirmado la edición
                backupCopy = null;
                inEdit = false;
            }
        }
    }
}
