package com.shamison;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class Jarijari extends JavaPlugin implements Listener{

	Twitter twitter;

	private String ACCESSTOKEN = null;
	private String ACCESSSECRET = null;
	private String CONSUMERKEY = null;
	private String CONSUMERSECRET = null;

	public void onEnable(){
		getLogger().info("enable");
		getServer().getPluginManager().registerEvents(this, this);
		TwitterFactory tf = new TwitterFactory();
		twitter = tf.getInstance();
	}


	public void onDisable(){
		getLogger().info("disable");
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Material block = event.getBlock().getType();
		if(block == Material.GRAVEL){
			Player player = event.getPlayer();
			this.Tweet("あぁ＾〜"+ player.getName() +"のこころじゃりじゃりするんじゃあ＾〜");
		}
	}

	public void Tweet(String tweet){
		CONSUMERKEY = "xxx";
		CONSUMERSECRET = "xxx";
		ACCESSTOKEN = "xxx";
		ACCESSSECRET = "xxx";
		twitter.setOAuthConsumer(CONSUMERKEY, CONSUMERSECRET);
		twitter.setOAuthAccessToken(new AccessToken(ACCESSTOKEN, ACCESSSECRET));

		StatusUpdate update = new StatusUpdate(tweet);

		try {
			@SuppressWarnings("unused")
			Status status = twitter.updateStatus(update);
			System.out.println("OK!");
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

}
