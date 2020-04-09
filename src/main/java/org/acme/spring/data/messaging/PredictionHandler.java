package org.acme.spring.data.messaging;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.acme.spring.data.jpa.Prediction;
import org.acme.spring.data.jpa.PredictionRepository;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

import io.smallrye.reactive.messaging.annotations.Broadcast;

@ApplicationScoped
public class PredictionHandler {

	private static Logger logger = Logger.getLogger(PredictionHandler.class);
	
	@Inject
	PredictionRepository repository;
	
	@Incoming("predictions")
	@Outgoing("predictions-stream")
	@Broadcast
	public Prediction process(MatchResult event) {
		
		logger.debug("Receiving Event: " + event.toString());
		
		Prediction newPrediction = new Prediction();
		newPrediction.setAwayScore(event.away_score);
		newPrediction.setAwayTeam(event.away_team);
		newPrediction.setHomeScore(event.home_score);
		newPrediction.setHomeTeam(event.home_team);
		
		return this.repository.save(newPrediction);
	}
	
}
