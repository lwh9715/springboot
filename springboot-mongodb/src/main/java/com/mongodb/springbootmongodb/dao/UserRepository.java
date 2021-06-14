package com.mongodb.springbootmongodb.dao;

import com.mongodb.springbootmongodb.Do.UserDo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author relax
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<UserDo, Long> {
}
