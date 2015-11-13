package com.shamison.tw;

import com.shamison.Main;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * Created by shamison on 15/11/14.
 */
public class TwInst {

    private Twitter twitter;

    private Main plugin;
    private boolean isNeedOAuth = true;

    public TwInst(Main p) {
        this.plugin = p;

        TwitterFactory tf = new TwitterFactory();
        twitter = tf.getInstance();
        if (plugin.getConfig().getString("CONSUMERKEY").length() < 1 ||
                plugin.getConfig().getString("CONSUMERSECRET").length() < 1) {
            plugin.getLogger().warning("SET CONSUMERKEYs");
        }else{
            twitter.setOAuthConsumer(getCONSUMERKEY(), getCONSUMERSECRET());
            if (plugin.getConfig().getString("ACCESSTOKEN").length() < 1 ||
                    plugin.getConfig().getString("ACCESSSECRET").length() < 1){
                plugin.getLogger().warning("SET ACCESS KEYs");
                plugin.getLogger().warning("OR RUN /oauth COMMAND IN YOUR WORLD");
            } else {
                twitter.setOAuthAccessToken(new AccessToken(getACCESSTOKEN(), getACCESSSECRET()));
                isNeedOAuth = false;
            }
        }
    }

    public Twitter getTwitter() {
        return twitter;
    }


    private String getCONSUMERKEY() {
        return plugin.getConfig().getString("CONSUMERKEY");
    }

    private String getCONSUMERSECRET() {
        return plugin.getConfig().getString("CONSUMERSECRET");
    }

    private String getACCESSSECRET() {
        return plugin.getConfig().getString("ACCESSTOKEN");
    }

    private String getACCESSTOKEN() {
        return plugin.getConfig().getString("ACCESSSECRET");
    }

    public boolean isNeedOAuth() {
        return isNeedOAuth;
    }
}
