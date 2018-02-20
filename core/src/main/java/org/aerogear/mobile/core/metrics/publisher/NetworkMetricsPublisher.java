package org.aerogear.mobile.core.metrics.publisher;

import android.content.Context;

import org.aerogear.mobile.core.MobileCore;
import org.aerogear.mobile.core.http.HttpRequest;
import org.aerogear.mobile.core.http.HttpResponse;
import org.aerogear.mobile.core.metrics.Metrics;
import org.aerogear.mobile.core.metrics.MetricsPublisher;
import org.aerogear.mobile.core.utils.ClientIdGenerator;
import org.json.JSONException;
import org.json.JSONObject;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Sends metrics data to the backend using the configuration in JSON config file
 */
public class NetworkMetricsPublisher implements MetricsPublisher {

    private Context context;
    private HttpRequest httpRequest;
    private String url;

    public NetworkMetricsPublisher(Context context, HttpRequest httpRequest, String url) {
        this.context = context;
        this.httpRequest = httpRequest;
        this.url = url;
    }

    @Override
    public void publish(Metrics... metrics) {

        try {

            JSONObject json = new JSONObject();

            json.put("clientId", ClientIdGenerator.getOrCreateClientId(context));
            json.put("timestamp", System.currentTimeMillis());

            JSONObject data = new JSONObject();
            for (final Metrics m : metrics) {
                data.put(m.identifier(), new JSONObject(m.data()));
            }

            json.put("data", data);

            httpRequest.post(url, json.toString().getBytes());

            MobileCore.getLogger().debug("Sending metrics");
            HttpResponse httpResponse = httpRequest.execute();
            httpResponse.onComplete(() -> {
                if (httpResponse.getStatus() == HTTP_OK) {
                    MobileCore.getLogger().debug("Metrics sent", json.toString());
                } else {
                    MobileCore.getLogger().error(httpResponse.getRequestError().getMessage(),
                        httpResponse.getRequestError());
                }
            });

        } catch (JSONException e) {
            MobileCore.getLogger().error(e.getMessage(), e);
        }

    }

}
