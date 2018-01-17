package org.aerogear.mobile.core.di.usage;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created to test DI
 */
public class TopLevelService {

    private final OkHttpClient okHttpClient;

    @Inject
    public TopLevelService(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    // TODO injectAppExecutors (create appExecutors module)
    protected void fetchJson()  {
        Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();
        try {
            try (Response response = okHttpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

                System.out.println(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
