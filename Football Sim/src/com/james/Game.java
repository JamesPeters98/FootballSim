package com.james;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import uk.co.codeecho.fixture.generator.Fixture;
import uk.co.codeecho.fixture.generator.FixtureGenerator;

public class Game {
	
	static FixtureGenerator fixtureGenerator = new FixtureGenerator();
	static League league = new League();
	static List<List<Fixture<Team>>> rounds;
	static Scanner reader = new Scanner(System.in);
	
	static int teamId = 1;
	static Team team;
	
	public static void main(String[] args){
		//MatchSim.runMatch(Teams.CHELSEA,Teams.LIVERPOOL,1);
				
		System.out.println("Choose your team");
		List<Team> teams = league.getTeams();
		for(int i = 0; i<teams.size(); i++){
			Team team = teams.get(i);
			System.out.println((i+1)+". "+team.name);
		}
		teamId = Utils.readNumber(reader,"Enter the number of the desired team.");
		
		team = Teams.LIVERPOOL;
		
		rounds = fixtureGenerator.getFixtures(league.getTeams(), true, teamId);
		
		System.out.println("Fixture List for "+team.name);
		System.out.println("----------------------------");
		for(int i=0; i<rounds.size(); i++){
			List<Fixture<Team>> round = rounds.get(i);
			for(Fixture<Team> fixture: round){
				if((fixture.getHomeTeam().id == teamId)||(fixture.getAwayTeam().id==teamId)){
					System.out.println("Week "+(i+1)+" "+fixture.getHomeTeam().name + " vs " + fixture.getAwayTeam().name);
				}
		    }
		}
		System.out.println("");
		
		for(int i=0; i<rounds.size(); i++){
		    System.out.println("Week " + (i+1)+" | Games remaining: "+(rounds.size()-i));
		    System.out.println("---------");
		    List<Fixture<Team>> round = rounds.get(i);
		    for(Fixture<Team> fixture: round){
		    	System.out.println(fixture.getHomeTeam().name + " vs " + fixture.getAwayTeam().name);
		    }
		    System.out.println("");
		    Utils.promptEnterKey(reader);
		    for(Fixture<Team> fixture: round){
				try {
					MatchResult result = new MatchSim().runMatch(reader,fixture.getHomeTeam(),fixture.getAwayTeam(),teamId);
					league.addStat(result);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}   
		    }
		    System.out.println("");
		    Utils.promptEnterKey(reader);
		    league.printTable();
		    Utils.promptEnterKey(reader);
		}
		league.printTable();
	}

}
