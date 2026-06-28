package com.jea;

import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.renderer.state.gui.pip.PictureInPictureRenderState;
import org.jspecify.annotations.Nullable;


public record VisualQuaternionRenderState(
        int x0, // The left X
        int x1, // The right X
        int y0, // The top Y
        int y1, // The bottom Y
        float scale, // The scale factor when drawing to the picture
        @Nullable ScreenRectangle scissorArea, // The rendering area
        @Nullable ScreenRectangle bounds // The bounds of the element
) implements PictureInPictureRenderState {

    // Additional constructors
    public VisualQuaternionRenderState(int x, int y, int width, int height, @Nullable ScreenRectangle scissorArea) {
        this(
                x, // x0
                x + width, // x1
                y, // y0
                y + height, // y1
                1f, // scale
                scissorArea,
                PictureInPictureRenderState.getBounds(x, y, x + width, y + height, scissorArea)
        );
    }
}