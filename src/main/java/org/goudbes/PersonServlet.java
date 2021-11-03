package org.goudbes;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;


@Path("/hello")
public class PersonServlet {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Welcome.";
    }

    @Path("/{username}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Person hello(@PathParam("username") String name) throws Exception {
        return new Person(name, "test@mail.com");
    }

    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> helloList() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Anniken", "anniken@mail.com"));
        list.add(new Person("Jacob", "jacob@mail.com"));
        return list;
    }
}