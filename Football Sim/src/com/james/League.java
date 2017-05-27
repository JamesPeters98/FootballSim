package com.james;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class League {
	
	private List<Team> teams;
	private HashMap<Team,LeagueStats> leagueStats;
	
	public League(){
		teams = new LinkedList<Team>();
		leagueStats = new HashMap<Team,LeagueStats>();
		addTeam(Teams.CHELSEA);
		addTeam(Teams.LIVERPOOL);
		addTeam(Teams.MAN_CITY);
		addTeam(Teams.TOTTENHAM);
		addTeam(Teams.ARSENAL);
		addTeam(Teams.MAN_UNITED);
		addTeam(Teams.EVERTON);
		addTeam(Teams.SOUTHAMPTON);
		addTeam(Teams.CRYS_PALACE);
		addTeam(Teams.BOURNEMOUTH);
		addTeam(Teams.LEICESTER);
		addTeam(Teams.SWANSEA);
		addTeam(Teams.WEST_BROM);
		addTeam(Teams.WEST_HAM);
		addTeam(Teams.BURNLEY);
		addTeam(Teams.HULL);
		addTeam(Teams.MIDDLESBROUGH);
		addTeam(Teams.WATFORD);
		addTeam(Teams.STOKE);
		addTeam(Teams.SUNDERLAND);
	}
	
	private void addTeam(Team team){
		teams.add(team);
		leagueStats.put(team, new LeagueStats());
	}
	
	public List<Team> getTeams(){
		return teams;
	}
	
	public void addStat(MatchResult result){
		LeagueStats statsHome = leagueStats.get(result.getHomeTeam());
		if(statsHome.team == null) statsHome.team = result.getHomeTeam();
		statsHome.goals += result.getHomeGoals();
		statsHome.goalsConceeded += result.getAwayGoals();
		statsHome.points += result.getHomePoints();
		if(result.getHomePoints() == 3) statsHome.wins++;
		if(result.getHomePoints() == 1) statsHome.draws++;
		if(result.getHomePoints() == 0) statsHome.losses++;
		
		LeagueStats statsAway = leagueStats.get(result.getAwayTeam());
		if(statsAway.team == null) statsAway.team = result.getAwayTeam();
		statsAway.goals += result.getAwayGoals();
		statsAway.goalsConceeded += result.getHomeGoals();
		statsAway.points += result.getAwayPoints();
		if(result.getAwayPoints() == 3) statsAway.wins++;
		if(result.getAwayPoints() == 1) statsAway.draws++;
		if(result.getAwayPoints() == 0) statsAway.losses++;
	}
	
	public void printTable(){
		System.out.format("%-2s%-25s%-10s%-15s%-18s%-8s%-8s%-8s\n", new String[]{"","Team","Points","Goals Scored","Goals Conceeded","Wins","Draws","Losses"});
		System.out.println("----------------------------------------------------------------------------------");
		ArrayList<LeagueStats> leagueStatsArray = new ArrayList<LeagueStats>(leagueStats.values());
		Utils.sortArray(leagueStatsArray);
		for(int i = 0; i <leagueStatsArray.size();i++) {
			LeagueStats stats = leagueStatsArray.get(i);
		    System.out.format("%-2s%-25s%-10s%-15s%-18s%-8s%-8s%-8s\n", new String[]{""+(i+1),stats.team.name,""+stats.points,""+stats.goals,""+stats.goalsConceeded,""+stats.wins,""+stats.draws,""+stats.losses});
		}
	}
	
	
	


}
