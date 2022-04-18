package uk.co.cypherlogic;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST API Endpoint for Scytale cipher
 *
 * @author ESRS Group 2
 * @version 2022-03-16
 */
@Path("/scytale")
public class EndpointScytale {

    /**
     * Creates a new instance of EndpointScytale
     */
    public EndpointScytale() {
    }

    @GET
    @Path("/encrypt/{pt}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encrypt(@PathParam("pt") String plaintext, @PathParam("depth") int depth) {
        return Response.ok(ScytaleCipher.encrypt(plaintext, depth)).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("/decrypt/{ct}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response decrypt(@PathParam("ct") String ciphertext, @PathParam("depth") int depth) {
        return Response.ok(ScytaleCipher.decrypt(ciphertext, depth)).header("Access-Control-Allow-Origin", "*").build();

    }

}
