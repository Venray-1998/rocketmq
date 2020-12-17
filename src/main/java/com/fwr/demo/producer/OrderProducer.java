package com.fwr.demo.producer;

import com.alibaba.fastjson.JSON;
import com.fwr.demo.entity.OrderStep;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 顺序发送,自定义选择broker里的队列,
 *
 * @author fwr
 * @date 2020-12-17
 */
public class OrderProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("172.21.68.193:9876;172.21.68.192:9876");
        producer.start();
        List<OrderStep> list = OrderStep.getList();
        for (OrderStep orderStep : list) {
            Message msg = new Message("orderTopic","order", JSON.toJSONBytes(orderStep));
            SendResult result = producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    long orderId = (long) o;
                    long index = orderId % list.size();
                    return list.get((int) index);
                }
            }, orderStep.getOrderId());
            System.out.println(result);
        }
        producer.shutdown();
    }
}
