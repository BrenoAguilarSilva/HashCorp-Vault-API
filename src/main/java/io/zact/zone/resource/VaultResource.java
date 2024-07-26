package io.zact.zone.resource;

import io.zact.zone.dto.ResponseDTO;
import io.zact.zone.dto.SecretsDTO;
import io.zact.zone.service.VaultService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

/**
 * VaultResource
 * <p>
 * This class is responsible for handling all HTTP requests related to Vault operations.
 * It is a RESTful resource that can be accessed through the path "/api/v1/vault".
 * It is also responsible for handling all exceptions related to Vault entity.
 * It is also responsible for handling all security related to Vault entity.
 */
@Path("/api/v1/vault")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "vault", description = "vault operations")
public class VaultResource {
    private static final String INVALID_CREDENTIALS_DATA_EXCEPTION_MESSAGE = "Invalid credentials";
    VaultService vaultService;

    @Inject
    public VaultResource(VaultService vaultService){
        this.vaultService = vaultService;
    }

    /**
     * This method is used to created Secrets From Path
     *
     * @return Response with the created Secrets From Path
     */
    @POST
    @Path("create/secretsPath/{Path}")
    @Operation(summary = "created Secrets From Path", description = "created Secrets From Path")
    @APIResponse(responseCode = "200", description = "created Secrets From Path successfully")
    @APIResponse(responseCode = "404", description = "created Secrets From Path cannot be returned")
    public Response createSecretsFromPathVault(@Parameter(description = "Path from Secret") @PathParam("Path") String pathSecret, @Parameter(description = "Fields from created")SecretsDTO secretsDTO){
        try{
            ResponseDTO response = vaultService.createSecretValuesFromPath(pathSecret, secretsDTO);
            return Response.ok(response).build();
        } catch (IllegalArgumentException illegalArgumentException){
            return Response.status(Response.Status.NOT_FOUND).entity(INVALID_CREDENTIALS_DATA_EXCEPTION_MESSAGE).build();
        }
    }

    /**
     * This method is used to return all secrets details from APP specified from Vault
     *
     * @return Response with the all secrets details
     */
    @GET
    @Path("/secretsPath/{Path}")
    @Operation(summary = "Return all secrets details from Path", description = "Return all secrets details from Path")
    @APIResponse(responseCode = "200", description = "returned all secrets details from Path successfully")
    @APIResponse(responseCode = "404", description = "all secrets details cannot be returned")
    public Response returnSecretsFromPathVault(@Parameter(description = "Path from Secret") @PathParam("Path") String pathSecret){
        try{
            ResponseDTO response = vaultService.getSecretValuesFromPath(pathSecret);
            return Response.ok(response).build();
        } catch (IllegalArgumentException illegalArgumentException){
            return Response.status(Response.Status.NOT_FOUND).entity(INVALID_CREDENTIALS_DATA_EXCEPTION_MESSAGE).build();
        }
    }

    /**
     * This method is used to update Secret From Path
     *
     * @return Response with update Secret From Path
     */
    @PATCH
    @Path("update/secretsPath/{Path}")
    @Operation(summary = "Return update Secret From Path", description = "Return update Secret From Path")
    @APIResponse(responseCode = "200", description = "update Secret From Path successfully")
    @APIResponse(responseCode = "404", description = "update Secret From Path cannot be returned")
    public Response updateSecretsFromPathVault(@Parameter(description = "Path from Secret") @PathParam("Path") String pathSecret,  @Parameter(description = "Fields from updated")SecretsDTO secretsDTO){
        try{
            ResponseDTO response = vaultService.updateSecretValuesFromPath(pathSecret, secretsDTO);
            return Response.ok(response).build();
        } catch (IllegalArgumentException illegalArgumentException){
            return Response.status(Response.Status.NOT_FOUND).entity(INVALID_CREDENTIALS_DATA_EXCEPTION_MESSAGE).build();
        }
    }

    /**
     * This method is used to return Delete Secrets from Path
     *
     * @return Response with the Delete Secrets from Path
     */
    @DELETE
    @Path("delete/secretsPath/{Path}")
    @Operation(summary = "Delete Secrets from Path", description = "Delete Secrets from Path")
    @APIResponse(responseCode = "204", description = "Delete Secrets from Path successfully")
    @APIResponse(responseCode = "404", description = "Delete Secrets from Path cannot be returned")
    public Response deleteSecretsFromPathVault(@Parameter(description = "Path from Secret") @PathParam("Path") String pathSecret){
        try{
            ResponseDTO response = vaultService.deleteSecretValuesFromPath(pathSecret);
            return Response.status(Response.Status.NO_CONTENT).entity(response).build();
        } catch (IllegalArgumentException illegalArgumentException){
            return Response.status(Response.Status.NOT_FOUND).entity(INVALID_CREDENTIALS_DATA_EXCEPTION_MESSAGE).build();
        }
    }

}
