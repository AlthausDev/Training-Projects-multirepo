using CsvHelper;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VITP._10_VacasWPF.Model
{
    public class AppDBContext: DbContext
    {
        public ObservableCollection<Vaca> ListVacas { get; set; }

        public AppDBContext()
        {
            ListVacas = new ObservableCollection<Vaca>();
        }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseInMemoryDatabase("InMemoryDatabase");
        }

        public override int SaveChanges()
        {
            int retValue = base.SaveChanges();
            SaveToCsv();
            return retValue;
        }

        private void SaveToCsv()
        {
            using (var writer = new StreamWriter(Utils.CsvConfig.PathFile))
            using (var csvWriter = new CsvWriter(writer, Utils.CsvConfig.Config))
            {
                csvWriter.WriteRecords(this.ListVacas.ToList());
            }
        }
    }
}
