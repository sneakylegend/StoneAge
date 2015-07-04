package me.sneaky.utils;

import me.sneaky.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreBoard implements Runnable{
	
	  final Main p;
	  
	  public ScoreBoard(Main instance)
	  {
	    this.p = instance;
	  }
	  
	  @SuppressWarnings("deprecation")
	public static void setScoreboard(Player player) throws IllegalStateException, IllegalArgumentException, Exception {
		    Scoreboard scoreboard = player.getServer().getScoreboardManager().getNewScoreboard();
		    Objective scoreboardObj = scoreboard.registerNewObjective("test", "dummy");
		    scoreboardObj.setDisplaySlot(DisplaySlot.SIDEBAR);
		    scoreboardObj.setDisplayName(ChatColor.RED + "StoneAgeKits");
		    scoreboardObj.getScore(Bukkit.getOfflinePlayer(ChatColor.GRAY + "Credits: ")).setScore(Main.instance.stats.getCredits(player));
		    scoreboardObj.getScore(Bukkit.getOfflinePlayer(ChatColor.GRAY + "Kills: ")).setScore(Main.instance.stats.getKills(player));
		    scoreboardObj.getScore(Bukkit.getOfflinePlayer(ChatColor.GRAY + "Deaths: ")).setScore(Main.instance.stats.getDeaths(player));
		    scoreboardObj.getScore(Bukkit.getOfflinePlayer(ChatColor.GRAY + "Killstreak: ")).setScore(Main.instance.stats.getCurrentKillStreak(player));
		    player.setScoreboard(scoreboard);
		}
		 
		public static Scoreboard getScoreboard(Player player) throws IllegalStateException, IllegalArgumentException, Exception {
		    if (player.getScoreboard() == null) ScoreBoard.setScoreboard(player);
		    return player.getScoreboard();
		}
		 
		public static void updateScoreboard(Player player) throws IllegalStateException, IllegalArgumentException, Exception {
			setScoreboard(player);
		}

		public void run() {
			for(Player player : Bukkit.getServer().getOnlinePlayers()){
				try {
					updateScoreboard(player);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
}