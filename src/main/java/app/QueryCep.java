package app;

import enums.ResponseFormat;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class QueryCep {

    private final HttpClient client;

    public QueryCep(HttpClient client) {
        this.client = client;
    }

    public HttpResponse<String> searchAddress (String cep) throws IOException, InterruptedException {

        URI uri = URI.create(("http://viacep.com.br/ws/" + cep + "/json"));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();

       return client.send(request, HttpResponse.BodyHandlers.ofString());

    }

}
