using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TesWPFtApp
{
    public class Persona
    {
        public int Id { get; set; }
        public string Name { get; set; }

        public Persona(int id, string name)
        {
            Id = id;
            Name = name;
        }
    }
}
