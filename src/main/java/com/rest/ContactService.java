package com.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rest.exception.AppException;



/**
 * Root resource (exposed at "contacts" path)
 */
@Path("contacts")
public class ContactService {


	ContactsDoa contactsObj = new ContactsDoa();


	/**
	 * Method handling HTTP POST requests. The returned object will be sent
	 * to the client as "text/html" media type.
	 *
	 * @return String that will be returned as a text/html response.
	 * @throws AppException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public Response addNewContact(Contacts contact) throws AppException {
		contactsObj.addNewContact(contact);
		return Response.status(Response.Status.CREATED)//201
				.entity("Contact name: " + contact.getName() + " added to records").build();
	}

	/**
	 * Method handling HTTP GET requests. The returned object will be sent
	 * to the client as "application/json" media type.
	 *
	 * @return Array of Strings that will be returned as a application/json response.
	 * @throws AppException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getContactNames() {
		return Response.status(Response.Status.OK)//200
				.entity(contactsObj.getAllContactNames()).build();
	}

	/**
	 * Method handling HTTP GET requests. The returned object will be sent
	 * to the client as "application/json" media type.
	 *
	 * @param Contact name String 
	 * @return Json that will be returned as a application/json response.
	 * @throws AppException
	 */
	@GET
	@Path("{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getContactDetails(@PathParam("name") String name) throws AppException {
		return Response.status(Response.Status.OK)//200
				.entity(contactsObj.getContactDetails(name)).build();
	}

	/**
	 * Method handling HTTP PUT requests. The returned object will be sent
	 * to the client as "text/html" media type.
	 *
	 * @param Contact name String 
	 * @return String that will be returned as a text/html response.
	 * @throws AppException
	 */
	@PUT
	@Path("{name}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public Response updateExistingContact(@PathParam("name") String name, Contacts contact) throws AppException {
		contactsObj.updateContact(name, contact);
		return Response.status(Response.Status.OK)//200
				.entity("Contact details updated for name: "+name).build();
	}

	/**
	 * Method handling HTTP DELETE requests. The returned object will be sent
	 * to the client as "text/html" media type.
	 *
	 * @param Contact name String 
	 * @return String that will be returned as a text/html response.
	 * @throws AppException
	 */
	@DELETE
	@Path("{name}")
	@Produces(MediaType.TEXT_HTML)
	public Response deleteExistingContact(@PathParam("name") String name) throws AppException {
		contactsObj.deleteContact(name);
		return Response.status(Response.Status.OK)//200
				.entity("Contact details deleted for name: "+name).build();
	}
}
