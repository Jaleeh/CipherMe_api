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
 * REST API Endpoint for Rail cipher
 *
 * @author ESRS Group 2
 * @version 2022-03-16
 */
@Path("/rail")
public class EndpointRail {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EndpointVigenere
     */
    public EndpointRail() {
    }

    @GET
    @Path("/encrypt/{pt}/{depth}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encrypt(@PathParam("pt") String plaintext, @PathParam("depth") int depth) throws Exception {
        Rail c = new Rail();
        return Response.ok(c.Encryption(plaintext, depth)).build();
    }

    @GET
    @Path("/decrypt/{ct}/{depth}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response decrypt(@PathParam("ct") String ciphertext, @PathParam("depth") int depth) throws Exception {
        Rail c = new Rail();
        return Response.ok(c.Decryption(ciphertext, depth)).build();
    }

}
