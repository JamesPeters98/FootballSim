package com.james;

import java.util.HashMap;

public class Record{
	
	HashMap<Team,Integer> records;
	private boolean most;
	private int recordsToHold;
	
	// most indicates a most record. 
	public Record(boolean most, int recordsToHold){
		this.most = most;
		this.recordsToHold = recordsToHold;
		records = new HashMap<Team,Integer>();
	}
	
	public void checkRecord(int record, Team team){
		for(int i = 0; i <= recordsToHold; i++){
			if(i < records.size()){
				Team recordTeam = (Team) records.keySet().toArray()[i];
				
			}
		}
	}
}