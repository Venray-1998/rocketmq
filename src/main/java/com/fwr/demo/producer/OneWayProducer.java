package com.fwr.demo.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * 单向消息
 *
 * @author fwr
 * @date 2020-12-17
 */
public class OneWayProducer {

    public static void main(String[] args) throws Exception{
        // 创建消息生产者
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        // 指定nameserver地址
        producer.setNamesrvAddr("172.21.68.193:9876;172.21.68.192:9876");
        // 启动生产者
        producer.start();
        // 创建消息对象，指定topic、tag和消息体
        for (int i = 0; i < 10; i++) {
            Message msg = new Message("topic1", "tag3", ("单向消息" + i+10).getBytes());
            // 发送消息
            producer.sendOneway(msg);
            TimeUnit.SECONDS.sleep(1);
        }
        // 关闭生产者
        producer.shutdown();
    }
}
