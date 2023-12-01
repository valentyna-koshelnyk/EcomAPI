package org.example.products;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
@Path("/products")

public class ProductResource {


        ProductDAO productDao = new ProductDAO();

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<Product> getProducts() {
            return productDao.getAllProducts();
        }

        @GET
        @Path("/{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public Product getProduct(@PathParam("id") int id) {
            return productDao.getProduct(id);
        }

        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        public void addProduct(Product product) {
            productDao.addProduct(product);
        }

        @PUT
        @Path("/{id}")
        @Consumes(MediaType.APPLICATION_JSON)
        public void updateProduct(@PathParam("id") int id, Product product) {
            productDao.updateProduct(id, product);
        }

        @DELETE
        @Path("/{id}")
        public void deleteProduct(@PathParam("id") int id) {
            productDao.deleteProduct(id);
        }

    @GET
    @Path("/filter/category")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> filterByCategory(@QueryParam("category") String category) {
        return productDao.filterByCategory(category);
    }

    @GET
    @Path("/filter/price")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> filterByPrice(
            @QueryParam("minPrice") int minPrice,
            @QueryParam("maxPrice") int maxPrice) {
        return productDao.filterByPrice(minPrice, maxPrice);
    }
    }

