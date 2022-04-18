package uk.co.cypherlogic;

import java.math.BigInteger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST API Endpoint for AES cipher
 *
 * @author ESRS Group 2
 * @version 2022-03-16
 */
@Path("/aes")
public class EndpointAes {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EndpointRsa
     */
    public EndpointAes() {
    }

    @GET
    @Path("/encrypt/{pt}/{SK}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encrypt(@PathParam("pt") String plaintext, @PathParam("SK") String secret_key) {
        AES c = new AES();
        return Response.ok(c.encrypt(plaintext, secret_key)).build();
    }

    @GET
    @Path("/decrypt/{ct}/{d}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response decrypt(@PathParam("ct") String ciphertext, @PathParam("SK") String secret_key) {
        AES c = new AES();
        return Response.ok(c.decrypt(ciphertext, secret_key)).build();
    }

}
