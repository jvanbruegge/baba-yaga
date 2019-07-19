package net.chonacky.minecraft.mod.chicken_mod;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderLaser extends ArrowRenderer<EntityLaser> {
    
	public RenderLaser(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}


	protected ResourceLocation getEntityTexture(EntityLaser entity) {
		return new ResourceLocation(ChickenMod.MODID,"textures/entity/cannon_laser.png");
	}

//	@Override
//	public void doRender(EntityLaser entity, double x, double y, double z, float entityYaw, float partialTicks) {
//		super.doRender(entity, x, y, z, entityYaw, partialTicks);
//	}
	

}
