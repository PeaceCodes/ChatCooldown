package me.peacetuba.chatcooldown.events;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.peacetuba.chatcooldown.ChatCooldown;
import me.peacetuba.chatcooldown.cc;

public class onChat implements Listener{

	private ChatCooldown main;
	
	public onChat(ChatCooldown main) {
		this.main = main;
	}
	
	@EventHandler
	public void on(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		UUID puuid = p.getUniqueId();
		int cooldown = main.cooldown;
			
		if(p.hasPermission("ChatCooldown.bypass")) {
			return;
		}
		
		if(main.isOff) {
			return;
		}
			
		if(main.getCooldownManager().getOnCooldown().containsKey(puuid)) {	
			int time = ((main.getCooldownManager().getOnCooldown().get(puuid)));
			String Time = Integer.toString(time);
			p.sendMessage(cc.chat("&cPlease wait &f" + Time + " &cseconds to talk again!"));
			e.setCancelled(true);
		} else {
			main.getCooldownManager().getOnCooldown().put(p.getUniqueId(), cooldown);
		}
		
		
	}
	
}
