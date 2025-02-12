package com.github.alexthe666.alexsmobs.client.render.layer;

import com.github.alexthe666.alexsmobs.client.model.ModelLeafcutterAnt;
import com.github.alexthe666.alexsmobs.client.render.OctopusColorRegistry;
import com.github.alexthe666.alexsmobs.client.render.RenderLeafcutterAnt;
import com.github.alexthe666.alexsmobs.entity.EntityLeafcutterAnt;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class LayerLeafcutterAntLeaf extends RenderLayer<EntityLeafcutterAnt, AdvancedEntityModel<EntityLeafcutterAnt>> {

    private static final ResourceLocation TEXTURE_0 = new ResourceLocation("alexsmobs:textures/entity/leafcutter_ant_leaf_0.png");
    private static final ResourceLocation TEXTURE_1 = new ResourceLocation("alexsmobs:textures/entity/leafcutter_ant_leaf_1.png");
    private static final ResourceLocation TEXTURE_2 = new ResourceLocation("alexsmobs:textures/entity/leafcutter_ant_leaf_2.png");

    public LayerLeafcutterAntLeaf(RenderLeafcutterAnt render) {
        super(render);
    }

    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityLeafcutterAnt entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entitylivingbaseIn.hasLeaf() && !entitylivingbaseIn.isQueen() && this.getParentModel() instanceof ModelLeafcutterAnt) {
            final int leafType = entitylivingbaseIn.getId() % 3;
            final ResourceLocation res = switch (leafType) {
                case 2 -> TEXTURE_2;
                case 1 -> TEXTURE_1;
                default -> TEXTURE_0;
            };
            VertexConsumer ivertexbuilder = bufferIn.getBuffer(RenderType.entityCutoutNoCull(res));
            int leafColor = Minecraft.getInstance().itemColors.getColor(new ItemStack(Items.JUNGLE_LEAVES), 0);
            if(entitylivingbaseIn.getHarvestedPos() != null && entitylivingbaseIn.getHarvestedState() != null){
                //leafColor = OctopusColorRegistry.getBlockColor(entitylivingbaseIn.getHarvestedState());
                leafColor = Minecraft.getInstance().getBlockColors().getColor(entitylivingbaseIn.getHarvestedState(), entitylivingbaseIn.level(), entitylivingbaseIn.blockPosition(), 0);
            }
            final float f = (float)(leafColor >> 16 & 255) / 255.0F;
            final float f1 = (float)(leafColor >> 8 & 255) / 255.0F;
            final float f2 = (float)(leafColor & 255) / 255.0F;
            this.getParentModel().renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, LivingEntityRenderer.getOverlayCoords(entitylivingbaseIn, 0.0F), f, f1, f2, 1.0F);


        }
    }
}
