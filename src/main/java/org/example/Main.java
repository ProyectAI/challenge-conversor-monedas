package org.example;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        // Solicitar la moneda base
        System.out.println("----------------- Elige la moneda de conversión -----------------");
        String baseCurrency = scanner.nextLine().toUpperCase();

        // Solicitar la moneda a consultar
        System.out.println("----------------- Elige la moneda destino -----------------");
        String targetCurrency = scanner.nextLine().toUpperCase();

        // Solicitar el monto a convertir
        System.out.println("----------------- Ingresa el monto a convertir -----------------");
        double amount = scanner.nextDouble();

        // Construir la URL
        String apiUrl = "https://v6.exchangerate-api.com/v6/a94369d46416e52d5cdab07a/latest/" + baseCurrency;

        // Crear cliente HTTP
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        // Hacer la solicitud HTTP
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Obtener el JSON de respuesta
        String json = response.body();
        System.out.println("Respuesta JSON:\n" + json);

        // Deserializar el JSON
        Gson gson = new Gson();
        Monedas monedas = gson.fromJson(json, Monedas.class);

        // Verificar si la moneda destino existe
        Double conversionRate = monedas.getConversionRates().get(targetCurrency);

        if (conversionRate != null) {
            double convertedAmount = amount * conversionRate;
            System.out.println("El monto convertido de " + amount + " " + baseCurrency + " a " + targetCurrency + " es: " + convertedAmount);
        } else {
            System.out.println("La moneda " + targetCurrency + " no está disponible.");
        }
    }
}
