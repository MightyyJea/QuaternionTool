package com.jea;

import com.mojang.brigadier.Command;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.feature.GizmoFeatureRenderer;
import net.minecraft.client.renderer.state.gui.GuiRenderState;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import net.neoforged.neoforge.client.event.RegisterPictureInPictureRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = QuaternionTool.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = QuaternionTool.MODID, value = Dist.CLIENT)
public class QuaternionToolClient {
    public QuaternionToolClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
    @SubscribeEvent
    static void registerPip(RegisterPictureInPictureRenderersEvent event){
        event.register(VisualQuaternionRenderState.class,VisualQuaternionPIPRenderer::new);
    }
    @SubscribeEvent
    static void registerCommand(RegisterClientCommandsEvent event){
        event.getDispatcher().register(Commands.literal("quaternion")
                .executes(context -> {
                    context.getSource().source.sendSystemMessage(Component.literal("command sent"));
                    GuiGraphicsExtractor extractor = new GuiGraphicsExtractor(Minecraft.getInstance(), new GuiRenderState(),0,0);
                    Minecraft.getInstance().gui.hud.extractRenderState(extractor,Minecraft.getInstance().getDeltaTracker());

                    extractor.submitPictureInPictureRenderState(new VisualQuaternionRenderState(extractor.peekScissorStack()));

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
