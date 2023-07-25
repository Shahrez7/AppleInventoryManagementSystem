import com.google.gson.Gson;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/path")
public class Resource {
    @GET
    @Path("/id/{InvId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String Fetchid(@PathParam("InvId") int InvId) {
        try {
            return new Gson().toJson(App.FetchByID(InvId));
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPList() {
        try {
            return new Gson().toJson(App.getPhone());
        } catch (Exception exc) {

            exc.printStackTrace();
        }
        return null;
    }
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String FetchAllInventory() {
        try {
            return new Gson().toJson(App.FetchAll());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }
    @GET
    @Path("/category/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String FetchAllByCategory(@PathParam("categoryId") int categoryId) {
        try {
            return new Gson().toJson(App.FetchAllByCategory(categoryId));
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }
    @GET
    @Path("/location/{locationId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String FetchAllByLocation(@PathParam("locationId") int locationId) {
        try {
            return new Gson().toJson(App.FetchAllByLocation(locationId));
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }
}
