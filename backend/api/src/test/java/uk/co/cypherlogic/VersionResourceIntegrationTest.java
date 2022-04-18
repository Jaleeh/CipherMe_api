package uk.co.cypherlogic;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Jersey Test Framework - Version Resource Integration Test
 *
 * @author ESRS Group 2
 * @version 2022-03-10
 */
public class VersionResourceIntegrationTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(EndpointVersion.class);
    }

    @Test
    public void versionResponseTest() {
        Response response = target("version").request().get();
        assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
    }

    @Test
    public void versionResultTest() {
        final String json = target("version").request().get(String.class);
        assertEquals(json, "{\"version\":\"v.0.24\"}");
    }
}
