package com.shamison;

import java.util.Calendar;

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

	private Twitter twitter;
	private String ACCESSTOKEN = null;
	private String ACCESSSECRET = null;
	private String CONSUMERKEY = null;
	private String CONSUMERSECRET = null;


	public void onEnable(){
		getLogger().info("enable");
		getServer().getPluginManager().registerEvents(this, this);
		TwitterFactory tf = new TwitterFactory();
		twitter = tf.getInstance();
		CONSUMERKEY = "xxx";
		CONSUMERSECRET = "xxx";
		ACCESSTOKEN = "xxx";
		ACCESSSECRET = "xxx";
		twitter.setOAuthConsumer(CONSUMERKEY, CONSUMERSECRET);
		twitter.setOAuthAccessToken(new AccessToken(ACCESSTOKEN, ACCESSSECRET));
	}

	public void onDisable(){
		getLogger().info("disable");
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		Material block = event.getBlock().getType();
		if(block == Material.GRAVEL){
			Player player = event.getPlayer();
			this.Tweet("あぁ＾〜"+ player.getName() +"のこころじゃりじゃりするんじゃあ＾〜 [" + this.time() + "]");
		}
	}

	public String time(){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		String time = year + "/" + month + "/" + day +  " " + hour + ":" + minute + ":" + second ;
		return time;
	}


	public void Tweet(String tweet){
		StatusUpdate update = new StatusUpdate(tweet);
		try {
			@SuppressWarnings("unused")
			Status status = twitter.updateStatus(update);
			System.out.println("success.");
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

}
