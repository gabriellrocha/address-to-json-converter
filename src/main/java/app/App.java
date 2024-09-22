package app;

import dtos.AddressDTO;
import exceptions.CepInvalidException;
import utils.CepUtil;
import utils.JsonUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private static boolean running;

    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        QueryCep query = new QueryCep(HttpClient.newBuilder().build());
        List<AddressDTO> list = new ArrayList<>();

        do {
            System.out.println("\nEnter the CEP: ");
            String stringCep = scanner.nextLine();

            try {
                if (CepUtil.isValidCep(stringCep)) {
                    HttpResponse<String> response = query.searchAddress(stringCep);

                    if (response.body().contains("erro")) {
                        throw new CepInvalidException("\nCEP not found in the database\n");
                    }

                    list.add(JsonUtil.deserialize(response.body()));
                    System.out.println("\nSearch completed successfully");
                }

                System.out.println("Press enter to continue or type \"EXIT\" to finish: ");

                if (scanner.nextLine().toUpperCase().trim().equalsIgnoreCase("EXIT")) {
                    App.running = true;
                }


            } catch (CepInvalidException e) {
                System.out.println(e.getMessage());
            }

        } while (!running);


        String path = System.getProperty("user.home") + "\\list.json";

        try {
            JsonUtil.serializeListForJson(list, new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8));
            System.out.println(".json file successfully created in " + path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
