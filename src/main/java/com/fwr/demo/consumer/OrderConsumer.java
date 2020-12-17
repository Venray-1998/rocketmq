package com.fwr.demo.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author fwr
 * @date 2020-12-17
 */
public class OrderConsumer {

    public static void main(String[] args) throws Exception{
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("order");
        consumer.setNamesrvAddr("172.21.68.193:9876;172.21.68.192:9876");
        consumer.subscribe("orderTopic","order");
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                for (MessageExt messageExt : list) {
                    System.out.println(Thread.currentThread().getName()+"======"+new String(messageExt.getBody()));
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
        System.out.println("qidongche那个膏");
    }
}
