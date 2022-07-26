package restServiceOne.BuisnesLogic;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import restServiceOne.hibernate.entity.CurrencyEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CurrencyLogic {

//    public List<CurrencyEntity> getCurrenciesFromWeb(){
//
//        List<CurrencyEntity> result = new ArrayList<>();
//
//        WebClient client = WebClient.builder()
//                .baseUrl("https://cdn.cur.su/api/cbr.json")
//                .defaultCookie("cookieKey", "cookieValue")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
//                .build();
//
//        Map obj = client.get().retrieve().bodyToMono(Map.class).block();
//
//
//
//        return result;
//    }

}
