package com.shamison.event;

import com.shamison.tw.Tw;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Calendar;

/**
 * Created by shamison on 15/11/14.
 */
public class DieEventListener implements Listener {

    private Tw tw;

    public DieEventListener(Tw tw) {
        this.tw = tw;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Material block = event.getBlock().getType();
        if(block == Material.GRAVEL){
            Player player = event.getPlayer();
            tw.tweet("あぁ＾〜"+
                    player.getName() +
                    "のこころじゃりじゃりするんじゃあ＾〜 [" +
                    this.time() + "]");
        }
    }

    @EventHandler
    public void onDeathLava(PlayerDeathEvent event){
        if(event.getDeathMessage().contains("lava")){
            event.setDeathMessage("溶岩の中、あったかいナリィ・・・");
            tw.tweet("溶岩の中、あったかいナリィ・・・ [" +
                    this.time() +
                    "]");
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
        return year + "/" + month + "/" + day +  " " + hour + ":" + minute + ":" + second;

    }
}
