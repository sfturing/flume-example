package cn.sfturing.flume.custom.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;
import java.util.Map;

public class TypeInterceptor implements Interceptor {

    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        Map<String, String> headers = event.getHeaders();
        String body = new String(event.getBody());
        //如果event的body包含hello则向header添加一个标签
        if (body.contains("hello")) {
            headers.put("type", "yes");
        } else {
            headers.put("type", "no");
        }
        return event;

    }

    @Override
    public List<Event> intercept(List<Event> list) {
        for (Event event : list) {
            intercept(event);
        }
        return list;
    }

    @Override
    public void close() {

    }
    public static class InterceptorBulder implements Builder {

        @Override
        public Interceptor build() {
            return new TypeInterceptor();
        }

        /**
         * 传递配置，可以将外部配置传递至内部
         *
         * @param context 配置上下文
         */
        @Override
        public void configure(Context context) {

        }
    }
}
