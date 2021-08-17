package com.huangpuguang.sms.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>RabbitMQ配置文件【可用于自动生成队列和交换机】 </p>
 *
 * @author Procon
 * @since 2021/7/19
 */

@Configuration
public class RabbitMqConfig {

    public static final String PROCON_EXCHANGE_DIRECT = "procon.exchange.direct";
    public static final String PROCON_EXCHANGE_DEAD = "procon.exchange.dead";

    public static final String PROCON_QUEUE_EMAIL ="procon.queue.email";
    public static final String PROCON_QUEUE_SMS = "procon.queue.sms";
    public static final String PROCON_QUEUE_BLOG= "procon.queue.blog";
    public static final String PROCON_QUEUE_DEAD= "procon.queue.dead";

    public static final String ROUTING_KEY_EMAIL = "procon.email";
    public static final String ROUTING_KEY_SMS = "procon.sms";
    public static final String ROUTING_KEY_BLOG= "procon.blog";

    public static final String X_MESSAGE_TTL = "x-message-ttl";
    public static final String X_DEAD_LETTER_EXANGE ="x-dead-letter-exchange";

    /**
     * 声明Direct交换机
     */
    @Bean
    public DirectExchange smsExchange() {
        // 声明路由交换机，durable:在rabbitmq重启后，交换机还在
        return new DirectExchange(PROCON_EXCHANGE_DIRECT,true,false);
    }

    /**
     * 声明Blog队列
     *
     */
    @Bean
    public Queue proconBlog() {
        Map<String,Object> args = new HashMap<>(16);
        //60s延时
        args.put(X_MESSAGE_TTL,60000);
        args.put(X_DEAD_LETTER_EXANGE,PROCON_EXCHANGE_DEAD);
        return new Queue(PROCON_QUEUE_BLOG,true,false,false,args);
    }

    /**
     * 声明Email队列
     *
     */
    @Bean
    public Queue proconEmail() {
        Map<String,Object> args = new HashMap<>(16);
        //60s延时
        args.put(X_MESSAGE_TTL,60000);
        args.put(X_DEAD_LETTER_EXANGE,PROCON_EXCHANGE_DEAD);
        return new Queue(PROCON_QUEUE_EMAIL,true,false,false,args);
    }

    /**
     * 声明SMS队列
     *
     */
    @Bean
    public Queue proconSms() {
        Map<String,Object> args = new HashMap<>(16);
        //60s延时
        args.put(X_MESSAGE_TTL,60000);
        args.put(X_DEAD_LETTER_EXANGE,PROCON_EXCHANGE_DEAD);
        return new Queue(PROCON_QUEUE_SMS,true,false,false,args);
    }

    /**
     * procon.blog 队列绑定交换机，指定routingKey
     *
     * @return proconBlogBinding
     */
    @Bean
    public Binding proconBlogBinding() {
        return BindingBuilder.bind(proconBlog()).to(smsExchange()).with(ROUTING_KEY_BLOG);
    }

    /**
     * procon.mail 队列绑定交换机，指定routingKey
     *
     * @return proconMailBindingBlogBinding
     */
    @Bean
    public Binding proconMailBinding() {
        return BindingBuilder.bind(proconEmail()).to(smsExchange()).with(ROUTING_KEY_EMAIL);
    }

    /**
     * procon.mail 队列绑定交换机，指定routingKey
     *
     * @return proconMailBindingBlogBinding
     */
    @Bean
    public Binding proconSmsBinding() {
        return BindingBuilder.bind(proconSms()).to(smsExchange()).with(ROUTING_KEY_SMS);
    }


    /**
     * 死信交换器
     *
     */
    @Bean
    public FanoutExchange proconDeadExchange(){
        return new FanoutExchange(PROCON_EXCHANGE_DEAD,true,false);
    }

    /**
     * 死信队列
     *
     */

    @Bean
    public Queue proconDeadQueue(){
        return new Queue(PROCON_QUEUE_DEAD,true,false,false);
    }

    /**
     * 死信绑定器
     *
     */
    @Bean
    public Binding proconDeadBinding(){
        return  BindingBuilder.bind(proconDeadQueue()).to(proconDeadExchange());
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
