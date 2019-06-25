package net.chonacky.minecraft.mod.chicken_mod;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityProtoChicken extends ChickenEntity
{

	public EntityProtoChicken(EntityType<? extends ChickenEntity> type, World worldIn) {
		super(type, worldIn);
		this.stepHeight = 2.1F;
	}
	
	   protected void registerAttributes() {
		      super.registerAttributes();
		      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
		      this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
		   }	
 
	   
	@Override
	public float getStandingEyeHeight(Pose pose, EntitySize size) {
		return (size.height * 0.95F);
	}
	
	@Override
	public EntityType<?> getType()
	{
		return ChickenMod.RegistryEvents.protochicken;
	}
	
	@Override
	public void livingTick()
	    {
	        super.livingTick();
	        if (this.world.isRemote && this.dataManager.isDirty())  {
	            this.dataManager.setClean();
	        }
	    }
	
	@Override 
    public boolean processInteract(PlayerEntity player, Hand hand)
	    {
		if (player instanceof PlayerEntity) {
			 this.mountTo(player);
			 super.processInteract(player, hand);
			 return true;
			}
		else return false;
	    }
	
	 
	protected void mountTo(PlayerEntity player)
	    {
	        player.rotationYaw = this.rotationYaw;
	        player.rotationPitch = this.rotationPitch;
	        if (!this.world.isRemote)  player.startRiding(this);
	    }

	@Override
	 public void updatePassenger(Entity passenger)
	    {
	        final float offset=0.4F;
	        super.updatePassenger(passenger);
	        float xFactor = -MathHelper.sin(this.renderYawOffset * 0.017453292F);
	        float zFactor = MathHelper.cos(this.renderYawOffset * 0.017453292F);
	        passenger.setPosition( 
	        		this.posX + (double)(offset * xFactor),
	        		this.posY + (double)(this.getEyeHeight()  - passenger.getEyeHeight() + 0.0D),
	        		this.posZ + (double)(offset * zFactor));
	        if (passenger instanceof LivingEntity)	((LivingEntity)passenger).renderYawOffset = this.renderYawOffset;
	    }

	  

	@Override
	public void travel(Vec3d vec3d)
	    {	
	        if (this.isBeingRidden() && this.canBeSteered() )	{
	            LivingEntity controllingPassenger = (LivingEntity)this.getControllingPassenger();
	            this.rotationYaw = controllingPassenger.rotationYaw;
	            this.prevRotationYaw = this.rotationYaw;
	            this.rotationPitch = controllingPassenger.rotationPitch * 0.5F;
	            this.setRotation(this.rotationYaw, this.rotationPitch);
	            this.renderYawOffset = this.rotationYaw;
	            this.rotationYawHead = this.renderYawOffset;
	            vec3d = new Vec3d(controllingPassenger.moveStrafing * 0.5F,vec3d.y,controllingPassenger.moveForward);
	            if (vec3d.z <= 0.0F) vec3d = new Vec3d(vec3d.x,vec3d.y,vec3d.z * 0.25F); 
	            this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;
	            if (this.canPassengerSteer()) {
	                this.setAIMoveSpeed((float)this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
	                super.travel(vec3d);
	            }
	            else if (controllingPassenger instanceof PlayerEntity)  {
	                this.setMotion(Vec3d.ZERO);
	            }
	            if (this.onGround)	 this.isJumping = false;
	        }
	        else {
	            this.jumpMovementFactor = 0.02F;
	            super.travel(vec3d);
	        }   
	    }

	@Override    
	public boolean canBeSteered()  
		{
	        return this.getControllingPassenger() instanceof LivingEntity;
	    }    
	    
	@Override @Nullable
    public Entity getControllingPassenger()
	    {
	        return this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);
	    }

}
