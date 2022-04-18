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
 * REST API Endpoint for El Gamal cipher
 *
 * @author ESRS Group 2
 * @version 2022-03-16
 */
@Path("/elgamal")
public class EndpointElGamal {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EndpointRsa
     */
    public EndpointElGamal() {
    }

    @GET
    @Path("/encrypt/{pt}/{p}/{SK}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encrypt(@PathParam("pt") String plaintext, @PathParam("p") BigInteger p, @PathParam("SK") BigInteger secretkey) {
    ElGamal c= new ElGamal();    
    return Response.ok(c.Encrypt(plaintext, p, secretkey)).build();
    }

    @GET
    @Path("/decrypt/{ct}/{d}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response decrypt(@PathParam("ct") String ciphertext, @PathParam("p") BigInteger p, @PathParam("SK") BigInteger secretkey) {
ElGamal c= new ElGamal();
        return Response.ok(c.Decrypt(ciphertext, p, secretkey)).build();
    }

}
