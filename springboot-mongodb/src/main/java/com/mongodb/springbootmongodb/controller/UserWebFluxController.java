package com.mongodb.springbootmongodb.controller;

import com.mongodb.springbootmongodb.Do.UserDo;
import com.mongodb.springbootmongodb.common.UserHandler;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author relax
 */

@RestController
public class UserWebFluxController {

    @Resource
    private UserHandler userHandler;

    @GetMapping(value = "/{id}")
    public Mono<UserDo> findCityById(@PathVariable("id") Long id) {
        return userHandler.findUserById(id);
    }

    @GetMapping(value = "/findAll")
    public Flux<UserDo> findAllCity() {
        return userHandler.findAllUser();
    }

    @PostMapping(value = "/save")
    public Mono<UserDo> saveCity(@RequestBody UserDo userDo) {
        return userHandler.save(userDo);
    }

    @PutMapping(value = "/update")
    public Mono<UserDo> modifyUser(@RequestBody UserDo userDo) {
        return userHandler.modifyUser(userDo);
    }

    @DeleteMapping(value = "del/{id}")
    public Mono<Long> deleteCity(@PathVariable("id") Long id) {
        return userHandler.deleteUser(id);
    }
}
