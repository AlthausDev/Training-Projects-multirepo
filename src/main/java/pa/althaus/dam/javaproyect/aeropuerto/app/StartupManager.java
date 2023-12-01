package pa.althaus.dam.javaproyect.aeropuerto.app;

import pa.althaus.dam.javaproyect.aeropuerto.model.AirlineCompany;
import pa.althaus.dam.javaproyect.aeropuerto.model.Airport;
import pa.althaus.dam.javaproyect.aeropuerto.model.DailyFlight;
import pa.althaus.dam.javaproyect.aeropuerto.model.Flight;
import pa.althaus.dam.javaproyect.aeropuerto.util.reader.CsvReader;

import java.util.HashMap;

public class StartupManager {

    private static final StartupManager INSTANCE = new StartupManager();
    private static HashMap<String, Airport> airportHashMap = new HashMap<>();
    private static HashMap<String, AirlineCompany> airlineCompanyHashMap = new HashMap<>();
    private static HashMap<String, Flight> flightHashMap = new HashMap<>();
    private static HashMap<String, DailyFlight> dailyFlightHashMap = new HashMap<>();
    private static HashMap<String, String> municipiosHashMap = new HashMap<>();


    private StartupManager() {
        init();
    }

    private void init() {
        CsvReader csvReader = new CsvReader();
        municipiosHashMap = csvReader.readMunicipiosCSV();
    }


    protected static StartupManager getInstance() {
        return INSTANCE;
    }
}
