package net.chonacky.minecraft.mod.chicken_mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderLaser<T extends AbstractArrowEntity> extends ArrowRenderer<T> {

	
    public static final Logger LOGGER = LogManager.getLogger();
    
	public RenderLaser(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		EntityType<?> entitytype = entity.getType();
		ResourceLocation resourcelocation = EntityType.getKey(entitytype);
		LOGGER.info("Laser Texture:  " + resourcelocation.getNamespace());
		return resourcelocation;
	}

}
