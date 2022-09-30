package cn.sfturing.flume.custom.sink;

import com.google.common.base.Charsets;
import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomSink extends AbstractSink implements Configurable {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomSink.class);

    private String prefix;
    private String end;

    @Override
    public Status process() throws EventDeliveryException {
        Status status = null;
        Channel channel = getChannel();
        //拿到channel事务
        Transaction transaction = channel.getTransaction();
        transaction.begin();
        try {
            Event take = channel.take();
            String body = new String(take.getBody(), Charsets.UTF_8);
            String result = prefix + body + end;
            LOGGER.info(result);
            transaction.commit();
            status = Status.READY;
        } catch (Exception e) {
            transaction.rollback();
            status = Status.BACKOFF;
        } finally {
            transaction.close();
        }
        return status;
    }

    @Override
    public void configure(Context context) {
        this.prefix = context.getString("prefix");
        this.end = context.getString("end", "on the road");
    }
}
