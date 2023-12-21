import Model.GetHome.DataItem;
import Model.GetHome.GetHomeResponse;
import Model.GetHome.HomeData;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class GetPerformId {
    HttpResponse<String> getPerformIdResponse;
    GetHomeResponse performIdResponse;
    Gson gson = new Gson();
    public String getPerformIdFromUri(String performUri) {

            HttpRequest getPerformIdRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.allticket.com/content/get-home"))
                    .POST(HttpRequest.BodyPublishers.ofString("{\"cached\":true,\"version\":\"V2\"}"))
                    .build();

            try {
                getPerformIdResponse = HttpClient.newHttpClient().send(getPerformIdRequest, HttpResponse.BodyHandlers.ofString());
                performIdResponse = gson.fromJson(getPerformIdResponse.body(), GetHomeResponse.class);

                for (HomeData data : performIdResponse.getData()) {
                    for (DataItem item : data.getItems()) {
                        if (item.getPerformUri().equalsIgnoreCase(performUri)) {
                            return item.getId();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
