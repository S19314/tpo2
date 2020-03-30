/**
 *
 *  @author Domariev Vladyslav S19314
 *
 */

package zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;



import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Service {
//    private String country,
//                   shortNameCountry;
    private Locale country;
    public Service(String countryName){
        this.country = findCountry(countryName); 
      //  shortNameCountry = findShortCountryName(country);
        
// При вводе ссылки проверка на пустоту. Если аргумент пустой, то 
        // тип ссылки будет не с 2 параметрами(Короткое название страны и город),
        // а просто город.
    }
    
    public String getWeather(String city){
        // url = api.openweathermap.org/data/2.5/weather?q={city name},{state}&appid={your api key}
        // My api key = c6d06fff429f9491ba9c77fad9baba72
        String protocol = "https://";
        String linkWeatherApi = protocol + "api.openweathermap.org/data/2.5/weather?q=" +
                city + "," +
                country.getCountry().toLowerCase() +
                "&appid=c6d06fff429f9491ba9c77fad9baba72";
        
          return  getDataByLink(linkWeatherApi);
//        return null; // JSON
    }
    
    public Double getRateFor(String walut){        
        // "https://api.exchangeratesapi.io/latest?base=PLN&symbols=THB");
        Currency currency = Currency.getInstance(country);
        String link ="https://api.exchangeratesapi.io/latest?base="+
                      currency.getSymbol() + 
                      "&symbols=" + walut;
        String result = getDataByLink(link);

        JSONParser parser = new JSONParser();
        JSONObject jsonObj = new JSONObject();;
        
        Object parseredObject  = null;
        try {
        	parseredObject = parser.parse(result);

        }catch(org.json.simple.parser.ParseException pse) {
        	pse.printStackTrace();
        }
        
        jsonObj = (JSONObject)parseredObject;
        JSONObject rateObj = (JSONObject)jsonObj.get("rates");
    	   return (Double)rateObj.get(walut);
    }
    
    private String getDataByLink(String link){
        String data = "";
        try{
        URL url = new URL(link);
        try (BufferedReader in = new BufferedReader(
                     new InputStreamReader(url.openStream(), "UTF-8"))) {
        String line;
        while((line = in.readLine()) != null) data +=line;
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        
        }catch(Exception exc){
            exc.printStackTrace();
        }
        
        return data;
    }
    
    public Double getNBPRate(){
        Currency currency = Currency.getInstance(country);
        String link ="https://api.exchangeratesapi.io/latest?base="+
        			   currency.getSymbol() + 
                      "&symbols=" +"PLN";//;
        String result = getDataByLink(link);

        JSONParser parser = new JSONParser();
        JSONObject jsonObj = new JSONObject();;
        
        Object parseredObject  = null;
        try {
        	parseredObject = parser.parse(result);

        }catch(org.json.simple.parser.ParseException pse) {
        	pse.printStackTrace();
        }
        
        jsonObj = (JSONObject)parseredObject;
        JSONObject rateObj = (JSONObject)jsonObj.get("rates");
    	   return (Double)rateObj.get(currency.getSymbol());

    }

    
    private Locale findCountry(String country){
        Locale.setDefault(Locale.ENGLISH);
        Locale[] locales = Locale.getAvailableLocales();
        
        for(int i = 0; i < locales.length; i++ ){
            String shortCountryName = locales[i].getDisplayCountry();
            if(shortCountryName.equals(country))
                return locales[i];
        }
        return null;
    }
    
    public void showWiki(){
        HtmlViewer.main(new String[]{country.getDisplayCountry()});
    }
}  
