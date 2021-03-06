package cn.jimoos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Portal APP
 *
 * @author chenqisheng
 */
@SpringBootApplication(scanBasePackages = {"cn.jimoos"})
public class JmApiTracerApp {
    public static void main(String[] args) {
        SpringApplication.run(JmApiTracerApp.class, args);
    }
}
