package randomizer;

import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.annotation.Priority;
import org.jeasy.random.api.Randomizer;
import org.jeasy.random.api.RandomizerRegistry;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Priority(1)
public class TestRandomizerRegistry implements RandomizerRegistry {

    private final Map<Class<?>, Randomizer<?>> randomizers = new HashMap<>();

    @Override
    public void init(EasyRandomParameters parameters) {
        // API Request randomizers

    }

    @Override
    public Randomizer<?> getRandomizer(Field field) {
        return getRandomizer(field.getType());
    }

    @Override
    public Randomizer<?> getRandomizer(Class<?> type) {
        return randomizers.get(type);
    }
}
