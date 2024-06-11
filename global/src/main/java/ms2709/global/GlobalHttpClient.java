package ms2709.global;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class GlobalHttpClient
 * @since 2024-05-20 오후 10:01
 */
@Component
public class GlobalHttpClient {
    private final HttpClient httpClient;
    public GlobalHttpClient() {
        this.httpClient = HttpClient.newBuilder().build();
    }

    public HttpResponse<String> sendGetRequest(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(java.net.URI.create(url))
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> sendPostRequest(String url, String body) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .uri(java.net.URI.create(url))
                .header("Content-Type", "application/json")
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public CompletableFuture<HttpResponse<String>> sendPostRequestAsync(String url, String body){
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .uri(java.net.URI.create(url))
                .header("Content-Type", "application/json")
                .build();
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }
}
