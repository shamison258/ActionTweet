package com.shamison;

import com.shamison.command.OAuthCommand;
import com.shamison.event.DieEventListener;
import com.shamison.tw.Tw;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	private String pin;



	public void onEnable(){
		getLogger().info("enable");
		getServer().getPluginManager().registerEvents(new DieEventListener(new Tw(this)), this);
		this.saveDefaultConfig();


	}

	private void setCmd() {
		String ck = this.getConfig().getString("CONSUMERKEY");
		String cs =	this.getConfig().getString("CONSUMERSECRET");
		if (ck.length() < 1 || cs.length() < 1){
			getLogger().warning("YOU SHOULD SET CONSUMER KEYs!!");
		}
		getServer().getPluginCommand("oauth").setExecutor(new OAuthCommand(this));
	}

	public void onDisable(){
		getLogger().info("disable");
	}



}
