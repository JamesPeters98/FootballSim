package com.james;

public class MatchResult {
	
	private int homeGoals;
	private int awayGoals;
	private int homePoints;
	private int awayPoints;
	
	private Team homeTeam;
	private Team awayTeam;
	
	public MatchResult(Team home, Team away, int homeGoals, int awayGoals){
		this.homeGoals = homeGoals;
		this.awayGoals = awayGoals;
		this.homeTeam = home;
		this.awayTeam = away;
		if(homeGoals > awayGoals){ homePoints = 3; }
		else if(homeGoals < awayGoals){ awayPoints = 3; }
		else { homePoints = 1; awayPoints = 1; }
	}
	
	public int getHomeGoals(){
		return homeGoals;
	}
	
	public int getAwayGoals(){
		return awayGoals;
	}
	
	public int getHomePoints(){
		return homePoints;
	}
	
	public int getAwayPoints(){
		return awayPoints;
	}
	
	public Team getHomeTeam() {
		return homeTeam;
	}
	
	public Team getAwayTeam() {
		return awayTeam;
	}

}