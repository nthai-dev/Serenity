package randomizer.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.randomizers.number.BigDecimalRandomizer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Random;

import static org.apache.commons.lang3.RandomUtils.nextInt;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomizerSupport {

    public static final int DEFAULT_RANDOM_STRING_SIZE = 10;

    public static EasyRandomParameters getStandardRandomParameters() {
        return new EasyRandomParameters()
                .seed(System.currentTimeMillis())
                .objectPoolSize(100)
                .randomizationDepth(10)
                .stringLengthRange(10, 50)
                .collectionSizeRange(0, 10)
                .charset(StandardCharsets.UTF_8)
                .randomize(Integer.class, RandomUtils::nextInt)
                .randomize(int.class, RandomUtils::nextInt)
                .randomize(Long.class, RandomUtils::nextLong)
                .randomize(long.class, RandomUtils::nextLong)
                .randomize(BigDecimal.class, new BigDecimalRandomizer(2, RoundingMode.HALF_UP))
                .scanClasspathForConcreteTypes(true)
                .overrideDefaultInitialization(false)
                .ignoreRandomizationErrors(true);
    }

    public static String randomAlphabetic() {
        return RandomStringUtils.randomAlphabetic(DEFAULT_RANDOM_STRING_SIZE);
    }

    public static String randomAlphabetic(int minLength, int maxLength) {
        int length = randomInt(minLength, maxLength);
        return RandomStringUtils.randomAlphabetic(length);
    }

    public static <T> T randomElement(T... array) {
        return array[nextInt(0, array.length)];
    }

    public static <T> T randomElement(Collection<T> collection) {
        int randomIndex = nextInt(0, collection.size());
        int index = 0;
        for(T t : collection) {
            if(index == randomIndex) {
                return t;
            }

            index++;
        }

        throw new AssertionError();
    }

    public static <T extends Enum<T>> T randomEnum(Class<T> enumClass,
                                                   T... excludedValues) {
        T[] enumValues = ArrayUtils.removeElements(enumClass.getEnumConstants(), excludedValues);
        return enumValues[nextInt(0, enumValues.length)];
    }

    public static int randomInt(int min, int max) {
        Random random = new Random();
        return Math.round(random.nextFloat() * (max - min) + min);
    }

    public static String randomBlankString() {
        String blankString = StringUtils.repeat(' ', randomInt(1, 10));
        return randomElement(null, "", " ", blankString);
    }

    public static String randomEmptyString() {
        return randomElement(null, "");
    }

}
