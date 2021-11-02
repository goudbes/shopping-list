package org.goudbes;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static final String BASE_URI = "http://localhost:8080/";

    public static Server startServer() throws Exception {
        final ResourceConfig config = new ResourceConfig(PersonServlet.class);
        return JettyHttpContainerFactory.createServer(URI.create(BASE_URI), config);
    }

    public static void main(String[] args) throws Exception {
        try {
            final Server server = startServer();
            if (server == null) {
                throw new RuntimeException("Server is null");
            }
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    System.out.println("Shutting down the application...");
                    server.stop();
                    System.out.println("Done, exit.");
                } catch (Exception e) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                }
            }));
            System.out.printf("Application started.%nStop the application using CTRL+C%n");
            Thread.currentThread().join();

        } catch (InterruptedException ex) {
            System.out.println("Interrupted");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}