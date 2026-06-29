package com.jea;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.resources.Identifier;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.util.Mth;
import org.joml.Vector2fc;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.io.IOException;
import java.util.ArrayList;


public class testEntityRenderer extends EntityRenderer<testEntity, testEntityRenderState> {
    private final ArrayList<Face> IdleFaces;
    private final ArrayList<Face> SpinningFaces;
    private final SpriteMapper CAT_MAPPER = new SpriteMapper(QuaternionTool.fromMod("textures/atlas/cat.png"),"entity/cat");
    private final SpriteId CAT_SPRITE = CAT_MAPPER.apply(QuaternionTool.fromMod("catsprite"));
    private final float AnglePerSecond = 0.73f;

    private RenderType CAT = RenderType.create("cat_rendertype", RenderSetup.builder(QuaternionToolClient.CAT).withTexture("Sampler0",QuaternionTool.fromMod("textures/entity/cat/cat.png")).createRenderSetup());

    {
        try {
            IdleFaces = OBJLoader.readObj("C:\\Users\\Jean\\Desktop\\Code\\Java\\quaterniontool-template-26.2\\src\\main\\resources\\assets\\quaterniontool\\obj\\cat\\cat_idle.obj");
            SpinningFaces = OBJLoader.readObj("C:\\Users\\Jean\\Desktop\\Code\\Java\\quaterniontool-template-26.2\\src\\main\\resources\\assets\\quaterniontool\\obj\\cat\\cat_spinning.obj");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected testEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public testEntityRenderState createRenderState() {
        return new testEntityRenderState();
    }

    @Override
    public void extractRenderState(testEntity entity, testEntityRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        if(!Minecraft.getInstance().isPaused()){
            entity.catSpinner.tick(partialTicks);
            if(entity.catSpinner.isSpinning){
                entity.setYRot(entity.yRotO+entity.catSpinner.currentSpinMoment.speed().radians*partialTicks);
                state.angleProgression = entity.yRotO + entity.catSpinner.currentSpinMoment.speed().radians*partialTicks;
                state.jumpProgression = (float) Math.abs(Math.sin(state.angleProgression/2))*0.2f;
                state.isSpinning = entity.catSpinner.isSpinning;
            }
        }
    }

    @Override
    public void submit(testEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        super.submit(state, poseStack, submitNodeCollector, camera);
        //OIIA OIIA
        poseStack.pushPose();
        poseStack.scale(1.8f,1.8f,1.8f);

        poseStack.mulPose(Axis.YP.rotation(state.angleProgression));
        poseStack.translate(0,state.jumpProgression,0);
        submitNodeCollector.submitCustomGeometry(poseStack, CAT,    ((pose, vertexConsumer) -> {
                    ArrayList<Face> chooseFaces;
                    if(state.isSpinning){
                        chooseFaces = SpinningFaces;
                    }else {
                        chooseFaces = IdleFaces;
                    }
                    for (Face face : chooseFaces) {
                        for (int i = 0; i < face.vertices().length; i++) {
                            Vector3fc vertex = face.vertices()[i];
                            Vector3fc normal = face.normals()[i];
                            Vector2fc texCoord = face.texCoords()[i];
                            vertexConsumer.addVertex(pose,vertex).setUv(texCoord.x(),texCoord.y()).setNormal(normal.x(),normal.y(),normal.z()).setColor(1).setLineWidth(1f).setLight(LightCoordsUtil.FULL_BRIGHT).setOverlay(OverlayTexture.NO_OVERLAY);
                        }
                    }
                }));
        poseStack.popPose();

        //default player skin model submit
        /*MeshDefinition playermesh = PlayerModel.createMesh(CubeDeformation.NONE, true);
        ModelPart unknowmodelpart = playermesh.getRoot().bake(64,64);
        poseStack.pushPose();
        Model.Simple playerModel = new Model.Simple(unknowmodelpart, identifier -> PlayerSkinRenderCache.DEFAULT_PLAYER_SKIN_RENDER_TYPE);
        poseStack.mulPose(Axis.YP.rotationDegrees(-playerModel.root().y));
        poseStack.scale(1,-1,1);
        poseStack.translate(0.0F, -1.6010001F, 0.0F);
        submitNodeCollector.submitModel(playerModel, Unit.INSTANCE, poseStack, DefaultPlayerSkin.getDefaultTexture(), 15728880, OverlayTexture.NO_OVERLAY, 0, null);
        poseStack.popPose();*/
    }
}
