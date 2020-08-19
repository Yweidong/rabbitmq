package org.dongdong.rabbitMQ.direct;

import com.rabbitmq.client.*;
import org.dongdong.utils.RabbitMQConnUtils;

import java.io.IOException;

/**
 * @Auther: 杨伟栋
 * @Date: 2020/8/19 22:22
 * @Description: 1371690483@qq.com
 */
public class customer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQConnUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("log_level","direct");
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue,"log_level","error");
        channel.basicConsume(queue,true,new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });
    }
}
