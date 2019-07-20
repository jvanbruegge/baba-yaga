/**
 * 
 */
package net.chonacky.minecraft.mod.chicken_mod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author philip
 *
 */
@OnlyIn(Dist.CLIENT)
public class ClientWork {

	
	public static boolean FireLaser(LaserSpawnPacket msg) {
		ClientWorld world = Minecraft.getInstance().world;				
		AbstractArrowEntity entity = new EntityLaser(world,msg.getxPos(),msg.getyPos(),msg.getzPos());
		entity.setEntityId(msg.getEntityId());
		entity.setUniqueId(msg.getUuid());
		//entity.setEntityId(msg.entityId);
		Entity shooter = world.getEntityByID(msg.getShooterId());
			if (shooter != null) {
				((AbstractArrowEntity)entity).setShooter(shooter);
			}
		world.addEntity(entity.getEntityId(),entity);
		ChickenMod.LOGGER.debug("Laser launched on client.  ID:"+entity.getEntityId()+ "   Expect: "+msg.getEntityId());
	
		return true;
	}
}
