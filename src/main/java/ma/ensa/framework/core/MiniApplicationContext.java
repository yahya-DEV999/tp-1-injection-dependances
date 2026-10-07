package ma.ensa.framework.core;

import java.util.HashMap;
import java.util.Map;

public class MiniApplicationContext {

    private final Map<Class<?>, Object> beans = new HashMap<>();

    public void registerBean(Class<?> type, Object instance) {
        beans.put(type, instance);
    }

    public <T> T getBean(Class<T> type) {
        Object bean = beans.get(type);

        if (bean == null) {
            throw new RuntimeException(
                    "Aucun bean trouvé pour : " + type.getName()
            );
        }

        return type.cast(bean);
    }
}