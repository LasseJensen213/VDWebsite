package rest;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/test")
public class NotificationServiceResource {

	/*
	@Path("/notifications")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response notifications(){
		ResponseBuilder resp;
        try {
            List<SituationExtensionDTO> notifications = ;
            resp = Response.status(Status.OK).entity(allScores);
        } catch (DAOException ex) {
            Logger.getLogger(GalgelegResource.class.getName()).log(Level.SEVERE, null, ex);
            resp = Response.status(Status.INTERNAL_SERVER_ERROR);
        } catch (NotBoundException ex) {
            Logger.getLogger(GalgelegResource.class.getName()).log(Level.SEVERE, null, ex);
            resp = Response.status(Status.INTERNAL_SERVER_ERROR);
        } catch (RemoteException ex) {
            Logger.getLogger(GalgelegResource.class.getName()).log(Level.SEVERE, null, ex);
            resp = Response.status(Status.INTERNAL_SERVER_ERROR);
        } catch (MalformedURLException ex) {
            Logger.getLogger(GalgelegResource.class.getName()).log(Level.SEVERE, null, ex);
            resp = Response.status(Status.INTERNAL_SERVER_ERROR);
        }
        return resp.build();		


	}
	 */

	@GET
	@Path("/verify")
	@Produces(MediaType.APPLICATION_JSON)
	public Response verifyRESTService() {
		String result = "RESTService Successfully started..";

		// return HTTP response 200 in case of success
		return Response.status(200).entity(result).build();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Jersey";
	}











}
