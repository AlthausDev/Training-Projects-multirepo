using BlancAvilesLogic;

namespace BlancAvilesTest
{
    public class Tests
    {
        private Cuenta cuenta;


        [SetUp]
        public void Setup()
        {
            cuenta = new Cuenta(new Cliente("Paco", "Doe"));
        }

        [Test]
        public void TestSaldo()
        {
            Assert.That(cuenta.Saldo, Is.EqualTo(0));

        }

        [Test]
        public void TestIncrementoSaldo()
        {
            cuenta.ingresar(100);
            Assert.That(cuenta.Saldo, Is.EqualTo(100));
        }

        [Test]
        public void TestDecrementoSaldo()
        {
            cuenta.extraer(100);
            Assert.That(cuenta.Saldo, Is.EqualTo(-100));
        }

        [Test]
        public void TestSaldoValido()
        {
            cuenta.extraer(100);
            Assert.That(cuenta.IsSaldoValido(), Is.True);
        }

        [Test]
        public void TestSaldoNoValido()
        {
            cuenta.extraer(150);
            Assert.That(cuenta.IsSaldoValido(), Is.False);
        }
    }
}