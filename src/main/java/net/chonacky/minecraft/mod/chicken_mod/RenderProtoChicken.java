package net.chonacky.minecraft.mod.chicken_mod;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderProtoChicken extends MobRenderer<EntityProtoChicken, ModelProtoChicken<EntityProtoChicken>>
{

	private static final ResourceLocation PROTOCHICKEN_TEXTURES = new ResourceLocation(ChickenMod.MODID,"textures/entity/byhut_v2.png");

    public RenderProtoChicken(EntityRendererManager manager)
    {
    	super(manager, new ModelProtoChicken<EntityProtoChicken>(), 1.75F);
    }
 
	protected ResourceLocation getEntityTexture(EntityProtoChicken entity) {
		return PROTOCHICKEN_TEXTURES;
	}

    @Override
    protected float handleRotationFloat(EntityProtoChicken livingBase, float partialTicks)
    {
        float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
        float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
        return (MathHelper.sin(f) + 1.0F) * f1;
    }
	
}