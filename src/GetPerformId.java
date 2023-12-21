import java.net.URI;
import java.net.http.HttpClient;

public class GetPerformId {
    public String getPerformIdFromUri(String performUri) {
        try {
            HttpClient getPerformIdRequest = HttpClient.newBuilder()
                    .uri(URI.create("https://api.allticket.com/content/get-home"))
                    .

        }
    }
}
