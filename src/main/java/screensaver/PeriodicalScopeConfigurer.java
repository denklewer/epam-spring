package screensaver;

import javafx.util.Pair;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.lang.Nullable;


import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class PeriodicalScopeConfigurer implements Scope {

    Map<String, Pair<LocalTime,Object>> map = new HashMap();
    public Object get(String name, ObjectFactory<?> objectFactory) {
     if (map.containsKey(name)){
         Pair<LocalTime, Object> pair = map.get(name);
         int secondaSinsLastRequest = LocalTime.now().getSecond()-pair.getKey().getSecond();
         if (secondaSinsLastRequest>5){
             map.put(name,new Pair(LocalTime.now(),objectFactory.getObject()));
         }
         } else {
         map.put(name,new Pair(LocalTime.now(),objectFactory.getObject()));
     }
        return map.get(name).getValue();
    }

    @Nullable
    public Object remove(String s) {
        return null;
    }

    public void registerDestructionCallback(String s, Runnable runnable) {

    }

    @Nullable
    public Object resolveContextualObject(String s) {
        return null;
    }

    @Nullable
    public String getConversationId() {
        return null;
    }
}
