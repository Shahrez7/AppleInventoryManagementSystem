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
    @GET
    @Path("/location/{locationId}/category/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String FetchAllByLocationAndCategory(@PathParam("locationId") int locationId, @PathParam("categoryId") int categoryId) {
        try {
            return new Gson().toJson(App.FetchAllByLocationAndCategory(locationId, categoryId));
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return null;
    }
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addInventoryItem(Inventory newInventory) {
        try {
            Inventory createdInventory = App.AddNewInventory(String.valueOf(newInventory));
            if (createdInventory != null) {
                return Response.status(Response.Status.OK).entity(new Gson().toJson(createdInventory)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Failed to add new inventory item.\"}").build();
            }
        } catch (Exception exc) {
            exc.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"An error occurred.\"}").build();
        }
    }
    @DELETE
    @Path("/id/{InvId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteInventoryItem(@PathParam("InvId") int InvId) {
        try {
            boolean isDeleted = App.deleteInventoryItemById(InvId);
            if (isDeleted) {
                return Response.status(Response.Status.OK).entity("{\"message\":\"Inventory item deleted successfully.\"}").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Inventory item not found.\"}").build();
            }
        } catch (Exception exc) {
            exc.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"An error occurred.\"}").build();
        }
    }

}
