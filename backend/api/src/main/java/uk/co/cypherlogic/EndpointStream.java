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
 * REST API Endpoint for Stream cipher with Linear Feedback Shift Register
 *
 * @author ESRS Group 2
 * @version 2022-03-16
 */
@Path("/stream")
public class EndpointStream {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EndpointVigenere
     */
    public EndpointStream() {
    }

    @GET
    @Path("/encrypt/{pt}/{iv}/{taps}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encrypt(@PathParam("pt") String plaintext, @PathParam("iv") String iv, @PathParam("taps") String taps) {
        StreamCipher stream = new StreamCipher(iv, taps);
        return Response.ok(stream.encrypt(plaintext)).header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("/decrypt/{ct}/{iv}/{taps}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response decrypt(@PathParam("ct") String ciphertext, @PathParam("iv") String iv, @PathParam("taps") String taps) {
        StreamCipher stream = new StreamCipher(iv, taps);
        return Response.ok(stream.decrypt(ciphertext)).header("Access-Control-Allow-Origin", "*").build();
    }

}
