package com.fwr.demo.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * 同步发送消息的生产者
 *
 * @author fwr
 * @date 2020-12-17
 */
public class SyncProducer {

    public static void main(String[] args) throws Exception {
        // 创建消息生产者
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        // 指定nameserver地址
        producer.setNamesrvAddr("172.21.68.193:9876;172.21.68.192:9876");
        // 启动生产者
        producer.start();
        // 创建消息对象，指定topic、tag和消息体
        for (int i = 0; i < 10; i++) {
            Message msg = new Message("topic1", "tag1", ("hello" + i).getBytes());
            // 设置消息发送延迟时间
            msg.setDelayTimeLevel(2);
            // 发送消息
            SendResult result = producer.send(msg);
            System.out.println(result);
        }
        // 关闭生产者
        producer.shutdown();
    }
}
