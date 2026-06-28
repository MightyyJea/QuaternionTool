package com.jea;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

public class OIIASound {
    public static final DeferredRegister<SoundEvent> SOUND_EVENT = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT,QuaternionTool.MODID);
    public static final Holder<SoundEvent> OIIA = SOUND_EVENT.register("OIIA", SoundEvent::createVariableRangeEvent);

}
