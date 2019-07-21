package net.chonacky.minecraft.mod.chicken_mod;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

public class EntityLaser extends AbstractArrowEntity {
	private World thisWorld;

	public EntityLaser(EntityType<? extends EntityLaser> entityType, World world) {
		super(entityType, world);
		this.thisWorld=world;
	}

	public EntityLaser(World worldIn, double x, double y, double z) {
		super(ChickenMod.RegistryEvents.cannon_laser, x, y, z, worldIn);
		this.thisWorld=worldIn;
	}

	public EntityLaser(World worldIn, LivingEntity shooter) {
		super(ChickenMod.RegistryEvents.cannon_laser, shooter, worldIn);
		this.thisWorld=worldIn;
	}
	

	@Override
	public void onCollideWithPlayer(PlayerEntity entityIn) {
	//Do nothing because we don't want to until this works
	//super.onCollideWithPlayer(entityIn);
	}

	public void tick() {
	  super.tick();
      if (!this.world.isRemote) 
      {
    	  ChickenMod.LOGGER.debug("Laser on Server Side : " + this.getEntityId() + "  " + this.getUniqueID().toString());
    	  if (this.inGround && this.timeInGround != 0  && this.timeInGround >= 600) 
    	  {
	    		 this.world.setEntityState(this, (byte)0);
	    		 this.remove();
    	  }
      }else { ChickenMod.LOGGER.debug("Laser on Client Side : " + this.getEntityId() + "  " + this.getUniqueID().toString() 
    				  + "   Count:" + ClientWork.GetEntityCount());	 }
	}
	
	@Override
	public IPacket<?> createSpawnPacket() {
//		try {				//inserted delay for debugging so we don't get caught with a disabled cursor
//			wait (1000);
//		} catch (Exception e) {}
		LaserSpawnPacket packet = new LaserSpawnPacket(this,this.getShooter());
	    ChickenModPacketHandler.HANDLER.send(PacketDistributor.TRACKING_CHUNK.with(()->thisWorld.getChunk(chunkCoordX, chunkCoordZ)), packet);    		 
	        return super.createSpawnPacket();
	}

	protected ItemStack getArrowStack() {
		// No Item Stack for this Entity
		return null;
	}
}
