package com.david.giczi.pairsgame.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class PairsGameLogic {

	
	private  List<Integer> guessedNumbers;
	private int previousClickedButtonNumber=-1;
	private int pairCounter=0;
	private int clickCounter=0;
	private int secCounter=0;


	public List<Integer> getGuessedNumbers() {
		return guessedNumbers;
	}
		

	public int getPairCounter() {
		return pairCounter;
	}


	public int getClickCounter() {
		return clickCounter;
	}
	
	

	public int getSecCounter() {
		return secCounter;
	}


	public void setSecCounter(int secCounter) {
		this.secCounter = secCounter;
	}

	
	
	public int getPreviousClickedButtonNumber() {
		return previousClickedButtonNumber;
	}

	

	public void setGuessedNumbers(List<Integer> guessedNumbers) {
		this.guessedNumbers = guessedNumbers;
	}


	public void setPreviousClickedButtonNumber(int previousClickedButtonNumber) {
		this.previousClickedButtonNumber = previousClickedButtonNumber;
	}


	public void setPairCounter(int pairCounter) {
		this.pairCounter = pairCounter;
	}


	public void setClickCounter(int clickCounter) {
		this.clickCounter = clickCounter;
	}


	public void generatingNumber() {
		
		guessedNumbers = new ArrayList<>();
		Random rnd=new Random();
		 
		 HashSet<Integer> oneStore=new HashSet<>();
		 HashSet<Integer> twoStore=new HashSet<>();
		 
		 while(oneStore.size()<10 || twoStore.size()<10) {
			 
			 oneStore.add(rnd.nextInt(10)+1);
			 twoStore.add(rnd.nextInt(10)+1);
	
		 }
		 
		 guessedNumbers.addAll(oneStore);
		 guessedNumbers.addAll(twoStore);
		 
		 Collections.shuffle(guessedNumbers);
		

	}
	
	
	public String[][] convertingRankingData(List<Result> data){
		
		Collections.sort(data);
		
		String[][] store=new String[data.size()][5];
		
		for(int i=0; i<data.size(); i++) {
			
			
		store[i][0] = String.valueOf(i+1);
		store[i][1] = data.get(i).getName();
		store[i][2] = String.valueOf(data.get(i).getSteps());
		store[i][3] = String.valueOf(data.get(i).getGamePeriodInSec());
		store[i][4] = data.get(i).getDate();
		
		}
		
		
		return store;
	}
	
	
	public boolean wasClickedTheButtonInPrevoiusStep(String buttonText) {
		
		int clickedButtonNumber=Integer.parseInt(buttonText);
		
		clickCounter++;
		
		
		if(previousClickedButtonNumber==clickedButtonNumber) {
			
			pairCounter++;
			
			previousClickedButtonNumber=-1;
			
			return true;
		}
		
		previousClickedButtonNumber=clickedButtonNumber;
		
		
		if(clickCounter%2==0) {
			previousClickedButtonNumber=-1;
		}
		
		
		return false;
	}
	
	
	public void init() {
		
		generatingNumber();
		
		 previousClickedButtonNumber=-1;
		 pairCounter=0;
		 clickCounter=0;
		 secCounter=0;
	}


	public String createTimeStamp() {
		
		Date date=new Date(System.currentTimeMillis());
		SimpleDateFormat format=new SimpleDateFormat("dd.MM.YYYY (EEEE) HH:mm:ss", Locale.ENGLISH);
		
		return format.format(date);
	}
	
}
