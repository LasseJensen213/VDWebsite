package com.lasse.RestService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import controller.Controller;
import dto.RSS.RSSMessageDTO;
import dto.datex.SituationDTO;
import rest.model.RestStateLevel;
import rest.model.RestUserText;



/**
 * Root resource (exposed at "myresource" path)
 */
@Path("backend")
public class BackendResource {
	

	@Path("notifications")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNotificationList() {
		Controller ctrl = Controller.getInstance();
		ResponseBuilder resp;

		List<SituationDTO> listOfNotifications = ctrl.getNotifications();
		
		resp = Response.status(Status.OK).entity(listOfNotifications);

		return resp.build();
	}
	
	@Path("warnings")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWarningList() {
		Controller ctrl = Controller.getInstance();
		ResponseBuilder resp;
		//Logger.getLogger(BackendResource.class.getName()).log(Level.INFO,ctrl.toString());

		List<RSSMessageDTO> listOfWarnings = ctrl.getWarnings();
		//List<RSSMessageDTO> listOfWarnings = new ArrayList<RSSMessageDTO>();
		resp = Response.status(Status.OK).entity(listOfWarnings);
		return resp.build();
	}
	
	
	@Path("addStateLevel")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addStateLevel(RestStateLevel restStateLevel) {
		Controller ctrl = Controller.getInstance();
		ResponseBuilder resp;
//		Logger.getLogger(BackendResource.class.getName()).log(Level.INFO,restStateLevel.toString());
		
		ctrl.insertServerElement(restStateLevel);

		resp = Response.status(Status.OK);
		
		return resp.build();
		
	}
	
	@Path("saveUserText")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveUserText(RestUserText restUserText) {
		Controller ctrl = Controller.getInstance();
		ResponseBuilder resp;
		Logger.getLogger(BackendResource.class.getName()).log(Level.INFO,restUserText.toString());
		
		ctrl.saveUserText(restUserText);

		resp = Response.status(Status.OK);
		
		return resp.build();

	}
	
	@Path("getUserText")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserText() {
		Controller ctrl = Controller.getInstance();
		ResponseBuilder resp;
		
		resp = Response.status(Status.OK).entity(ctrl.getUserText());
		
		return resp.build();
		
		
	}
	
	@Path("getCurrentState")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCurrrentState() {
		Controller ctrl = Controller.getInstance();
		ResponseBuilder resp;
		
		resp = Response.status(Status.OK).entity(ctrl.getState());
		
		return resp.build();
	}
	
}






