package com.jea;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.render.pip.PictureInPictureRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.SubmitNodeStorage;
import net.minecraft.client.renderer.feature.GizmoFeatureRenderer;
import net.minecraft.client.renderer.gizmos.DrawableGizmoPrimitives;
import net.minecraft.client.renderer.state.gui.pip.PictureInPictureRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.gizmos.Gizmos;
import net.minecraft.gizmos.TextGizmo;
import net.minecraft.world.phys.Vec3;

public class VisualQuaternionPIPRenderer extends PictureInPictureRenderer<VisualQuaternionRenderState> {

    @Override
    public Class getRenderStateClass() {
        return VisualQuaternionRenderState.class;
    }

    @Override
    protected void renderToTexture(VisualQuaternionRenderState visualQuaternionRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector) {

        DrawableGizmoPrimitives gizmotodraw = new DrawableGizmoPrimitives();
        gizmotodraw.addText(new Vec3(2f,2f,1f), "ma bite", TextGizmo.Style.forColor(25));
        gizmotodraw.submit(submitNodeCollector, Minecraft.getInstance().gameRenderer.gameRenderState().levelRenderState.cameraRenderState,false );
        Minecraft.getInstance().gameRenderer.featureRenderDispatcher().renderAllFeatures((SubmitNodeStorage) submitNodeCollector);
    }
    @Override
    protected String getTextureLabel() {
        return "";
    }
}
