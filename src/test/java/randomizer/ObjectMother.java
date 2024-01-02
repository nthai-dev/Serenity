package randomizer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jeasy.random.EasyRandom;
import randomizer.common.RandomizerSupport;

import java.util.function.Consumer;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectMother {

    private static final EasyRandom RANDOMIZER = new EasyRandom(
            RandomizerSupport.getStandardRandomParameters()
                    .randomizerRegistry(new TestRandomizerRegistry()));

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> T next(Class<T> clazz) {
        return RANDOMIZER.nextObject(clazz);
    }

    public static <T> T next(Class<T> clazz,
                             Consumer<T> customizer) {
        T obj = next(clazz);
        customizer.accept(obj);
        return obj;
    }

    @SneakyThrows
    public static <T> T clone(T source,
                              Consumer<T> customizer) {

        T cloned = (T) OBJECT_MAPPER.readValue(
                OBJECT_MAPPER.writeValueAsString(source),
                source.getClass());
        customizer.accept(cloned);
        return cloned;
    }
}
