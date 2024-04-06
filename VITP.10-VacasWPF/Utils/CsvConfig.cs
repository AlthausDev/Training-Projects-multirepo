using CsvHelper.Configuration;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VacasWPF.Utils
{
    public static class CsvConfig
    {
        private static string NombreArchivo = "vacas.csv";
        private static string Path = AppDomain.CurrentDomain.BaseDirectory;
        public static string PathFile = System.IO.Path.Combine(Path, "Data", NombreArchivo);

        public static CsvConfiguration Config =
            new CsvConfiguration(CultureInfo.CurrentCulture)
            {
                Delimiter = ";",
                Encoding = Encoding.UTF8
            };

    }
}
