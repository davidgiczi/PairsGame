package com.david.giczi.pairsgame.model;

public class Result implements Comparable<Result>{

	
	private int id;
	private String name;
	private int steps;
	private int gamePeriodInSec;
	private String date;
	
	
	public Result(int id, String name, int steps, int gamePeriodInSec, String date) {
		
		this.id = id;
		this.name = name;
		this.steps = steps;
		this.gamePeriodInSec = gamePeriodInSec;
		this.date = date;
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public int getSteps() {
		return steps;
	}


	public int getGamePeriodInSec() {
		return gamePeriodInSec;
	}


	public String getDate() {
		return date;
	}

	

	@Override
	public String toString() {
		return id + " [name=" + name + ", steps=" + steps + ", gamePeriodInSec=" + gamePeriodInSec
				+ ", date=" + date + "]";
	}


	@Override
	public int compareTo(Result o) {
		
		return this.steps + this.gamePeriodInSec>o.steps + o.gamePeriodInSec ? 1 : this.steps + this.gamePeriodInSec == o.steps + o.gamePeriodInSec ? 0 : -1;
	}
	
	
	
}
