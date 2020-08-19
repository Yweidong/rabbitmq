package org.dongdong.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Auther: 杨伟栋
 * @Date: 2020/8/19 20:28
 * @Description: 1371690483@qq.com
 */
public class RabbitMQConnUtils {

    private static ConnectionFactory connectionFactory = null;

    static {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("106.15.251.237");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("ems");
        connectionFactory.setVirtualHost("/ems");

    }

    public static Connection getConnection() {
        try {

            Connection connection = connectionFactory.newConnection();
            return connection;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection(Channel channel,Connection conn) {
        try {
            if (channel!=null) channel.close();
            if (conn!=null) conn.close();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
