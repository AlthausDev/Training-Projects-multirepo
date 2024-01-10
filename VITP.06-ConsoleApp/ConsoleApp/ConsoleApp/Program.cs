using System.Text;

internal class Program
{
    private static void Main(string[] args)
    {
        var limit = 3;
        int[] source = {1, 2, 3, 4, 5};

        var query = from item in source
                    where item < limit
                    select item;

        source.Where(x => x < limit).ToArray();
        int[] newArray = query.ToArray();

        String saludo = string.Empty;
        saludo += "Hello";
        saludo += " World!";

        Console.WriteLine(saludo);


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.AppendLine("Hello");
        stringBuilder.AppendLine("Wordl!");

        Console.WriteLine("Hello, World!");
    }
}