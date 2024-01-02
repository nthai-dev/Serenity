package integration.step.common;


import static integration.step.common.Stepper.step;

public class Sleeper {

    public static void waitUntilNextSecond() {
        step("wait until next second", () -> {
            long currentTime = System.currentTimeMillis();
            long waitTime = ((currentTime / 1000) + 1) * 1000 - currentTime + 1;
            Thread.sleep(waitTime);
        });
    }

}
