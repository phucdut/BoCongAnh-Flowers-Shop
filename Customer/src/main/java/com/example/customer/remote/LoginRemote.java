package com.example.customer.remote;


import com.example.customer.responseBody.CustomerResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class LoginRemote {

    public HttpHeaders sendPostRequestLogin(String username, String password) {
        String url = "http://localhost:80/perform_login";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("username", username)
                .queryParam("password", password);
        // Gửi yêu cầu POST
        ResponseEntity<CustomerResponse> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                null,
                CustomerResponse.class
        );
        return responseEntity.getHeaders();
    }
}
