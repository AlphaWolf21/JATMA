package abused_master.JATMA.Registry;

import java.util.Random;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MobDrops {
	
	 public static Random random;
	 public static int dropped;
	 
	 @SubscribeEvent
	 public void onEntityDrop(LivingDropsEvent event) {
	 random = new Random();
	 dropped = random.nextInt(2) + 1; //DO NOT CHANGE THIS? (+1 means at least 1 item drops..)
	 
	 //cow, extends EntityAnimal. you could use EntityTameable and so on..
	 if(event.getEntityLiving() instanceof EntityMob) {
	 event.getEntityLiving().entityDropItem(new ItemStack(Items.GOLD_NUGGET), dropped);
	 }
	 
	 // all hostile mobs
	 /*
	 if(event.getEntityLiving() instanceof EntityMob) {
	 
	 }
	 */
	 }
	
}
