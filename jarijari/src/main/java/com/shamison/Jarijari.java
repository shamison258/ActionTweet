package com.shamison;

import java.util.Calendar;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class Jarijari extends JavaPlugin implements Listener, CommandExecutor{

	private String pin;
	private Twitter twitter;
	private String CONSUMERKEY;
	private String CONSUMERSECRET;
	private String ACCESSSECRET;
	private String ACCESSTOKEN;
	private AccessToken accessToken;
	private RequestToken requestToken;



	public void onEnable(){
		getLogger().info("enable");
		getServer().getPluginManager().registerEvents(this, this);
		this.saveDefaultConfig();
		this.setupTwitterUtil();
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(cmd.getName().equalsIgnoreCase("oauth")){
				try {
					requestToken = twitter.getOAuthRequestToken();
					requestToken.getToken();
					requestToken.getTokenSecret();
					if (null == accessToken) {
						sender.sendMessage("clickURL: "+ requestToken.getAuthorizationURL());
						sender.sendMessage("/pin [pin] plz");
					}else{
						sender.sendMessage("don't need oauth.");
					}
				} catch (TwitterException e) {
					e.printStackTrace();
				}
			return true;
		}else if(cmd.getName().equalsIgnoreCase("pin")){
			this.pin = args[0];
			try {
				accessToken = twitter.getOAuthAccessToken(requestToken, pin);
				this.getConfig().set("ACCESSTOKEN", accessToken.getToken());
				this.getConfig().set("ACCESSECRET", accessToken.getTokenSecret());
				this.saveConfig();
				sender.sendMessage("sucess");
			} catch (TwitterException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			return true;
		}
			return false;

	}

	public void setupTwitterUtil() {
		TwitterFactory tf = new TwitterFactory();
		twitter = tf.getInstance();
		CONSUMERKEY = "Z6LpQ2jsdD8neVoCxVyVg";
		CONSUMERSECRET = "UcI6T9DqsLrpgobfztOZ3WRR4xuLjHAj4nxAE8iG8";
		ACCESSTOKEN = this.getConfig().getString("ACCESSTOKEN");
		ACCESSSECRET = this.getConfig().getString("ACCESSSECRET");
		twitter.setOAuthConsumer(CONSUMERKEY, CONSUMERSECRET);

		if(null == ACCESSTOKEN || null == ACCESSSECRET){
			return;
		}
		twitter.setOAuthAccessToken(new AccessToken(ACCESSTOKEN, ACCESSSECRET));

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

	@EventHandler
	public void onDeathLava(PlayerDeathEvent event){
		if(event.getDeathMessage().contains("lava")){
			event.setDeathMessage("溶岩の中、あったかいナリィ・・・");
			this.Tweet("溶岩の中、あったかいナリィ・・・ [" + this.time() + "]");
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
}
