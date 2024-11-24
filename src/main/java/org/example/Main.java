package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner p = new Scanner(System.in);
        System.out.println("-----------------Elige la moneda de conversión-----------------");
        String dato = p.nextLine();

        String direcc = "https://v6.exchangerate-api.com/v6/a94369d46416e52d5cdab07a/latest/"+dato ;


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request= HttpRequest.newBuilder()
                .uri(URI.create(direcc))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        

    }




}