
using StringTest;

TestIsLength4Test();
TestContainsH();

Console.WriteLine("Las pruebas se realizaron de manera exitosa");

static void TestIsLength4Test()
{

    //Arrange
    String cadena = "Hola";

    //Act
    int cadenaLength = cadena.Length;

    //Assert
    Assert.That(!cadenaLength.Equals(4), "Prueba de longitud fallida");    

}


static void TestContainsH()
{

    //Arrange
    String cadena = "Holav";

    //Act
    bool cadenaContainsH = cadena.ToUpper().Contains("H");

    //Assert
    Assert.That(!cadenaContainsH, "Prueba de patron fallido");

}

