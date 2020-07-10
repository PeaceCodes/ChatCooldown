package me.peacetuba.chatcooldown;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.peacetuba.chatcooldown.commands.setCooldown;
import me.peacetuba.chatcooldown.events.onChat;

public class ChatCooldown extends JavaPlugin{

	public int cooldown = 5;
	public boolean isOff = false;
	
	private chatCooldownManager CCM;
	public chatCooldownManager getCooldownManager() {
		return CCM;
	}
	
	public void onEnable() {
		CCM = new chatCooldownManager();
		runCooldown();
		getCommand("setcooldown").setExecutor(new setCooldown(this));
		Bukkit.getServer().getPluginManager().registerEvents(new onChat(this), this);
	}

	
	
	@SuppressWarnings("deprecation")
	private void runCooldown() {
		Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
			public void run() {
				for(UUID name : CCM.getOnCooldown().keySet()) {
					if(CCM.getOnCooldown().get(name) < 1) {
						CCM.getOnCooldown().remove(name);
					} else {
						CCM.getOnCooldown().put(name, CCM.getOnCooldown().get(name) - 1);
					}
				}
			}
		}, 0L, 20L);
	}
	
}
