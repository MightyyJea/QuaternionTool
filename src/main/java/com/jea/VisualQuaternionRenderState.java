package com.jea;

import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.renderer.state.gui.pip.PictureInPictureRenderState;
import org.jspecify.annotations.Nullable;

public class VisualQuaternionRenderState implements PictureInPictureRenderState {
    public VisualQuaternionRenderState(ScreenRectangle screenRectangle){
        screensRectangle = screenRectangle;
    }
    ScreenRectangle screensRectangle;
    @Override
    public int x0() {
        return 0;
    }

    @Override
    public int x1() {
        return 50;
    }

    @Override
    public int y0() {
        return 0;
    }

    @Override
    public int y1() {
        return 50;
    }

    @Override
    public float scale() {
        return 50f;
    }

    @Override
    public @Nullable ScreenRectangle scissorArea() {
        return screensRectangle;
    }

    @Override
    public @Nullable ScreenRectangle bounds() {
        return screensRectangle;
    }
}
