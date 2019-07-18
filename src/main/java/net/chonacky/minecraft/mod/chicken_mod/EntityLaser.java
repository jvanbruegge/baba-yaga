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
    	  if (this.inGround && this.timeInGround != 0  && this.timeInGround >= 600) {
	    		 this.world.setEntityState(this, (byte)0);
	    		 this.remove();
    	  }
	}
	
	@Override
	public IPacket<?> createSpawnPacket() {
	    ChickenModPacketHandler.HANDLER.send(PacketDistributor.TRACKING_CHUNK.with(()->thisWorld.getChunk(chunkCoordX, chunkCoordZ)), new LaserSpawnPacket(this,this.getShooter()));    		 
	        return super.createSpawnPacket();
	}

	protected ItemStack getArrowStack() {
		// No Item Stack for this Entity
		return null;
	}
}
