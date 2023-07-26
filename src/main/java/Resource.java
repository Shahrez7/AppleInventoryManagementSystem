import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/path")
public class Resource {
    private static final Logger LOGGER = LogManager.getLogger(Resource.class);


    @GET
    @Path("/id/{InvId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String Fetchid(@PathParam("InvId") int InvId) {
        try {LOGGER.info("GET Request Received: Fetch by ID: "+InvId);
            return new Gson().toJson(App.FetchByID(InvId));
        } catch (Exception exc) {
            LOGGER.error("Error Occurred!");
            exc.printStackTrace();
        }
        return null;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPList() {
        try {
            LOGGER.info("GET Request Received: Get All Inventory Items List");
            return new Gson().toJson(App.getPhone());
        } catch (Exception exc) {
            LOGGER.error("Error Occurred!");
            exc.printStackTrace();
        }
        return null;
    }
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String FetchAllInventory() {
        try {
            LOGGER.info("GET Request Received: Get All Inventory Items");
            return new Gson().toJson(App.FetchAll());
        } catch (Exception exc) {
            LOGGER.error("Error Occurred!");
            exc.printStackTrace();
        }
        return null;
    }
    @GET
    @Path("/category/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String FetchAllByCategory(@PathParam("categoryId") int categoryId) {
        try {
            LOGGER.info("GET Request Received: Fetch AlL by Category: "+categoryId);
            return new Gson().toJson(App.FetchAllByCategory(categoryId));
        } catch (Exception exc) {
            LOGGER.error("Error Occurred!");
            exc.printStackTrace();

        }
        return null;
    }
    @GET
    @Path("/location/{locationId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String FetchAllByLocation(@PathParam("locationId") int locationId) {
        try {
            LOGGER.info("GET Request Received: Fetch AlL by Location: "+locationId);
            return new Gson().toJson(App.FetchAllByLocation(locationId));
        } catch (Exception exc) {
            LOGGER.error("Error Occurred!");
            exc.printStackTrace();
        }
        return null;
    }
    @GET
    @Path("/location/{locationId}/category/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String FetchAllByLocationAndCategory(@PathParam("locationId") int locationId, @PathParam("categoryId") int categoryId) {
        try {
            LOGGER.info("GET Request Received: Fetch AlL by Location: "+locationId+ " and Category: "+categoryId);
            return new Gson().toJson(App.FetchAllByLocationAndCategory(locationId, categoryId));
        } catch (Exception exc) {
            exc.printStackTrace();
            LOGGER.error("Error Occurred!");
        }
        return null;
    }
    @DELETE
    @Path("/id/{InvId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteInventoryItem(@PathParam("InvId") int InvId) {
        try {
            boolean isDeleted = App.deleteInventoryItemById(InvId);
            if (isDeleted) {
                LOGGER.info("Delete Request Received: Delete Inventory Item ID : "+InvId);
                return Response.status(Response.Status.OK).entity("{\"message\":\"Inventory item deleted successfully.\"}").build();
            } else {
                LOGGER.error("Error: Item ID Not Found");
                return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Inventory item not found.\"}").build();
            }
        } catch (Exception exc) {
            LOGGER.error("Error Occurred!");
            exc.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"An error occurred.\"}").build();
        }
    }
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addInventoryItem(String payload) {
        Gson gson = new Gson();
        Inventory newItem = gson.fromJson(payload, Inventory.class);

        Inventory addedItem = App.addInventoryItem(newItem);

        if (addedItem != null) {
            LOGGER.info("Post Request Received: Item Added");
            String responseJson = gson.toJson(addedItem);
            return Response.status(Response.Status.OK).entity(responseJson).build();
        } else {
            LOGGER.error("Error Occurred!");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"Failed to add inventory item.\"}").build();
        }
    }

    @PUT
    @Path("/{inventory_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateInventoryItem(@PathParam("inventory_id") int inventoryId, String payload) {
        Gson gson = new Gson();
        Inventory updatedItem = gson.fromJson(payload, Inventory.class);
        updatedItem.setId(inventoryId);
        Inventory result = App.updateInventoryItem(updatedItem);
        if (result != null) {
            LOGGER.info("Put Request Received: Update Inventory Item ID : "+inventoryId);
            String responseJson = gson.toJson(result);
            return Response.status(Response.Status.OK).entity(responseJson).build();
        } else {
            LOGGER.error("Error Occurred!");
            return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Inventory item not found.\"}").build();
        }
    }

}
