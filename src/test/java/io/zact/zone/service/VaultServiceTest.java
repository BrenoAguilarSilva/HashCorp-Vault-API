package io.zact.zone.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import io.zact.zone.dto.ResponseDTO;
import io.zact.zone.dto.SecretsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
public class VaultServiceTest {
    @Mock
    private HttpClient httpClient;

    @Mock
    private HttpResponse<String> httpResponse;

    @InjectMocks
    private VaultService vaultService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createSecretValuesFromPathReturnsResponseDTOWhenSuccessful() throws IOException, InterruptedException {
        // Configura o segredo e a resposta esperada
        String secretPath = "test/path";
        SecretsDTO secretsDTO = new SecretsDTO();
        String responseBody = "{\"key\":\"value\"}";

        // Configura o mock para HttpClient
        when(httpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
                .thenReturn(httpResponse);

        // Configura o mock para HttpResponse
        when(httpResponse.body()).thenReturn(responseBody);
        when(httpResponse.statusCode()).thenReturn(200);

        // Executa o método sob teste
        ResponseDTO responseDTO = vaultService.createSecretValuesFromPath(secretPath, secretsDTO);

        // Verifica o resultado
        assertEquals(200, responseDTO.getStatusCode());
    }

    @Test
    void getSecretValuesFromPathReturnsResponseDTOWhenSuccessful() throws IOException, InterruptedException {
        // Configura o caminho do segredo e a resposta esperada
        String secretPath = "test/path";
        String responseBody = "{\"key\":\"value\"}";

        // Configura o mock para HttpClient
        when(httpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
                .thenReturn(httpResponse);

        // Configura o mock para HttpResponse
        when(httpResponse.body()).thenReturn(responseBody);
        when(httpResponse.statusCode()).thenReturn(200);

        // Executa o método sob teste
        ResponseDTO responseDTO = vaultService.getSecretValuesFromPath(secretPath);

        // Verifica o resultado
        assertEquals(200, responseDTO.getStatusCode());
    }

    @Test
    void updateSecretValuesFromPathReturnsResponseDTOWhenSuccessful() throws IOException, InterruptedException {
        // Configura o segredo e a resposta esperada
        String secretPath = "test/path";
        SecretsDTO secretsDTO = new SecretsDTO();
        String responseBody = "{\"key\":\"value\"}";

        // Configura o mock para HttpClient
        when(httpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
                .thenReturn(httpResponse);

        // Configura o mock para HttpResponse
        when(httpResponse.body()).thenReturn(responseBody);
        when(httpResponse.statusCode()).thenReturn(500);

        // Executa o método sob teste
        ResponseDTO responseDTO = vaultService.updateSecretValuesFromPath(secretPath, secretsDTO);

        // Verifica o resultado
        assertEquals(500, responseDTO.getStatusCode());
    }

    @Test
    void deleteSecretValuesFromPathReturnsResponseDTOWhenSuccessful() throws IOException, InterruptedException {
        // Configura o caminho do segredo
        String secretPath = "test/path";
        String responseBody = "{\"response\":\"success\"}";

        // Configura o mock para HttpClient
        when(httpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
                .thenReturn(httpResponse);

        // Configura o mock para HttpResponse
        when(httpResponse.body()).thenReturn(responseBody);
        when(httpResponse.statusCode()).thenReturn(204);

        // Executa o método sob teste
        ResponseDTO responseDTO = vaultService.deleteSecretValuesFromPath(secretPath);

        assertEquals(204, responseDTO.getStatusCode(), "Status code should be 204");
    }
}
