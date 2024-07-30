package io.zact.zone.resource;
import io.quarkus.test.junit.QuarkusTest;
import io.zact.zone.dto.ResponseDTO;
import io.zact.zone.dto.SecretsDTO;
import io.zact.zone.service.VaultService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
public class VaultResourceTest {

    @Mock
    private VaultService vaultService;

    @InjectMocks
    private VaultResource vaultResource;

    private static final String INVALID_CREDENTIALS_DATA_EXCEPTION_MESSAGE = "Invalid credentials";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createSecretsFromPathVaultReturnsOkWhenSecretIsCreated() {
        SecretsDTO secretsDTO = new SecretsDTO();
        ResponseDTO responseDTO = new ResponseDTO(200, null);

        when(vaultService.createSecretValuesFromPath(anyString(), any(SecretsDTO.class)))
                .thenReturn(responseDTO);

        Response response = vaultResource.createSecretsFromPathVault("test/path", secretsDTO);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(responseDTO, response.getEntity());
    }

    @Test
    void createSecretsFromPathVaultReturnsNotFoundWhenExceptionIsThrown() {
        SecretsDTO secretsDTO = new SecretsDTO();

        when(vaultService.createSecretValuesFromPath(anyString(), any(SecretsDTO.class)))
                .thenThrow(new IllegalArgumentException());

        Response response = vaultResource.createSecretsFromPathVault("test/path", secretsDTO);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        assertEquals(INVALID_CREDENTIALS_DATA_EXCEPTION_MESSAGE, response.getEntity());
    }

    @Test
    void returnSecretsFromPathVaultReturnsOkWhenSecretsAreReturned() {
        ResponseDTO responseDTO = new ResponseDTO(200, null);

        when(vaultService.getSecretValuesFromPath(anyString()))
                .thenReturn(responseDTO);

        Response response = vaultResource.returnSecretsFromPathVault("test/path");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(responseDTO, response.getEntity());
    }

    @Test
    void returnSecretsFromPathVaultReturnsNotFoundWhenExceptionIsThrown() {
        when(vaultService.getSecretValuesFromPath(anyString()))
                .thenThrow(new IllegalArgumentException());

        Response response = vaultResource.returnSecretsFromPathVault("test/path");

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        assertEquals(INVALID_CREDENTIALS_DATA_EXCEPTION_MESSAGE, response.getEntity());
    }

    @Test
    void updateSecretsFromPathVaultReturnsOkWhenSecretIsUpdated() {
        SecretsDTO secretsDTO = new SecretsDTO();
        ResponseDTO responseDTO = new ResponseDTO(200, null);

        when(vaultService.updateSecretValuesFromPath(anyString(), any(SecretsDTO.class)))
                .thenReturn(responseDTO);

        Response response = vaultResource.updateSecretsFromPathVault("test/path", secretsDTO);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(responseDTO, response.getEntity());
    }

    @Test
    void updateSecretsFromPathVaultReturnsNotFoundWhenExceptionIsThrown() {
        SecretsDTO secretsDTO = new SecretsDTO();

        when(vaultService.updateSecretValuesFromPath(anyString(), any(SecretsDTO.class)))
                .thenThrow(new IllegalArgumentException());

        Response response = vaultResource.updateSecretsFromPathVault("test/path", secretsDTO);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        assertEquals(INVALID_CREDENTIALS_DATA_EXCEPTION_MESSAGE, response.getEntity());
    }

    @Test
    void deleteSecretsFromPathVaultReturnsNoContentWhenSecretIsDeleted() {
        ResponseDTO responseDTO = new ResponseDTO(204, null);

        when(vaultService.deleteSecretValuesFromPath(anyString()))
                .thenReturn(responseDTO);

        Response response = vaultResource.deleteSecretsFromPathVault("test/path");

        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
        assertEquals(responseDTO, response.getEntity());
    }

    @Test
    void deleteSecretsFromPathVaultReturnsNotFoundWhenExceptionIsThrown() {
        when(vaultService.deleteSecretValuesFromPath(anyString()))
                .thenThrow(new IllegalArgumentException());

        Response response = vaultResource.deleteSecretsFromPathVault("test/path");

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        assertEquals(INVALID_CREDENTIALS_DATA_EXCEPTION_MESSAGE, response.getEntity());
    }
}
