/**
 *
 *  @author Domariev Vladyslav S19314
 *
 */

package zad1;

import zad1.Service;

public class Main {
  public static void main(String[] args) {
    Service s = new Service("Poland");
    String weatherJson = s.getWeather("Warsaw");
    Double rate1 = s.getRateFor("USD");
    Double rate2 = s.getNBPRate();
    // ...
    s.showWiki();
  
    System.out.println(weatherJson);
    System.out.println(rate1);
    System.out.println(rate2);
  }
}
