package com.mongodb.springbootmongodb.common;

import com.mongodb.springbootmongodb.Do.UserDo;
import com.mongodb.springbootmongodb.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author relax
 */

@Component
public class UserHandler {

    private final UserRepository userRepository;

    @Autowired
    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<UserDo> save(UserDo userDo) {
        return userRepository.save(userDo);
    }

    public Mono<UserDo> findUserById(Long id) {

        return userRepository.findById(id);
    }

    public Flux<UserDo> findAllUser() {

        return userRepository.findAll();
    }

    public Mono<UserDo> modifyUser(UserDo userDo) {

        return userRepository.save(userDo);
    }

    public Mono<Long> deleteUser(Long id) {
        return userRepository.deleteById(id).flatMap(mono ->
                Mono.create(cityMonoSink -> cityMonoSink.success(id)));
    }
}
