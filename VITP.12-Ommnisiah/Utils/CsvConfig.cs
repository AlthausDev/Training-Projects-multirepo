using CsvHelper;
using CsvHelper.Configuration;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Text;
using VITP._12_Ommnisiah.Model;

namespace VITP._12_Ommnisiah.Utils
{
    public class CsvConfig
    {
        private static readonly string File = "beers.csv";
        private static readonly string Path = AppDomain.CurrentDomain.BaseDirectory;
        private static readonly string PathFile = System.IO.Path.Combine(Path, "Data", File);

        public static CsvConfiguration Configuration { get; }

        static CsvConfig()
        {
            Configuration = new CsvConfiguration(CultureInfo.CurrentCulture)
            {
                Delimiter = ",",
                Encoding = Encoding.UTF8,
                HasHeaderRecord = true // Indica si el archivo CSV tiene un encabezado
            };
        }

        public static List<Beer> ReadBeersFromCsv()
        {
            using (var reader = new StreamReader(PathFile, Encoding.UTF8))
            using (var csv = new CsvReader(reader, Configuration))
            {
                return csv.GetRecords<Beer>().ToList();
            }
        }
    }
}
