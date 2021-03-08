package cn.jimoos.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author :keepcleargas
 * @date :2021-03-08 17:28.
 */
@Controller
@RequestMapping("/")
@Slf4j
public class IndexController {
    @GetMapping
    public String index() {
        return "index";
    }
}
