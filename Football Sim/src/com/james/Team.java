package com.james;

public class Team {
	
	int id;
	String name;
	double attack;
	double defence;
	
	double penalites;
	double freekicks;
	
	int winStreak = 0;
	int loseStreak = 0;
	int trophiesWon = 0;
	double averagePosition = 0;
	double totalPositions = 0;
	double leaguesPlayed = 0;
	
	public Team(int id, String name, double attack, double defence, double pens, double freekicks){
		this.id = id;
		this.name = name;
		this.attack = ratioToProbability2(attack) ;
		this.defence = ratioToProbability(defence);
		this.penalites = pens;
		this.freekicks = freekicks;
		System.out.println("Generated "+name+". Attack: "+this.attack+" Defence: "+this.defence);
	}
	
	public static double ratioToProbability(double ratio){
		return ((Math.exp(0.287682*ratio)/Math.exp(0.287682))+1.8)/3;
	}
	
	public static double ratioToProbability2(double ratio){
		return 0.22*(Math.exp(1.5*ratio)/Math.exp(1.5));
	}

}
