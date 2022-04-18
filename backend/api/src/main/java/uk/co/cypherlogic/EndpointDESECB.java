package uk.co.cypherlogic;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST API Endpoint for DESBlockCipher cipher - for a single block only
 *
 * @author ESRS Group 2
 * @version 2022-03-23
 */
@Path("/des-ecb")
public class EndpointDESECB {

    /**
     * Creates a new instance of EndpointVigenere
     */
    public EndpointDESECB() {
    }

    @GET
    @Path("/encrypt/{pt}/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encrypt(@PathParam("pt") String plaintext, @PathParam("key") String key) {
        DESBlockCipher des;
        try {
            des = new DESBlockCipher(key);
            return Response.ok(des.encrypt(plaintext)).header("Access-Control-Allow-Origin", "*").build();
        } catch (Exception ex) {
            return Response.serverError().header("Access-Control-Allow-Origin", "*").build();
        }

    }

    @GET
    @Path("/decrypt/{ct}/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response decrypt(@PathParam("ct") String ciphertext, @PathParam("key") String key) {
        DESBlockCipher des;
        try {
            des = new DESBlockCipher(key);
            return Response.ok(des.decrypt(ciphertext)).header("Access-Control-Allow-Origin", "*").build();
        } catch (Exception ex) {
            return Response.serverError().header("Access-Control-Allow-Origin", "*").build();
        }

    }

}
