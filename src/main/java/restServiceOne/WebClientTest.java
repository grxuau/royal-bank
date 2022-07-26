package restServiceOne;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import javax.swing.plaf.IconUIResource;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class WebClientTest {

    public static void main(String[] args) {

        WebClient client = WebClient.builder()
                .baseUrl("https://cdn.cur.su/api/cbr.json")
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
                .build();

        Map<String,Object> response = client.get().retrieve().bodyToMono(Map.class).block();

        Map<String,Double> currencyPairs = new LinkedHashMap<>();
        Timestamp dateTime;
        for (Map.Entry<String,Object> responsePart:response.entrySet()) {
            if (responsePart.getKey().equals("rates")) {
                currencyPairs = (Map)responsePart.getValue();
            }
            if (responsePart.getKey().equals("putISODate")) {
             try {
                    String dateString = (String) responsePart.getValue();
                    dateTime = Timestamp.from(Instant.parse(dateString));

                } catch(Exception e) {

                }
            }
        }
    }
}
