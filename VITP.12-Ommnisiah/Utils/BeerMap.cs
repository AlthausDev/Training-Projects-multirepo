using CsvHelper.Configuration;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using VITP._12_Ommnisiah.Model;

namespace VITP._12_Ommnisiah.Utils
{
    public sealed class BeerMap : ClassMap<Beer>
    {
        public BeerMap()
        {
            Map(m => m.Id).Name("id");
            Map(m => m.Abv).Name("abv");
            Map(m => m.Name).Name("beer");
            Map(m => m.Style).Name("style");
            Map(m => m.Brewer).Name("brewery");
            Map(m => m.City).Name("city");
            Map(m => m.State).Name("state");
            Map(m => m.Label).Name("label");
        }
    }

}
