package org.acme.spring.data.messaging;

public class MatchResult {
    public String home_team;
    public String away_team;
    public Integer home_score;
    public Integer away_score;

    public MatchResult(String home_team, String away_team, Integer home_score, Integer away_score) {
        this.home_team = home_team;
        this.away_team = away_team;
        this.home_score = home_score;
        this.away_score = away_score;
    }

    public MatchResult() {
    }

	@Override
	public String toString() {
		return "MatchResult [home_team=" + home_team + ", away_team=" + away_team + ", home_score=" + home_score
				+ ", away_score=" + away_score + "]";
	}
    
}
