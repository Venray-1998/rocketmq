package com.fwr.demo.producer;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 事务发送消息的生产者
 *
 * @author fwr
 * @date 2020-12-17
 */
public class TransactionProducer {

    public static void main(String[] args) throws Exception {
        TransactionMQProducer producer = new TransactionMQProducer("transaction");
        producer.setNamesrvAddr("172.21.68.193:9876;172.21.68.192:9876");
        // 设置事务监听器
        producer.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {
                if (StringUtils.equals("tag1", message.getTags())) {
                    return LocalTransactionState.COMMIT_MESSAGE;
                } else if (StringUtils.equals("tag2", message.getTags())) {
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                } else {
                    return LocalTransactionState.UNKNOW;
                }
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                System.out.println("回查"+messageExt.getTags());
                return LocalTransactionState.UNKNOW;
            }
        });
        producer.start();
        String[] tags = {"tag1", "tag2", "tag3"};
        for (int i = 0; i < 3; i++) {
            Message msg = new Message("transaction", tags[i], ("hello" + i).getBytes());
            SendResult result = producer.sendMessageInTransaction(msg, null);
            System.out.println(result);
        }
        // 关闭生产者
        //producer.shutdown();
    }
}
