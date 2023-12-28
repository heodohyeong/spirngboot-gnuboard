package com.gnuboard.demo.util.redis;

import org.springframework.data.repository.CrudRepository;

public interface RedisTokenRepository extends CrudRepository<RedisToken , Long> {
}
