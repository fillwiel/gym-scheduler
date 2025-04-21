package com.wielkopolan.gymscheduler.service;

import com.wielkopolan.gymscheduler.entity.ScheduledTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class RequestSenderService {

    private final WebClient client;
    private final String authCookieToken;

    public RequestSenderService(WebClient.Builder builder,
                                @Value("${auth.cookie.token}") String authCookieToken) {
        this.client = builder.baseUrl("https://atmosfera-lodz.cms.efitness.com.pl").build();
        this.authCookieToken = authCookieToken;
    }

    public boolean sendPostRequest(ScheduledTask task) {
        try {
            client.post()
                    .uri("/Schedule/RegisterForClass")
                    .header("Accept", "*/*")
                    .header("Accept-Language", "pl,en-US;q=0.9,en;q=0.8,pt-PT;q=0.7,pt;q=0.6")
                    .header("Connection", "keep-alive")
                    .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .header("Cookie", "language=pl-PL; .ASPXAUTH_Cms=" + authCookieToken)
                    .header("Origin", "https://atmosfera-lodz.cms.efitness.com.pl")
                    .bodyValue("id=" + task.getId() + "&memberID=" + task.getMemberId() + "&promoCodeID=&promoCode=&g-recaptcha-response=")
                    .retrieve()
                    .bodyToMono(String.class)
                    .doOnNext(body -> log.info("Response: {}", body)) // Log the response body
                    .onErrorResume(e -> {
                        log.error("Error during request: {}", e.getMessage());
                        return Mono.empty();
                    })
                    .block();

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}