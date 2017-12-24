package Helper;

import java.util.UUID;

public class NameGenerator {
    public static String getName(String base) {
        UUID uuid = UUID.randomUUID();
        return base + " " + uuid.toString();
    }
}
