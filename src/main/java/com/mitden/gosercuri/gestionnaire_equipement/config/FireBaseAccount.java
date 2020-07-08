package com.mitden.gosercuri.gestionnaire_equipement.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mitden.gosercuri.gestionnaire_equipement.util.exception.TechnicalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@Configuration
public class FireBaseAccount {
  String KEY_FILE ="ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAiZ29zZWN1cmktOGE4NTIiLAogICJwcml2YXRlX2tleV9pZCI6ICI3MmZiYTc4ODRiNDVjMWIzYjY4MzEyMDMxZmM2MTkxNTRmZDA3Y2FjIiwKICAicHJpdmF0ZV9rZXkiOiAiLS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tXG5NSUlFdlFJQkFEQU5CZ2txaGtpRzl3MEJBUUVGQUFTQ0JLY3dnZ1NqQWdFQUFvSUJBUUQzR1RFWGZaREltd3VZXG4xaWZ1Z1dPSldXd2JRQ3g2YkI0M2FyNU8zb3VSVzJidU1UY0R6T2VETFZ4a0Mvdm5DYklNSGNjWU1reHJ0SzZrXG5iNEVycHZqYWlKN0J3TFE2d1hVRWtmUEM0dERPdGdzUXJjNGlNUmh3Y0VqdTRJRWIwSlVSQXNOTzBiYWhpeDBSXG5lMkJHOE1TeVpOeTZ5ODMyRWZISjVWYytGQ3BRU1FibFZwNVd6bVdNaGxIUzM3dkY5OHY0T3g1TEp0M0VVaVdlXG5RdWEyRUtpQ1R4bEtsK1JMYitnRjVGZU5oMVhNbDI4ZlRrd2hYcHRCOGpaTnU1QmM3WnBxR3VoOXFWSmsyakQ5XG5aUC93ZThON3ZQcnhwdC9vVHhBMHJ0cmx3MTUvN1FLT1lPSjJoTjcxdDlHbHo0Q1lhSWM5NDA5N0NVcWJVMVFIXG5qWlFEc1hSRkFnTUJBQUVDZ2dFQUtJeGpRSElVQ0w5Y3ExTjFVUSs5aUVQNjJBdnpZSkdPY2RTYjd6Qlk0ZDBJXG5hb3o3OUsweXpwVnFoR1p0QzNCTlhleWpCOGIxUUhWeGRUMXhDbnRIZWdFU1lFUWhyMFNnMGNPcnB5NzJTZjJZXG5taitUUkl3ZThSQ1QvMU1yREtmeFZrM0lSQTVvOUdCeVFEU3BTMnJtTkVvVjZMZGFFSWZJVURQSTJjQ1FZd1VPXG5SaWNWaHJzNnJRVkdtRjRla2xqZEtPVGpIbncwZkpxMHRWNGw2U1ZkeHpzRU9hbWpaUTNMbE9pb0M2TkVoM29HXG5SOWg4V1JSTWt1UnBVN1hwc042c0dGTGZUMHhBb3U1bGdVbG1PZzgwUW1GV1U1a3l2Q0U4SE5lVzM0WXo4Z2tGXG5EODRhVTQ4bUVmVVVhV2swMWl5MWZFZ0w3Mm1oanlRRm5OMVhzbS96MFFLQmdRRCtRZHVnSStzblJGdmtuS0ZNXG5tNXNJZ3FCc2diTzhTVXp2MU1ZSVhFcUZ5ZGJ1VVB3SVRzUGpFWi94M1RYZDR3bDZSVGY3Z2UxSGY4d0xmQVljXG54ZmlNVU5WM2R4NndPRFJzMFpLNS9JSmZVUXR5MVNyUVpPREFTQW5YSGZYWXl3SGFYMDRiWDZYZ2dQWEFkMGpuXG5Lay9ielZTeDFGaEhBdU85eGFjdXMxWjJqUUtCZ1FENHlzVzFuT2VRNERQMUZPYTYyZDdtVm9ic3ljTnNIRS9FXG54cDl2N2NyZjJsWGhHRmZRY3d0dk5kZHNkRGUxWVZ2VXZsQS96Y01CeGR6a3M1MW1Zalg3TmRsRjQ0QW1QeGhHXG40dXlIV3YvZXluL1A1OWRzbldubkZ2eXhEeFVCb3ZLSXJYSGZHb3lrRi9kd1U1WDFlNkM2dnZGZm1Ga1ZNU2tNXG51SDRWMEZXQ21RS0JnRGNZSERnYU1HVmsyYWl6Qy95bXB6Uno0Y0ZCWmFvQ0ZDclA1MDErZWlOOWRoWlQ5QmowXG53RnVld1JMT3hWSmNWUHhxclZQUmRwSU1yakU2NGJRd2JjZ3g1OStvTHowdUlLWUh6NHgvbTl4OW1rWjZ6SGsxXG5zc0hYbWxKRHlyYjhYbDFzVTJkNUZWK05jak1QWmZ1NVhtNlprcVdxQnFyMGhGYmwwbkU3S1J3dEFvR0FMSk00XG5CU2dOblRsdVZiVGUrZzliRG0zbVVycDdsdjBqbFovNHBQYzM2QStZWDNJYkdhL2pGcFBRK3c5L2c3SGo0Q1FSXG5BTDErK094WWZXbHBCQlVJa3UrODgvYWsyVFZveGhxV1FsSWRrZTg2SXlmb3d2OFpJRnhQL2ZFejFFMDdveDFnXG5UNWJVSi9wYlRQYk00V1M3RVhwd2lLNGxGOVR0QStoeVBycEs5OUVDZ1lFQXFBRDlqU09vaGN3Ym56bkREbzREXG42c3Y5c2hWSDRtQU9qMURsYTcwRzU1NXc5S0ZLT1NJQjQ4UUxUQWgzdTNoWHFkZ2Excmt6WncyeVV5d010RGZtXG42VDlnT2FMd2djUjZZbzgvUFR3OGFkUlBWSDE1MC9YbHBxdVpwOFp1Tms2UkZIQndxbjhnd2hqZnV2cjNpWmNhXG5UUnJTaDdzOEhLdmhzNnVnUmwxMzJMMD1cbi0tLS0tRU5EIFBSSVZBVEUgS0VZLS0tLS1cbiIsCiAgImNsaWVudF9lbWFpbCI6ICJmaXJlYmFzZS1hZG1pbnNkay0xd29xekBnb3NlY3VyaS04YTg1Mi5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsCiAgImNsaWVudF9pZCI6ICIxMDk4MDQwMDM0MTMyNjgzNjM4NDkiLAogICJhdXRoX3VyaSI6ICJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20vby9vYXV0aDIvYXV0aCIsCiAgInRva2VuX3VyaSI6ICJodHRwczovL29hdXRoMi5nb29nbGVhcGlzLmNvbS90b2tlbiIsCiAgImF1dGhfcHJvdmlkZXJfeDUwOV9jZXJ0X3VybCI6ICJodHRwczovL3d3dy5nb29nbGVhcGlzLmNvbS9vYXV0aDIvdjEvY2VydHMiLAogICJjbGllbnRfeDUwOV9jZXJ0X3VybCI6ICJodHRwczovL3d3dy5nb29nbGVhcGlzLmNvbS9yb2JvdC92MS9tZXRhZGF0YS94NTA5L2ZpcmViYXNlLWFkbWluc2RrLTF3b3F6JTQwZ29zZWN1cmktOGE4NTIuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iCn0K";
    String CACHE_NAME = "monsupercache";

    Logger LOG = LoggerFactory.getLogger(FireBaseAccount.class);

    @Bean
    public DatabaseReference firebaseDatabase() {
        DatabaseReference firebase = FirebaseDatabase.getInstance().getReference();
        return firebase;
    }

    @PostConstruct
    void init() throws TechnicalException {
        try {

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(
                            GoogleCredentials.fromStream(firebaseKey()))
                    .setDatabaseUrl("https://gosecuri-8a852.firebaseio.com")
                    .build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (FileNotFoundException e) {
            TechnicalException.throwTechnicalException("key file not found", e);
        } catch (IOException e) {
            TechnicalException.throwTechnicalException("io exception", e);
        }
    }

    @Bean
    public InputStream firebaseKey() throws IOException {
        byte[] decode = Base64.getDecoder().decode(KEY_FILE);
        return new ByteArrayInputStream(decode);

    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager();
    }

}
