package cn.sfturing.flume.custom.source;

import org.apache.flume.Context;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.SimpleEvent;
import org.apache.flume.source.AbstractSource;

public class TestSource extends AbstractSource implements Configurable, PollableSource {
    private String prefix;
    private String suffix;
    @Override
    public Status process() throws EventDeliveryException {
        Status status = null;
        try {
            for (int i = 0; i < 5; i++) {
                SimpleEvent event = new SimpleEvent();
                event.setBody((prefix + "--" + i + "--" + suffix).getBytes());
                //传递数据给Channel
                getChannelProcessor().processEvent(event);
                status = Status.READY;
            }
            Thread.sleep(2000);
        } catch (Exception e) {
            status = Status.BACKOFF;
        }
        return status;
    }

    @Override
    public long getBackOffSleepIncrement() {
        return 0;
    }

    @Override
    public long getMaxBackOffSleepInterval() {
        return 0;
    }

    @Override
    public void configure(Context context) {
        this.prefix = context.getString("prefix");
        this.suffix = context.getString("suffix", "suffix");
    }
}
