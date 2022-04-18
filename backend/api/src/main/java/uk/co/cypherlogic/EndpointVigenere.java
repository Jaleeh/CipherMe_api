package uk.co.cypherlogic;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST API Endpoint for Vigenere cipher
 *
 * @author ESRS Group 2
 * @version 2022-02-16
 */
@Path("/vigenere")
public class EndpointVigenere {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EndpointVigenere
     */
    public EndpointVigenere() {
    }

    @GET
    @Path("/encrypt/{pt}/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encrypt(@PathParam("pt") String plaintext, @PathParam("key") String key) {
        return Response.ok(VigenereCipher.encrypt(plaintext, key)).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("/decrypt/{ct}/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response decrypt(@PathParam("ct") String ciphertext, @PathParam("key") String key) {
        return Response.ok(VigenereCipher.decrypt(ciphertext, key)).header("Access-Control-Allow-Origin", "*").build();

    }

}
