package utilities;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private final ThreadLocal<Map<String, Object>> scenarioContext;

    public ScenarioContext() {
        scenarioContext = ThreadLocal.withInitial(HashMap::new);
    }

    public Object getScenarioContext(String key) {
        return scenarioContext.get().get(key);
    }

    public void setScenarioContext(String key, Object value) {
        this.scenarioContext.get().put(key, value);
    }
}
