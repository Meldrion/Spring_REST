package lu.post.rest;

import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class HelloController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @CrossOrigin
    @RequestMapping("/hello")
    public Hello greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Hello(counter.incrementAndGet(),
                String.format(template, name));
    }

    @CrossOrigin
    @RequestMapping("/addition")
    public Integer addition(@RequestParam(value="value1")int value1,
                            @RequestParam(value="value2")int value2) {
        return value1 + value2;
    }

    @RequestMapping(value = "/",
            method = RequestMethod.GET)
    @ResponseBody
    public String plaintext(HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        return "Welcome to my REST WebService";
    }

}