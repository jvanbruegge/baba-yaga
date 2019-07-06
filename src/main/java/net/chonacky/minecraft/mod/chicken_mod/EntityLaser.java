package net.chonacky.minecraft.mod.chicken_mod;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityLaser extends AbstractArrowEntity {

	public EntityLaser(EntityType<? extends EntityLaser> entityType, World world) {
		super(entityType, world);
		// TODO Auto-generated constructor stub
	}

	public EntityLaser(World worldIn, double x, double y, double z) {
	      super(ChickenMod.RegistryEvents.cannon_laser, x, y, z, worldIn);
	   }

	public EntityLaser(World worldIn, LivingEntity shooter) {
	      super(ChickenMod.RegistryEvents.cannon_laser, shooter, worldIn);
	   }

	@Override
	protected ItemStack getArrowStack() {
		// TODO Auto-generated method stub
		return null;
	}


	public void tick() {
	  super.tick();
      if (!this.world.isRemote) 
    	  if (this.inGround && this.timeInGround != 0  && this.timeInGround >= 600) 
	    		 this.world.setEntityState(this, (byte)0);
	}
}
