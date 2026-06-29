package com.jea;

import net.minecraft.client.renderer.entity.state.EntityRenderState;

public class testEntityRenderState extends EntityRenderState {
    public float angleProgression;
    public float jumpProgression;
    public boolean isSpinning;
    public float rpm;
    public testEntityRenderState(){
        angleProgression = 0;
        jumpProgression = 0;
        isSpinning = false;
    }
}
