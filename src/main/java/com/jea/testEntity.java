package com.jea;

import net.minecraft.client.Minecraft;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class testEntity extends Entity {
    CatSpinner catSpinner;
    public testEntity(EntityType<?> type, Level level) {
        super(type, level);
        catSpinner = new CatSpinner();
    }


    public void resetCatSpinner(){
        catSpinner = new CatSpinner();
    }
    @Override
    public void playSound(SoundEvent sound, float volume, float pitch) {
        super.playSound(sound, volume, pitch);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    @Override
    public boolean hurtServer(ServerLevel serverLevel, DamageSource damageSource, float v) {
        return false;
    }

    @Override
    protected void readAdditionalSaveData(ValueInput valueInput) {

    }

    @Override
    protected void addAdditionalSaveData(ValueOutput valueOutput) {

    }
}
