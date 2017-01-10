package io.github.mc_umod;

import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.stats.*;
import net.minecraftforge.common.*;

public class UAchievement {
	
	public static AchievementPage uachievementpage;
	public static Achievement firstjoin;
	
	public UAchievement() {
		init();
		register();
	}
	
	private void init() {
		firstjoin = new Achievement("uachievement.firstjoin", "firstjoin", 0, 0, new ItemStack(Blocks.LOG), (Achievement) null);
		uachievementpage = new AchievementPage("achievementpage.upage", new Achievement[] { firstjoin });
		UMod.log.debug("Init Achievements");
	}
	
	private void register() {
		firstjoin.registerStat();
		AchievementPage.registerAchievementPage(uachievementpage);
		UMod.log.debug("Register Achievements");
	}
}
