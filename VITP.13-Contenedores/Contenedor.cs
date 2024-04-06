using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Examen5AAS
{
    /**
         * Inicializa un contenedor con todos sus datos
         * @param referencia
         * @param peso
         * @param volumen
         * @param tipoContenido 
         */


    /// <summary>
    /// Clase para registro de contenedores para examen de Desarrollo de Interfaces Distancia
    /// </summary>
    public class Contenedor
    {



        /// <summary>
        /// Inicializa un contenedor con todos sus datos
        /// </summary>
        /// <param name="referencia"></param>
        /// <param name="peso"></param>
        /// <param name="volumen"></param>
        /// <param name="tipoContenido"></param>        
        public Contenedor(String referencia, int peso, int volumen, String tipoContenido)
        {
            this.Referencia = referencia;
            this.Peso = peso;
            this.Volumen = volumen;
            this.TipoContenido = tipoContenido;
        }

        /// <summary>
        /// Referencia del contenedor
        /// </summary>
        public string Referencia { get; set; }

        /// <summary>
        /// Peso del contenedor
        /// </summary>
        public int Peso { get; set; }

        /// <summary>
        /// Volumen del contenedor
        /// </summary>
        public int Volumen { get; set; }

        /// <summary>
        /// Tipo de contenido
        /// </summary>
        public string TipoContenido { get; set; }
    }
}
