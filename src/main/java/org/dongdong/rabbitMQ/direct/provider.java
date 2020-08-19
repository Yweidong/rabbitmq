package org.dongdong.rabbitMQ.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.dongdong.utils.RabbitMQConnUtils;

import java.io.IOException;

/**
 * @Auther: 杨伟栋
 * @Date: 2020/8/19 22:20
 * @Description: 1371690483@qq.com
 */
public class provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQConnUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("log_level","direct");
        String routerkey = "info";

        channel.basicPublish("log_level",routerkey, MessageProperties.PERSISTENT_TEXT_PLAIN,"direct".getBytes());

        RabbitMQConnUtils.closeConnection(channel,connection);

    }
}
