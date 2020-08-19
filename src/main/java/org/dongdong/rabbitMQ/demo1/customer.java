package org.dongdong.rabbitMQ.demo1;

import com.rabbitmq.client.*;
import org.dongdong.utils.RabbitMQConnUtils;

import java.io.IOException;

/**
 * @Auther: 杨伟栋
 * @Date: 2020/8/19 20:37
 * @Description: 1371690483@qq.com
 */
public class customer {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMQConnUtils.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare("hello",true,false,false,null);
        channel.basicQos(1);//每次消费一条数据

        //参数2   true自动消息确认  false不自动消息确认
        channel.basicConsume("hello",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);//手动确认消息
            }
        });
    }
}
