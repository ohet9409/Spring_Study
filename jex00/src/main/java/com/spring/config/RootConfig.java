package com.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
// 설정파일을 대신하는 클래스
@Configuration
@ComponentScan(basePackages = {"com.spring"})
public class RootConfig {

}
