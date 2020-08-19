package org.dongdong.rabbitMQ.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.dongdong.utils.RabbitMQConnUtils;

import java.io.IOException;

/**
 * @Auther: 杨伟栋
 * @Date: 2020/8/19 21:53
 * @Description: 1371690483@qq.com
 */
public class provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQConnUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("logs","fanout");
        channel.basicPublish("logs","",null,"exchange".getBytes());
        RabbitMQConnUtils.closeConnection(channel,connection);
    }
}
