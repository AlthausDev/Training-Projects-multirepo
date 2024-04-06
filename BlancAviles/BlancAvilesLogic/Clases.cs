namespace BlancAvilesLogic
{
    public class Cliente
    {
        public string Nombre { get; set; }
        public string Apellidos { get; set; }

        public Cliente()
        {
        }

        public Cliente(string nombre, string apellidos)
        {
            Nombre = nombre;
            Apellidos = apellidos;
        }
    }

    public class Cuenta
    {
        public Cliente Cliente { get; set; }
        public Double Saldo { get; set; }

        public bool IsSaldoValido() { return Saldo >= -100;  }

        public Cuenta(Cliente cliente)
        {
            this.Cliente = cliente;
            this.Saldo = 0;
        }

        public void ingresar(double cantidad)
        {
            Saldo += cantidad;
        }

        public void extraer(double cantidad)
        {
            Saldo -= cantidad;
        }
    }

}
