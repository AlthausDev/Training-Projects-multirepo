using CsvHelper.Configuration;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VacasWPF
{
    public static class LogicaNegocio
    {
        private static string nombreArchivo = "vacas.csv";
        private static string ruta = AppDomain.CurrentDomain.BaseDirectory;
        public static string RutaArchivo = 
            System.IO.Path.Combine(ruta, "Data", nombreArchivo);
        /// <summary>
        /// Configuración del CSV
        /// </summary>
        public static CsvConfiguration CsvConfig =
            new CsvConfiguration(CultureInfo.CurrentCulture)
            { Delimiter = ";", Encoding = Encoding.UTF8 };
    }
}
