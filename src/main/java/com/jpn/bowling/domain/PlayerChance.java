package com.jpn.bowling.domain;

public class PlayerChance {
	private String player;
	private String knockedDownCount;
	
	public PlayerChance (String player, String knockedDownCount) {
		this.player = player;
		this.knockedDownCount = knockedDownCount;
	}
	
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public String getKnockedDownCount() {
		return knockedDownCount;
	}
	public void setKnockedDownCount(String knockedDownCount) {
		this.knockedDownCount = knockedDownCount;
	}
	
	

}
