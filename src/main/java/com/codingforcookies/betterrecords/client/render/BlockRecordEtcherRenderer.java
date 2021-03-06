package com.codingforcookies.betterrecords.client.render;

import com.codingforcookies.betterrecords.common.block.tile.TileEntityRecordEtcher;
import com.codingforcookies.betterrecords.common.lib.StaticInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class BlockRecordEtcherRenderer extends TileEntitySpecialRenderer {

    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale, int destroyStage) {
        if(!(te instanceof TileEntityRecordEtcher)) {
            GL11.glPushMatrix();
            {
                GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
                GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
                bindTexture(StaticInfo.modelRecordEtcherRes);
                StaticInfo.modelRecordEtcher.render(null, 0, 0, 0F, 0.0F, 0.0F, 0.0625F);
            }
            GL11.glPopMatrix();
            return;
        }


        TileEntityRecordEtcher tileEntityRecordEtcher = (TileEntityRecordEtcher)te;

        if(tileEntityRecordEtcher.recordEntity != null) {
            GL11.glPushMatrix();
            {
                GL11.glTranslatef((float)x + .5F, (float)y + .65F, (float)z + .5F);
                GL11.glScalef(2F, 2F, 2F);
                GL11.glRotatef(90F, 1F, 0F, 0F);
                GL11.glRotatef(tileEntityRecordEtcher.recordRotation * 57.3F, 0F, 0F, 1F);
                GL11.glTranslatef(0F, -.35F, 0F);
                if(Minecraft.getMinecraft().gameSettings.fancyGraphics)
                    Minecraft.getMinecraft().getRenderManager().renderEntityWithPosYaw(tileEntityRecordEtcher.recordEntity, 0, 0, 0, 0, 0);
                else{
                    Minecraft.getMinecraft().gameSettings.fancyGraphics = true;
                    Minecraft.getMinecraft().getRenderManager().renderEntityWithPosYaw(tileEntityRecordEtcher.recordEntity, 0, 0, 0, 0, 0);
                    Minecraft.getMinecraft().gameSettings.fancyGraphics = false;
                }
            }
            GL11.glPopMatrix();
        }

        GL11.glPushMatrix();
        {
            GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
            bindTexture(StaticInfo.modelRecordEtcherRes);
            StaticInfo.modelRecordEtcher.render(null, tileEntityRecordEtcher.needleLocation, tileEntityRecordEtcher.recordRotation, 0F, 0.0F, 0.0F, 0.0625F);
        }
        GL11.glPopMatrix();
    }
}
