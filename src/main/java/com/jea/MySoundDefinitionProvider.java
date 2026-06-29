package com.jea;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

public class MySoundDefinitionProvider extends SoundDefinitionsProvider {
    /**
     * Creates a new instance of this data provider.
     *
     * @param output The {@linkplain PackOutput} instance provided by the data generator.
     * @param modId  The mod ID of the current mod.
     */
    protected MySoundDefinitionProvider(PackOutput output) {
        super(output, QuaternionTool.MODID);
    }

    @Override
    public void registerSounds() {
        add(OIIASound.OIIA.get(), SoundDefinition.definition()
                .with(sound(QuaternionTool.fromMod("oiia_sound"), SoundDefinition.SoundType.SOUND)
                        .volume(4f)
                        .preload(true))
                .subtitle("sound.quaterniontool.oiia")
        );
    }
}
