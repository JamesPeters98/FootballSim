package com.james;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MatchSim implements KeyListener{
	
	double trials = 1;
	int totalGoals = 0;
	
	int openPlayGoalsA = 0;
	int penGoalsA = 0;
	int freeKickGoalsA = 0;
	
	int openPlayGoalsB = 0;
	int penGoalsB = 0;
	int freeKickGoalsB = 0;
	
	int pens = 0;
	int freekicks = 0;
	
	int teamAgoals;
	int teamBgoals;

	public MatchResult runMatch(Scanner scanner, Team home, Team away, int teamId) throws InterruptedException {
		
		if((teamId==home.id)||(teamId==away.id)){
			System.out.println(home.name+" vs "+away.name);
			System.out.println("-----------------------");
		}
				
		//for(int z = 1; z<=trials; z++){
			for(int i = 1; i<= 90; i++){
				Result resultA = teamLoop(away,home);
				Result resultB = teamLoop(home,away);
				
				if(resultA.goalScored()) teamAgoals++;
				if(resultA.freekickScored()) freeKickGoalsA++;
				if(resultA.penaltyScored()) penGoalsA++;
				if(resultB.goalScored()) teamBgoals++;
				if(resultB.freekickScored()) freeKickGoalsB++;
				if(resultB.penaltyScored()) penGoalsB++;
				
				if((teamId==home.id)||(teamId==away.id)){
					//System.out.print("Min "+i+" |");
					
					if(resultA.openPlayGoal()) System.out.println(i+"' GOAL! "+home.name+" "+teamAgoals+"-"+teamBgoals);
					if(resultB.openPlayGoal()) System.out.println(i+"' GOAL! "+away.name+" "+teamAgoals+"-"+teamBgoals);
					if(resultA.penaltyScored()) System.out.println(i+"' GOAL! Penalty! "+home.name+" "+teamAgoals+"-"+teamBgoals);
					if(resultB.penaltyScored()) System.out.println(i+"' GOAL! Penalty! "+away.name+" "+teamAgoals+"-"+teamBgoals);
					if(resultA.freekickScored()) System.out.println(i+"' GOAL! Freekick! "+home.name+" "+teamAgoals+"-"+teamBgoals);
					if(resultB.freekickScored()) System.out.println(i+"' GOAL! Freekick! "+away.name+" "+teamAgoals+"-"+teamBgoals);
					if((resultA.resultType == ResultType.NOTHING)&&(resultB.resultType == ResultType.NOTHING)){
						//System.out.println("");
					}
					//TimeUnit.MILLISECONDS.sleep(250);
					
					if(i==45){
						System.out.println("Half Time!"+home.name+" "+teamAgoals+"-"+teamBgoals+" "+away.name);
						//TimeUnit.SECONDS.sleep(2);
					}
					if(i==90){
						System.out.println("Game Over!");
						System.out.println(home.name+" "+teamAgoals+"-"+teamBgoals+" "+away.name);
						System.out.println("");
						Utils.promptEnterKey(scanner);
						System.out.println("Other Results");
						System.out.println("---------------");
					}
				}
			}

		
			if((teamId!=home.id)&&(teamId!=away.id))System.out.println(home.name+" "+teamAgoals+"-"+teamBgoals+" "+away.name);
		return new MatchResult(home, away, teamAgoals, teamBgoals);

	}
	
	public Result teamLoop(Team teamA, Team teamB){
		double rand = Math.random();
		//Probability of defensive error
		if(rand <= 1-teamA.defence){
			rand = Math.random();
			if(rand <= 0.02){
				pens++;
				rand = Math.random();
				if(rand <= teamB.penalites){
					return new Result(ResultType.GOAL_PENALTY);
				}
			} 
			else if(rand <= 0.08){
				freekicks++;
				rand = Math.random();
				if(rand <= teamB.freekicks){
					return new Result(ResultType.GOAL_FREE_KICK);
				}
			}
			//prob of attack
			else if(rand <= teamB.attack){
				return new Result(ResultType.GOAL_OPEN_PLAY);
			}
			
		}
		return new Result(ResultType.NOTHING);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println("Key Pressed!");
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
