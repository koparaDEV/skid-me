package net.zell7z.models.base;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class WingsModelBase extends ModelBase {

    protected float getWingAngle(float maxAngle, int totalTime, double d) {
        float angle;
        float deltaTime = getAnimationTime(totalTime, d);
        if (deltaTime <= 0.5F) {
            angle = Sigmoid(-4.0F + deltaTime * 2.0F * 8.0F);
        } else {
            angle = 1.0F - Sigmoid(-4.0F + (deltaTime * 2.0F - 1.0F) * 8.0F);
        }
        angle *= maxAngle;
        return angle;
    }

    protected void setRotation(ModelRenderer model, float y) {
        model.rotateAngleY = y;
    }

    private static float Sigmoid(double value) {
        return 1.0F / (1.0F + (float)Math.exp(-value));
    }

    private float getAnimationTime(int totalTime, double d) {
        float time = (float)((System.currentTimeMillis() + d) % totalTime);
        return time / totalTime;
    }
}