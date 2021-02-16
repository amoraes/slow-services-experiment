package com.github.amoraes.microservicehystrixthread;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class SlowServiceClient {

    private static final String URL = "http://localhost:8001/";

    private final CloseableHttpAsyncClient asyncClient;

    public SlowServiceClient(CloseableHttpAsyncClient asyncClient) {
        this.asyncClient = asyncClient;
        this.asyncClient.start();
    }

    public Mono<String> hello() {
        return Mono.fromCompletionStage(sendRequestWithApacheHttpClient());
    }

    private CompletableFuture<String> sendRequestWithApacheHttpClient() {
        CompletableFuture<org.apache.http.HttpResponse> cf = new CompletableFuture<>();
        FutureCallback<org.apache.http.HttpResponse> callback = new HttpResponseCallback(cf);
        HttpUriRequest request = new HttpGet(URL);
        asyncClient.execute(request, callback);
        return cf.thenApply(response -> {
            try {
                return EntityUtils.toString(response.getEntity());
            } catch (ParseException | IOException e) {
                throw new RuntimeException("error!", e);
            }
        });
    }

}



class HttpResponseCallback implements FutureCallback<org.apache.http.HttpResponse> {

    private CompletableFuture<org.apache.http.HttpResponse> cf;

    HttpResponseCallback(CompletableFuture<org.apache.http.HttpResponse> cf) {
        this.cf = cf;
    }

    @Override
    public void failed(Exception ex) {
        cf.completeExceptionally(ex);
    }

    @Override
    public void completed(org.apache.http.HttpResponse result) {
        cf.complete(result);
    }

    @Override
    public void cancelled() {
        cf.completeExceptionally(new Exception("Cancelled by http async client"));
    }
}
