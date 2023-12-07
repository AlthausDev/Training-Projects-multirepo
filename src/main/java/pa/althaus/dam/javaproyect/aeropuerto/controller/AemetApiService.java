package pa.althaus.dam.javaproyect.aeropuerto.controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import pa.althaus.dam.javaproyect.aeropuerto.model.TemperatureInfo;

import java.time.LocalDate;

public class AemetApiService {

    private static final String API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYW11ZWxhbHRoYXVzQGdtYWlsLmNvbSIsImp0aSI6IjNiMjFlNDhhLWIxYjYtNGViNy1hMmRlLWJlNzM3OGI4OGIzZiIsImlzcyI6IkFFTUVUIiwiaWF0IjoxNzAxOTM0OTg4LCJ1c2VySWQiOiIzYjIxZTQ4YS1iMWI2LTRlYjctYTJkZS1iZTczNzhiODhiM2YiLCJyb2xlIjoiIn0.cjwZNey5Ey90jtNlt5uFH64hzQUx3QRfmTth6ktmfWY";
    private static final String URL_AEMET = "https://opendata.aemet.es/opendata/api/prediccion/especifica/municipio/diaria/{municipio}";

    public static TemperatureInfo getTemperatureInfo(String airportCode, String codigoMunicipio) {
        try {
            String apiUrl = URL_AEMET.replace("{municipio}", codigoMunicipio);

            HttpResponse<JsonNode> jsonResponse = Unirest.get(apiUrl)
                    .header("accept", "application/json")
                    .queryString("api_key", API_KEY)
                    .asJson();

            int statusCode = jsonResponse.getStatus();

            if (statusCode == 200) {
                JSONObject temperaturaObj = jsonResponse.getBody().getObject().getJSONObject("temperatura");

                if (temperaturaObj != null && temperaturaObj.has("minima") && temperaturaObj.has("maxima")) {
                    double minTemperature = temperaturaObj.getDouble("minima");
                    double maxTemperature = temperaturaObj.getDouble("maxima");

                    return new TemperatureInfo(airportCode, codigoMunicipio, minTemperature, maxTemperature);
                } else {
                    System.out.println("La estructura de la respuesta no es la esperada.");
                    return null;
                }
            } else {
                System.out.println("Error en la solicitud: " + statusCode);
                return null;
            }
        } catch (UnirestException e) {
            System.out.println("Error en la solicitud: " + e.getMessage());
            return null;
        }
    }
}
