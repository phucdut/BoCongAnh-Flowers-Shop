package com.example.customer.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Value("${app.firebase-configuration-file}")
    private String configPath;

    @Bean
    FirebaseMessaging firebaseMessaging() throws IOException {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(configPath).getInputStream())).build();
        FirebaseApp app = FirebaseApp.initializeApp(options, "my-app");
        return FirebaseMessaging.getInstance(app);
    }

}
