package randomizer.common;

import org.apache.commons.lang3.RandomStringUtils;
import org.jeasy.random.api.Randomizer;

public class FixedSizeStringRandomizer implements Randomizer<String> {
    private final int length;

    public FixedSizeStringRandomizer(int length) {
        this.length = length;
    }

    @Override
    public String getRandomValue() {
        return RandomStringUtils.randomAlphabetic(this.length);
    }
}
