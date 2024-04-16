using BlazorWebPage.Shared;

namespace BlazorWebPage.Server.Interfaces
{
    public interface IWeatherForecastService
    {

        public WeatherForecast[] getAll();
    }
}
