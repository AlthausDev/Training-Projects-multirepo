namespace StringTestUnit
{
    public class Tests
    {
        [SetUp]
        public void Setup()
        {
        }

        [Test]
        public void TestIsLength4Test()
        {

            //Arrange
            String cadena = "Hola";

            //Act
            int cadenaLength = cadena.Length;

            //Assert
            Assert.That(!cadenaLength.Equals(4), "Prueba de longitud fallida");

        }

        [Test]
        public void TestContainsH()
        {

            //Arrange
            String cadena = "Holav";

            //Act
            bool cadenaContainsH = cadena.ToUpper().Contains("H");

            //Assert
            Assert.That(!cadenaContainsH, "Prueba de patron fallido");

        }

        [Test]
        public void TestFormat()
        {
            //Arrange
            String cadena = "Holav";

            //Act
            bool cadenaContainsH = cadena.ToUpper().Contains("H");

            //Assert
            Assert.That(!cadena.Length.Equals(4), "Prueba de longitud fallida");
            Assert.That(!cadenaContainsH, "Prueba de patron fallido");
        }
    }
}