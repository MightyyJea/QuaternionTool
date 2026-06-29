package com.jea;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class OIIASound {
    public static final DeferredRegister<SoundEvent> SOUND_EVENT = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT,QuaternionTool.MODID);
    public static final Supplier<SoundEvent> OIIA = SOUND_EVENT.register("oiia",()-> SoundEvent.createVariableRangeEvent(QuaternionTool.fromMod("oiia")));
    public static void init(){

    }

}
