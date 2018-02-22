package com.robi.messengerApp.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.robi.messengerApp.Service.MessageService;
import com.robi.messengerApp.model.Message;

@Path("/messages") // this path will map to url messages
public class MessageResource {

	MessageService messageService = new MessageService();

	@GET // this will map to get url request
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages() {
		return messageService.getAllMessages();
	}

	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") long id) {
		return messageService.getMessage(id);
	}

}
