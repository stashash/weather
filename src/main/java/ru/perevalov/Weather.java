package ru.perevalov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.DefaultHttpClient;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
/**
 * 4f29b811ca85de9f879367dfcd55621e
 * **/
public class Weather {
    public static void main(String[] arg) throws IOException, JAXBException, URISyntaxException {
        String city = "Ulan-Ude";
        Scanner in = new Scanner(System.in);
        System.out.print("Введите город:(Moscow/Sankt-Peterburg/Tokio) ");
        city = in.nextLine();
        HttpClient client = new DefaultHttpClient();
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="+
                city+
                "" +
                "&appid=4f29b811ca85de9f879367dfcd55621e" +
                "&mode=xml" +
                "&labg=ru");
        HttpGet request = new HttpGet(url.toURI());

        JAXBContext jaxbContext = JAXBContext.newInstance(Current.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Current current = (Current) jaxbUnmarshaller.unmarshal(url);
        System.out.println(current);

        System.out.println("Город - "+ current.city.name);
        System.out.println("Температура - "+current.temperature.value.subtract(new BigDecimal(273)));


    }
}
