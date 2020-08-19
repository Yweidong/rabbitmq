package org.dongdong.rabbitMQ.demo1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.dongdong.utils.RabbitMQConnUtils;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @Auther: 杨伟栋
 * @Date: 2020/8/19 20:37
 * @Description: 1371690483@qq.com
 */
public class provider {
    @Test
    public void test() {
        Connection connection = RabbitMQConnUtils.getConnection();
        Channel channel = null;
        try {
           channel = connection.createChannel();
            /*
             * @Author 杨伟栋
             * @Description
             * @Date 2020/8/19 8:44 下午
             * @Param [
             *        参数1   队列名称
             *        参数2   数据是否持久化 true是
             *        参数3   是否独占对列
             *        参数4   是否消费完后删除队列
             *        参数5   额外参数
             * ]
             * @return void
             **/
            channel.queueDeclare("hello",true,false,false,null);
            /*
             * @Author 杨伟栋
             * @Description
             * @Date 2020/8/19 8:47 下午
             * @Param [
             *        参数1   交换机名称
             *        参数2   队列名称
             *        参数3   rabbitmq重启后数据持久化
             *        参数4   发送的消息
             * ]
             * @return void
             **/
            channel.basicPublish("","hello", MessageProperties.PERSISTENT_BASIC,"hello rabb".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            RabbitMQConnUtils.closeConnection(channel,connection);
        }
    }
}
