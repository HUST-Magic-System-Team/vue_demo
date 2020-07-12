package com.manli.manli_java.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@EnableCaching
@Configuration
public class Redis2Config {
    @Value("${spring.redis1.host}")
    private String hostName;
    @Value("${spring.redis1.port}")
    private int    port;
    @Value("${spring.redis1.password}")
    private String password;
    @Value("${spring.redis1.pool.max-idle}")
    private int    maxIdl;
    @Value("${spring.redis1.pool.min-idle}")
    private int    minIdl;
    @Value("${spring.redis1.database}")
    private int    database;
    @Value("${spring.redis1.keytimeout}")
    private long   keytimeout;
    @Value("${spring.redis1.timeout}")
    private int    timeout;

    @Value("${spring.redis2.host}")
    private String hostName2;
    @Value("${spring.redis2.port}")
    private int    port2;
    @Value("${spring.redis2.password}")
    private String password2;
    @Value("${spring.redis2.pool.max-idle}")
    private int    maxIdl2;
    @Value("${spring.redis2.pool.min-idle}")
    private int    minIdl2;
    @Value("${spring.redis2.database}")
    private int    database2;
    @Value("${spring.redis2.keytimeout}")
    private long   keytimeout2;
    @Value("${spring.redis2.timeout}")
    private int    timeout2;


    @Bean
    public RedisConnectionFactory redis1ConnectionFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdl);
        poolConfig.setMinIdle(minIdl);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setNumTestsPerEvictionRun(10);
        poolConfig.setTimeBetweenEvictionRunsMillis(60000);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
        jedisConnectionFactory.setHostName(hostName);
        if (!password.isEmpty()) {
            jedisConnectionFactory.setPassword(password);
        }
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setDatabase(database);
        return jedisConnectionFactory;
    }


    @Bean(name = "redis1Template")
    public RedisTemplate<String, Object> redis1Template() throws Exception {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(redis1ConnectionFactory());
        setSerializer(redisTemplate);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    @Bean
    public RedisConnectionFactory redis2ConnectionFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdl2);
        poolConfig.setMinIdle(minIdl2);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setNumTestsPerEvictionRun(10);
        poolConfig.setTimeBetweenEvictionRunsMillis(60000);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
        jedisConnectionFactory.setHostName(hostName2);
        if (!password2.isEmpty()) {
            jedisConnectionFactory.setPassword(password2);
        }
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setDatabase(database);
        return jedisConnectionFactory;
    }


    @Bean(name = "redis2Template")
    public RedisTemplate<String, Object> redis2Template() throws Exception {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(redis2ConnectionFactory());
        setSerializer(redisTemplate);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    private void setSerializer(RedisTemplate<String, Object> redisTemplate) {
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
//                Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        redisTemplate.setKeySerializer(redisTemplate.getStringSerializer());
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        //在使用String的数据结构的时候使用这个来更改序列化方式
//        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
//        redisTemplate.setKeySerializer(stringSerializer );
//        redisTemplate.setValueSerializer(stringSerializer );
//        redisTemplate.setHashKeySerializer(stringSerializer );
//        redisTemplate.setHashValueSerializer(stringSerializer );

        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
        // value值的序列化采用fastJsonRedisSerializer
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        // key的序列化采用StringRedisSerializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    }
}

