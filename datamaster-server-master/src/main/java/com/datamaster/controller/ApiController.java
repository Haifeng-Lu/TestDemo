package com.datamaster.controller;

import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datamaster.dto.Foo;

@RestController
@RequestMapping("/api")
public class ApiController {

	@RequestMapping(value="", method=RequestMethod.GET)
	public String index() {
		return "RESTful API framework is working";
	}
	
	@RequestMapping(value="/session", method=RequestMethod.GET)
	public HttpSession session(HttpSession session) {
		return session;
	}
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	@RequestMapping(value = "/pullFoo", method = RequestMethod.GET)
	public Foo pullFoo(@RequestParam(value="name", defaultValue="Foo") String name) {
		return new Foo(counter.incrementAndGet(), String.format(template, name));
	}
}
