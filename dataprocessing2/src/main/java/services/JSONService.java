//package services;
//
//import XStreamJSon.Product;
//
//import javax.ws.rs.Consumes;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.core.Response;
//
//@Path("/json/product")
//public class JSONService {
//
//    @POST
//    @Path("/post")
//    @Consumes("application/json")
//    public Response createProductInJSON(Product product) {
//
//        String result = "Product created : " + product;
//        return Response.status(201).entity(result).build();
//
//
//    }
//}
