package com.jea;

import com.mojang.math.Transformation;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.client.renderer.item.CuboidItemModelWrapper;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.RangeSelectItemModel;
import net.minecraft.client.renderer.item.properties.numeric.Time;
import net.minecraft.data.PackOutput;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OIIAItemModelProvider extends ModelProvider {
    public OIIAItemModelProvider(PackOutput output, String modId) {
        super(output, QuaternionTool.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        super.registerModels(blockModels, itemModels);
        itemModels.itemModelOutput.register(
                QuaternionTool.OIIA_ITEM.get(),
                new ClientItem(
                        new RangeSelectItemModel.Unbaked(
                                Optional.empty(),
                                new Time(true, Time.TimeSource.RANDOM),
                                1f,
                                List.of(new RangeSelectItemModel.Entry(
                                        0.3f,
                                        new CuboidItemModelWrapper.Unbaked(
                                                ModelLocationUtils.getModelLocation(QuaternionTool.OIIA_ITEM.get(),"spinning"),
                                                Optional.empty(),
                                                Collections.emptyList()
                                        )
                                )),
                                Optional.of(
                                        new CuboidItemModelWrapper.Unbaked(
                                                // Points to 'assets/examplemod/models/item/example_item.json'
                                                ModelLocationUtils.getModelLocation(QuaternionTool.OIIA_ITEM.get(),"idle"),
                                                Optional.empty(),
                                                Collections.emptyList()
                                        )
                                )
                        ),
                        ClientItem.Properties.DEFAULT
                ));
    }
}
