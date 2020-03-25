package com.spring.cov19.clients;

import org.springframework.stereotype.Component;

import feign.Feign;
import feign.Logger;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.Getter;

@Getter
@Component
public class Cov19Client {
	private Cov19DataClient covClient = createClient(Cov19DataClient.class, "https://raw.githubusercontent.com/");
	
	private static <T> T createClient(Class<T> type, String uri) {
        return Feign.builder()
            .client(new OkHttpClient())
//            .encoder(new GsonEncoder())
//            .decoder(new GsonDecoder())
            .logger(new Slf4jLogger(type))
            .logLevel(Logger.Level.FULL)
            .target(type, uri);
    }
}
