package randomizer.object;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.api.Randomizer;
import randomizer.common.RandomizerSupport;

public abstract class BaseRandomizer<T> implements Randomizer<T> {

    private final EasyRandom generator;
    private final Class<T> clazz;

    protected BaseRandomizer(Class<T> clazz) {
        this.clazz = clazz;
        this.generator = new EasyRandom(this.getRandomParameters());
    }

    private EasyRandomParameters getRandomParameters() {
        EasyRandomParameters randomParameters = RandomizerSupport.getStandardRandomParameters();
        return customizeRandomParameters(randomParameters);
    }

    protected EasyRandomParameters customizeRandomParameters(EasyRandomParameters randomParameters) {
        // Default: no customization needed
        return randomParameters;
    }

    @Override
    public final T getRandomValue() {
        return generator.nextObject(clazz);
    }
}
