package org.dongdong.rabbitMQ.fanout;

import com.rabbitmq.client.*;
import org.dongdong.utils.RabbitMQConnUtils;

import java.io.IOException;

/**
 * @Auther: 杨伟栋
 * @Date: 2020/8/19 21:56
 * @Description: 1371690483@qq.com
 */
public class customer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQConnUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("logs","fanout");
        //创建临时队列
        String queue = channel.queueDeclare().getQueue();
        //队列绑定交换机
        channel.queueBind(queue,"logs","");
        channel.basicConsume(queue,true,new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1--"+new String(body));
            }
        });
    }
}
