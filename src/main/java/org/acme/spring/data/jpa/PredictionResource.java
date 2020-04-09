package org.acme.spring.data.jpa;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;

@Path("/predictions")
public class PredictionResource {

	@Inject
	PredictionRepository repo;
	
	@Inject
	@Channel("predictions-stream") Publisher<Prediction> predictions;
	
	@GET
	@Produces("application/json")
	public Iterable<Prediction> getPredictionHistory() {
		return repo.findAll();
	}
	
	@GET
	@Path("/stream")
	@Produces(MediaType.SERVER_SENT_EVENTS)
	@SseElementType("application/json")
	public Publisher<Prediction> stream() {
		return predictions;
	}
	
}
