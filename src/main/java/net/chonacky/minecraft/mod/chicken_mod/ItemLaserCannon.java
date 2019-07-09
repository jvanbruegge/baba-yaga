package net.chonacky.minecraft.mod.chicken_mod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemLaserCannon extends Item  {
	
	public static final int maxDamage = 100;
	
	public ItemLaserCannon(Properties properties) {
		super (properties);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand handIn) {
		ItemStack stack = player.getHeldItem(handIn);
		if ((this.getDamage(stack)< maxDamage) ) {   
				if (!world.isRemote) {
					AbstractArrowEntity laser = new EntityLaser(world,player);
					laser.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 8.0F, 0.05F);
					world.addEntity(laser);
					} 
				if (!player.isCreative()) {
					stack.setDamage(this.getDamage(stack)+1); }	
		} else {	//Damage has reached maximum -remove laser cannon and replace with uncharged laser cannon
			stack.shrink(1); 
			return new ActionResult<ItemStack>(ActionResultType.SUCCESS,new ItemStack(ChickenMod.RegistryEvents.uncharged_laser_cannon));
		}
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS,stack);
	}
	
}