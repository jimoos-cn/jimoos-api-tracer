package cn.jimoos.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Ping
 *
 * @author keepcleargas
 */
@RestController
@RequestMapping("/v1/pings")
public class PingApi {
    @GetMapping
    public String ping(@RequestParam(value = "q", required = false, defaultValue = "world") String q) {
        return "hello," + q;
    }
}
