package uk.co.cypherlogic;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author ESRS Group 2
 * @version 2022-02-16
 */
@javax.ws.rs.ApplicationPath("/")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(uk.co.cypherlogic.EndpointAes.class);
        resources.add(uk.co.cypherlogic.EndpointCaesar.class);
        resources.add(uk.co.cypherlogic.EndpointDES.class);
        resources.add(uk.co.cypherlogic.EndpointDESECB.class);
        resources.add(uk.co.cypherlogic.EndpointElGamal.class);
        resources.add(uk.co.cypherlogic.EndpointOtp.class);
        resources.add(uk.co.cypherlogic.EndpointRail.class);
        resources.add(uk.co.cypherlogic.EndpointRoute.class);
        resources.add(uk.co.cypherlogic.EndpointRsa.class);
        resources.add(uk.co.cypherlogic.EndpointScytale.class);
        resources.add(uk.co.cypherlogic.EndpointStream.class);
        resources.add(uk.co.cypherlogic.EndpointVersion.class);
        resources.add(uk.co.cypherlogic.EndpointVigenere.class);
    }

}
