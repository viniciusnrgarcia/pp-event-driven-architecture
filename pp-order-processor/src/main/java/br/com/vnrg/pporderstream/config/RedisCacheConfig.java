package br.com.vnrg.pporderstream.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class RedisCacheConfig {

    @Value("${spring.redis.server-url}")
    private String server;

    @Value("${spring.redis.server-port}")
    private Integer port;

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(this.server, this.port);
        return new JedisConnectionFactory(config);
    }

}
