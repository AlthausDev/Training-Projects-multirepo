using CsvHelper;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VacasWPF.Models
{
    public class AppVacasDBContext : DbContext
    {
        public ObservableCollection<Vaca> Vacas { get; set; }

        public AppVacasDBContext() {
            Vacas = new ObservableCollection<Vaca>();
        }
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            // Usa InMemory como el proveedor de base de datos
            optionsBuilder.UseInMemoryDatabase("InMemoryDatabase");
        }

        public override int SaveChanges()
        {
            int retValue = base.SaveChanges();
            saveToCsv();
            return retValue;
        }

        public override int SaveChanges(bool acceptAllChangesOnSuccess)
        {
            int retValue =  base.SaveChanges(acceptAllChangesOnSuccess);
            saveToCsv();
            return retValue;
        }

        private void saveToCsv()
        {
            using (var writer = new StreamWriter(LogicaNegocio.RutaArchivo))
            using (var csvWriter = new CsvWriter(writer, LogicaNegocio.CsvConfig))
            {
                csvWriter.WriteRecords(this.Vacas.ToList());
            }
        }
    }
}
