package main.java.dev.ecom.orders;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
@Path("/orders")
public class OrdersResource {


    OrderDAO orderDAO = new OrderDAO();
    private OrderDAO orderDao;

    @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<Order> getOrders() {
            return orderDao.getAllOrders();
        }

        @GET
        @Path("/{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public Order getOrder(@PathParam("id") int id) {
            return orderDAO.getOrder(id);
        }

        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        public void addOrder(Order order) {
            orderDAO.addOrder(order);
        }

        @PUT
        @Path("/{id}")
        @Consumes(MediaType.APPLICATION_JSON)
        public void updateOrder(@PathParam("id") int id, Order order) {
            orderDAO.updateOrder(id, order);
        }

        @DELETE
        @Path("/{id}")
        public void deleteOrder(@PathParam("id") int id) {
            orderDAO.deleteOrder(id);
        }
}
