package com.shamison.tw;

import com.shamison.Main;
import twitter4j.StatusUpdate;
import twitter4j.TwitterException;

/**
 * Created by shamison on 15/11/14.
 */
public class Tw {

    private TwInst inst;
    private Main plugin;

    public Tw(Main plugin) {
        this.inst = new TwInst(plugin);
        this.plugin = plugin;
    }


    public void tweet(String tweet) {
        if (!inst.isNeedOAuth()) {
            try {
                inst.getTwitter().updateStatus(new StatusUpdate(tweet));
            } catch (TwitterException e) {
                //e.printStackTrace();
                plugin.getLogger().warning("SET KEYS");
            }
        } else {
            plugin.getLogger().warning("SET KEYS PLZ");
        }
    }


}
