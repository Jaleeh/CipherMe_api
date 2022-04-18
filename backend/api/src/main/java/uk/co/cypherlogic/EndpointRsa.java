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
 * REST API Endpoint for RSA cipher
 *
 * @author ESRS Group 2
 * @version 2022-03-16
 */
@Path("/rsa")
public class EndpointRsa {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EndpointRsa
     */
    public EndpointRsa() {
    }

    @GET
    @Path("/encrypt/{pt}/{p}/{q}/{e}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response encrypt(@PathParam("pt") String plaintext, @PathParam("p") BigInteger p, @PathParam("q") BigInteger q, @PathParam("q") BigInteger e) {
         RSA c = new RSA();
        //c.encryptMessage(pt);
        return Response.ok(c.encryptMessage(plaintext,p,q,e)).build();
    }

    @GET
    @Path("/decrypt/{ct}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response decrypt(@PathParam("ct") String ciphertext) {
        RSA c = new RSA();
        return Response.ok(c.decryptMessage(ciphertext)).build();
    }

}
