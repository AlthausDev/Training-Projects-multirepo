using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StringTest
{
    public class Assert
    {
        public static void That(bool condition)
        {
            That(condition, "Prueba Fallida");
        }

        public static void That(bool condition, string errorMessage)
        {
            if (condition)
            {
                throw new Exception(errorMessage);
            }
        }
    }
}
