package com.example.demo.service.greeting.controller;

import com.example.demo.service.greeting.model.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

// "In Spring’s approach to building RESTful web services, HTTP requests are handled by a
// controller. These components are identified by the @RestController annotation."

// "The @RestController annotation marks the class as a controller where every method returns a
// domain object instead of a view. It is shorthand for including both [@Controller] and [@ResponseBody]."

// "A key difference between a traditional MVC controller and the RESTful web service controller
// shown earlier is the way that the HTTP response body is created. Rather than relying on a
// view technology to perform server-side rendering of the greeting data to HTML, this RESTful
// web service controller populates and returns a [Greeting] object. The object data will be
// written directly to the HTTP response as JSON."
@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    // The annotations for HTTP verbs all derive from @RequestMapping.
    // Synonym for @GetMapping: @RequestMapping(method=GET).

    // "The @GetMapping annotation ensures that HTTP GET requests to [/greeting] are mapped to
    // the [greeting()] method."
    @GetMapping("/greeting")
    // "The @RequestParam annotation binds the value of the query string parameter [user-name]
    // into the [name] parameter of the [greeting()] method. If the [user-name] parameter is
    // absent in the request, the [defaultValue] of [World] is used."
    public Greeting greeting(@RequestParam(value = "user-name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    // "This application uses the Jackson JSON library to automatically marshal instances of
    // type [Greeting] into JSON. Jackson is included by default by the web starter."

    // "The [Greeting] object must be converted to JSON. Thanks to Spring’s HTTP message converter
    // support, you need not do this conversion manually. Because Jackson 2 is on the classpath,
    // Spring's [MappingJackson2HttpMessageConverter] is automatically chosen to convert the
    // [Greeting] instance to JSON."
}
