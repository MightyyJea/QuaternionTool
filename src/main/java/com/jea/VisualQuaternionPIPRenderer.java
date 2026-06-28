package com.jea;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.render.pip.PictureInPictureRenderer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.PlayerSkinRenderCache;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.SubmitNodeStorage;
import net.minecraft.client.renderer.block.MovingBlockRenderState;
import net.minecraft.client.renderer.feature.GizmoFeatureRenderer;
import net.minecraft.client.renderer.gizmos.DrawableGizmoPrimitives;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.gui.GuiRenderState;
import net.minecraft.client.renderer.state.gui.pip.PictureInPictureRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.gizmos.Gizmos;
import net.minecraft.gizmos.TextGizmo;
import net.minecraft.network.chat.Style;
import net.minecraft.util.ARGB;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.world.phys.Vec3;

public class VisualQuaternionPIPRenderer extends PictureInPictureRenderer<VisualQuaternionRenderState> {

    @Override
    public Class<VisualQuaternionRenderState> getRenderStateClass() {
        return VisualQuaternionRenderState.class;
    }

    @Override
    protected void renderToTexture(VisualQuaternionRenderState visualQuaternionRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector) {
        DrawableGizmoPrimitives gizmotodraw = new DrawableGizmoPrimitives();
        gizmotodraw.addText(new Vec3(2f,2f,1f), "ma bite",
                TextGizmo.Style.forColor(ARGB.color(1,1,1)));
        gizmotodraw.submit(submitNodeCollector,
                Minecraft.getInstance().gameRenderer.gameRenderState().levelRenderState.cameraRenderState,
                false );

        //modelpart submit test
        MeshDefinition playermesh = PlayerModel.createMesh(CubeDeformation.NONE, true);
        ModelPart unknowmodelpart = playermesh.getRoot().bake(1,1);
        poseStack.pushPose();
        poseStack.translate(1,0,1);
        poseStack.scale(100,100,100);
        submitNodeCollector.submitModelPart(unknowmodelpart, poseStack, PlayerSkinRenderCache.DEFAULT_PLAYER_SKIN_RENDER_TYPE, LightCoordsUtil.FULL_BRIGHT, OverlayTexture.NO_OVERLAY,null);
        submitNodeCollector.submitText(poseStack,50,50, FormattedCharSequence.forward("ma bite", Style.EMPTY),true, Font.DisplayMode.SEE_THROUGH, LightCoordsUtil.FULL_BRIGHT,ARGB.color(1,0,0),0,0 );
        poseStack.popPose();
    }
    @Override
    protected String getTextureLabel() {
        return "quaternion:test";
    }

}
