package cn.jimoos.rest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author :keepcleargas
 * @date :2021-03-08 18:35.
 */
@Controller
public class ErrorApi implements ErrorController {
    @RequestMapping("/error")
    public String handleError() {
        return "index";
    }

    @Override
    public String getErrorPath() {
        return "index";
    }
}
