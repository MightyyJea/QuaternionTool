package com.jea;

import com.mojang.blaze3d.PrimitiveTopology;
import com.mojang.blaze3d.pipeline.BindGroupLayout;
import com.mojang.blaze3d.pipeline.DepthStencilState;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.shaders.UniformType;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.MeshData;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.brigadier.Command;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.data.AtlasProvider;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Hud;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.BindGroupLayouts;
import net.minecraft.client.renderer.PlayerSkinRenderCache;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.feature.FeatureRenderDispatcher;
import net.minecraft.client.renderer.feature.GizmoFeatureRenderer;
import net.minecraft.client.renderer.state.gui.GuiRenderState;
import net.minecraft.client.renderer.state.gui.pip.GuiSkinRenderState;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.client.resources.model.sprite.AtlasManager;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.PlayerSkin;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;
import org.spongepowered.asm.mixin.MixinEnvironment;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = QuaternionTool.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = QuaternionTool.MODID, value = Dist.CLIENT)
public class QuaternionToolClient {
    public static final BindGroupLayout CATLAYOUT  = BindGroupLayout.builder()
            .withSampler("Sampler0")
            .withUniform("Globals", UniformType.UNIFORM_BUFFER)
            .build();
    public static RenderPipeline CAT = RenderPipeline.builder()
            .withVertexShader("core/position_tex")
            .withFragmentShader("core/position_tex")
            .withBindGroupLayout(CATLAYOUT)
            .withBindGroupLayout(BindGroupLayouts.MATRICES_PROJECTION)
            .withVertexBinding(0,DefaultVertexFormat.POSITION_TEX)
            .withLocation(QuaternionTool.fromMod("pipeline/cat"))
            .withPrimitiveTopology(PrimitiveTopology.TRIANGLES)
            .withCull(true)
            .withDepthStencilState(DepthStencilState.DEFAULT)
            .build();
    public QuaternionToolClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
    @SubscribeEvent
    public static void gatherdata(GatherDataEvent.Client event){
        event.createProvider(MySoundDefinitionProvider::new);
    }
    @SubscribeEvent
    static void spawningCat(EntityJoinLevelEvent event){
        if(event.getEntity().getType() == QuaternionTool.TESTENTITY.get()){
            event.getEntity().playSound(OIIASound.OIIA.get(),4,1);
            ((testEntity) event.getEntity()).resetCatSpinner();
        }
    }
    @SubscribeEvent
    static void registerRenderPipeline(RegisterRenderPipelinesEvent event){
        event.registerPipeline(CAT);
    }
    @SubscribeEvent
    static void registerGuiLayer(RegisterGuiLayersEvent event){
        event.registerAbove(Identifier.withDefaultNamespace("crosshair"),QuaternionTool.fromMod("quaternion_hud"),
                (guiGraphics, deltaTracker) ->{
                    //guiGraphics.submitPictureInPictureRenderState(new VisualQuaternionRenderState(0,0, guiGraphics.guiWidth(), guiGraphics.guiHeight(), guiGraphics.peekScissorStack()));
                    MeshDefinition def = PlayerModel.createMesh(CubeDeformation.NONE, false);
                    guiGraphics.submitPictureInPictureRenderState(new GuiSkinRenderState(
                            new Model.Simple(def.getRoot().bake(1,1),(identifier -> PlayerSkinRenderCache.DEFAULT_PLAYER_SKIN_RENDER_TYPE)),
                                    DefaultPlayerSkin.getDefaultSkin().body().texturePath(),
                                    0f,
                                    0f,
                                    0f,
                                    50,
                                    50,
                                    200,
                                    200,
                                    1f,
                                    guiGraphics.peekScissorStack()
                                    ));
                }

                );
    }
    @SubscribeEvent
    static void registerPip(RegisterPictureInPictureRenderersEvent event){
        event.register(VisualQuaternionRenderState.class,VisualQuaternionPIPRenderer::new);
    }
    @SubscribeEvent
    static void registerCommand(RegisterClientCommandsEvent event){
        event.getDispatcher().register(Commands.literal("quaternion")
                .executes(context -> {
                    return 1;
                }));
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        QuaternionTool.LOGGER.info("HELLO FROM CLIENT SETUP");
        QuaternionTool.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }
}
