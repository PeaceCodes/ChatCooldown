package me.peacetuba.chatcooldown;

import java.util.HashMap;
import java.util.UUID;

public class chatCooldownManager {

	private final HashMap<UUID, Integer> onCooldown;
	
	public chatCooldownManager() {
		onCooldown = new HashMap<UUID, Integer>();
	}
	
	public HashMap<UUID, Integer> getOnCooldown(){
		return onCooldown;
	}
	
}
