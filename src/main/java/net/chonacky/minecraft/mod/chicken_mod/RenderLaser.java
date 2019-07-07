package net.chonacky.minecraft.mod.chicken_mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderLaser extends ArrowRenderer<EntityLaser> {

	
    public static final Logger LOGGER = LogManager.getLogger();
    
	public RenderLaser(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}


	@Override
	protected ResourceLocation getEntityTexture(EntityLaser entity) {
		return new ResourceLocation(ChickenMod.MODID,"textures/entity/canon_laser.png");
	}


	@Override
	public void doRender(EntityLaser entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	

}
