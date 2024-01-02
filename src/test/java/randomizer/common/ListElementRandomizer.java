package randomizer.common;

import lombok.SneakyThrows;
import org.apache.commons.lang3.concurrent.LazyInitializer;
import org.jeasy.random.api.Randomizer;

import java.util.List;

import static org.apache.commons.lang3.RandomUtils.nextInt;

public abstract class ListElementRandomizer<T>
        extends LazyInitializer<List<T>>
        implements Randomizer<T> {
    @SneakyThrows
    @Override
    public final T getRandomValue() {
        List<T> elements = this.get();
        return elements.get(nextInt(0, elements.size()));
    }
}
