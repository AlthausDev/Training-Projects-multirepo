using CsvHelper.Configuration;
using System;
using System.Globalization;
using System.Text;

namespace VITP._12_Ommnisiah.Utils
{
    internal class CsvConfig
    {
        private static readonly string File = "beers.csv";
        private static readonly string Path = AppDomain.CurrentDomain.BaseDirectory;
        private static readonly string PathFile = System.IO.Path.Combine(Path, "Data", File);

        public static CsvConfiguration Configuration { get; }

        private static CsvConfiguration CreateCsvConfiguration()
        {
            var configuration = new CsvConfiguration(CultureInfo.CurrentCulture)
            {
                Delimiter = ",",
                Encoding = Encoding.UTF8
            };

            return configuration;
        }
    }
}
