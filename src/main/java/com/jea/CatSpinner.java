package com.jea;

import java.util.ArrayList;

public class CatSpinner {
    public ArrayList<SpinMoment> spinMoments;
    public float timeElapse;
    public boolean isSpinning;
    public SpinMoment currentSpinMoment;
    public static SpinMoment NONE = new SpinMoment(SpinSpeed.NORMAL,0,0);
    public CatSpinner(){
        timeElapse = 0;
        isSpinning = false;

        this.spinMoments = new ArrayList<>();
        initializeWithDefaultData();
    }
    public void tick(float partialTick){
        timeElapse += partialTick;
        for (SpinMoment spinMoment : spinMoments) {
            if(spinMoment.tickTimeCodeStart < timeElapse && spinMoment.tickTimeCodeEnd > timeElapse){
                isSpinning = true;
                currentSpinMoment = spinMoment;
                break;
            }
            isSpinning =false;
        }
        QuaternionTool.LOGGER.info("time elapse : "+timeElapse);
    }
    public SpinMoment getCurrentSpinMoment(){
        if (isSpinning){

        }
        return new SpinMoment(SpinSpeed.NORMAL,0,0);
    }
    private void initializeWithDefaultData() {
        spinMoments.add(new SpinMoment(SpinSpeed.NORMAL, 31F, 185f));       // 60 RPM → 6.28 rad/s
        spinMoments.add(new SpinMoment(SpinSpeed.NORMAL, 315, 515f));    // 120 RPM → 12.57 rad/s
        spinMoments.add(new SpinMoment(SpinSpeed.WTF, 610, 3030f));    // 120 RPM → 12.57 rad/s
        spinMoments.add(new SpinMoment(SpinSpeed.WTF, 3300, 4350f));    // 120 RPM → 12.57 rad/s
        spinMoments.add(new SpinMoment(SpinSpeed.NORMAL, 4500, 10000f));    // 120 RPM → 12.57 rad/s
    }


    public record SpinMoment(SpinSpeed speed, float tickTimeCodeStart, float tickTimeCodeEnd){}
    public enum SpinSpeed {
        NORMAL(0.73f),
        WTF(1.3f);
        public float radians;
         SpinSpeed(float rad){
            radians = rad;
        }
    }
}
