package io.zact.zone.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.zact.zone.dto.ResponseDTO;
import io.zact.zone.dto.SecretsDTO;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.ConfigProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@ApplicationScoped
public class VaultService {
    String ClientTokenVault = ConfigProvider.getConfig().getValue("quarkus.vault.authentication.client-token", String.class);
    String vaultUrlServer = ConfigProvider.getConfig().getValue("quarkus.vault.authentication.client-token", String.class);
    private static final Logger LOGGER = LoggerFactory.getLogger(VaultService.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public ResponseDTO getSecretValuesFromPath(String secretPath){
        try{
            LOGGER.info("Montando a URL");
            String URL =  vaultUrlServer + "/v1/secret/data/" + secretPath;
            HttpClient client = HttpClient.newHttpClient();
            LOGGER.info("Montando a chamada");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .header("Authorization", "Bearer " + ClientTokenVault)
                    .GET()
                    .build();

            LOGGER.info("Realizando a chamada");
            HttpResponse<String> tokenResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonNode responseBody = objectMapper.readTree(tokenResponse.body());

            return new ResponseDTO(tokenResponse.statusCode(), responseBody);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseDTO createSecretValuesFromPath(String secretPath, SecretsDTO secretsDTO){
        try{
            LOGGER.info("Montando a URL");
            String URL =  vaultUrlServer + "/v1/secret/data/" + secretPath;
            LOGGER.info("Convertendo secretsDTO para JSON");
            String requestBody = objectMapper.writeValueAsString(secretsDTO);

            HttpClient client = HttpClient.newHttpClient();
            LOGGER.info("Montando a chamada");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .header("Authorization", "Bearer " + ClientTokenVault)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            LOGGER.info("Realizando a chamada");
            HttpResponse<String> tokenResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonNode responseBody = objectMapper.readTree(tokenResponse.body());

            return new ResponseDTO(tokenResponse.statusCode(), responseBody);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseDTO updateSecretValuesFromPath(String secretPath, SecretsDTO secretsDTO) {
        try {
            LOGGER.info("Montando a URL");
            String URL = vaultUrlServer + "/v1/secret/data/" + secretPath;

            LOGGER.info("Convertendo secretsDTO para JSON");
            String requestBody = objectMapper.writeValueAsString(secretsDTO);

            HttpClient client = HttpClient.newHttpClient();
            LOGGER.info("Montando a chamada");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .header("Authorization", "Bearer " + ClientTokenVault)
                    .header("Content-Type", "application/merge-patch+json")
                    .method("PATCH", HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            LOGGER.info("Realizando a chamada");
            HttpResponse<String> tokenResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonNode responseBody = objectMapper.readTree(tokenResponse.body());

            return new ResponseDTO(tokenResponse.statusCode(), responseBody);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseDTO deleteSecretValuesFromPath(String secretPath){
        try{
            LOGGER.info("Montando a URL");
            String URL =  vaultUrlServer + "/v1/secret/metadata/" + secretPath;
            LOGGER.info("URL de DELETE" + URL);
            HttpClient client = HttpClient.newHttpClient();
            LOGGER.info("Montando a chamada");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .header("Authorization", "Bearer " + ClientTokenVault)
                    .DELETE().build();

            LOGGER.info("Realizando a chamada");
            HttpResponse<String> tokenResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonNode responseBody = objectMapper.readTree(tokenResponse.body());

            return new ResponseDTO(tokenResponse.statusCode(), responseBody);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
