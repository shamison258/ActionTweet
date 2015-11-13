package com.shamison.command;

import com.shamison.Main;
import com.shamison.tw.TwInst;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * Created by shamison on 15/11/14.
 */
public class OAuthCommand implements CommandExecutor {


    private AccessToken accessToken;
    private RequestToken requestToken;
    private Main plugin;
    private Twitter twitter;


    public OAuthCommand(Main m) {
        this.plugin = m;
        this.twitter = new TwInst(plugin).getTwitter();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        switch (cmd.getName().toLowerCase()) {
            case "oauth" :
                return oauthCmd(sender);
            case "pin" :
                return pinCmd(sender, args);
        }
        return false;
    }

    private boolean oauthCmd(CommandSender sender){
        try {
            requestToken = twitter.getOAuthRequestToken();
            requestToken.getToken();
            requestToken.getTokenSecret();
            if (null == accessToken) {
                sender.sendMessage("clickURL: "+ requestToken.getAuthorizationURL());
                sender.sendMessage("/pin [pin]");
            }else{
                sender.sendMessage("don't need oauth.");
            }
            return true;
        } catch (TwitterException e) {
            e.printStackTrace();
            return false;
        }
    }


    private boolean pinCmd(CommandSender sender, String args[]){
        String pin = args[0];
        if (pin.length() < 1)
            return false;
        try {
            accessToken = twitter.getOAuthAccessToken(requestToken, pin);
            plugin.getConfig().set("ACCESSTOKEN", accessToken.getToken());
            plugin.getConfig().set("ACCESSECRET", accessToken.getTokenSecret());
            plugin.saveConfig();
            sender.sendMessage("Sucess to OAuth");
            sender.sendMessage("reload this plugin");
            return true;
        } catch (TwitterException e) {
            e.printStackTrace();
            return false;
        }
    }
}
