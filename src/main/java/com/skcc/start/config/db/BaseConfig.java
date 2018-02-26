package com.skcc.start.config.db;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;

@Configuration
@EnableJpaRepositories("com.skcc.start.repository.jpa")
@EntityScan(value = {"com.skcc.start.entity", "com.skcc.fwk.entity"})
public class BaseConfig {

    // Spring 띄우기 전에 읽는 클래스
    // 기본 설정값 저장

}
