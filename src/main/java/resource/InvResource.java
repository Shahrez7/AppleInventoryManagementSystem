package resource;

import com.google.gson.Gson;
import domain.Inventory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.InvService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/path")
public class InvResource {
    private static final Logger LOGGER = LogManager.getLogger(InvResource.class);
    public InvService InventoryServices = new InvService();

    public InvResource() throws ClassNotFoundException {
    }

    @GET
    @Path("/id/{InvId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String Fetchid(@PathParam("InvId") int InvId) {
        if (InvId <= 0) {
            return null;
        }
        try {LOGGER.info("GET Request Received: Fetch by ID: "+InvId);
            return new Gson().toJson(InventoryServices.FetchByID(InvId));
        } catch (Exception exc) {
            LOGGER.error("Error Occurred!");
            exc.printStackTrace();
        }
        return ("Done");
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPList() {
        try {
            LOGGER.info("GET Request Received: Get All Inventory Items List");
            return new Gson().toJson(InventoryServices.getPhone());
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
            return new Gson().toJson(InventoryServices.FetchAll());
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
            return new Gson().toJson(InventoryServices.FetchAllByCategory(categoryId));
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
            return new Gson().toJson(InventoryServices.FetchAllByLocation(locationId));
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
            return new Gson().toJson(InventoryServices.FetchAllByLocationAndCategory(locationId, categoryId));
        } catch (Exception exc) {
            exc.printStackTrace();
            LOGGER.error("Error Occurred!");
        }
        return null;
    }
    @DELETE
    @Path("/id/{InvId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteInventoryItem(@PathParam("InvId") int InvId) {
        try {
            InventoryServices.deleteInventoryItemById(InvId);

                LOGGER.info("Delete Request Received: Delete Inventory Item ID : "+InvId);
                return ("Item Deleted");

            }
        catch (Exception exc) {
            LOGGER.error("Error Occurred!");
            exc.printStackTrace();
        }
        return null;
    }
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addInventoryItem(String payload) throws ClassNotFoundException {

        try{
        Gson gson = new Gson();
        Inventory newItem = gson.fromJson(payload, Inventory.class);
        InventoryServices.addInventoryItem(newItem);
        LOGGER.info("Post Request Received: Item Added");
        return new Gson().toJson(newItem);

        } catch(Exception exc) {
            LOGGER.error("Error Occurred!");
            exc.printStackTrace();
        }
        return null;
    }

    @PUT
    @Path("/{inventory_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateInventoryItem(@PathParam("inventory_id") int inventoryId, String payload) throws ClassNotFoundException {
        try {
        Gson gson = new Gson();
        Inventory updatedItem = gson.fromJson(payload, Inventory.class);
        updatedItem.setId(inventoryId);
        InventoryServices.updateInventoryItem(updatedItem);
        LOGGER.info("Put Request Received: Update Inventory Item ID : "+inventoryId);
        return new Gson().toJson(updatedItem);
        } catch (Exception exc){
            LOGGER.error("Error Occurred!");
            exc.printStackTrace();
        }
        return null;
    }

}