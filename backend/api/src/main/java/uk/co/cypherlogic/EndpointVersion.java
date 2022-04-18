package uk.co.cypherlogic;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST API Endpoint returns current version
 *
 * @author ESRS Group 2
 * @version 2022-03-02
 */
@Path("version")
public class EndpointVersion {

    @Context
    private UriInfo context;
    private String version = "v.0.24";

    /**
     * Creates a new instance of EndpointVersion
     */
    public EndpointVersion() {
    }

    /**
     * Retrieves representation of an instance of
     * uk.co.cypherlogic.crypto.EndpointVigenere
     *
     * @return an MediaType.APPLICATION_JSON response
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response version() {
        return Response.ok("{\"version\":\"" + this.version + "\"}").header("Access-Control-Allow-Origin", "*").build();
    }

}
