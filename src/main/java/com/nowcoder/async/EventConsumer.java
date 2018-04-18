package com.nowcoder.async;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nowcoder.server.JedisAdapter;
import com.nowcoder.util.RedisKeyUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventConsumer implements InitializingBean {
    private Map<EventType,List<EventHandler>> config = new HashMap<>();
    private ApplicationContext applicationContext;

    @Autowired
    JedisAdapter jedisAdapter;

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String ,EventHandler> beans = applicationContext.getBeansOfType(EventHandler.class);
        if(beans != null){
            for(Map.Entry<String, EventHandler> entry : beans.entrySet()) {
                List<EventType> eventTypes =  entry.getValue().getSupportEventTypes();
                for(EventType type : eventTypes) {
                    if(!config.containsKey(type)) {
                        config.put(type,new ArrayList<>());
                    }
                    config.get(type).add(entry.getValue());
                }
            }
        }
    }

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while(true) {
                String key = RedisKeyUtil.getEventQueueKey();
                List<String> events = jedisAdapter.brpop(0,key);
                for(String msg : events) {
                    if(msg.equals(key)) continue;
                    EventModel eventModel = JSON.parseObject(msg,EventModel.class);
                    for(EventHandler handler : config.get(eventModel.getType())) {
                        handler.doHandle(eventModel);
                    }

                }
            }
        }
    });
}
