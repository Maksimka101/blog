package com.maksimka.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


// 192.168.0.54:8080/test/html - use it for phone
@RestController
@RequestMapping("test")
class TestController {

    val testHTML = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<body>\n" +
            "\n" +
            "<p>I am normal</p>\n" +
            "<p style=\"color:red;\">I am red</p>\n" +
            "<p style=\"color:blue;\">I am blue</p>\n" +
            "<p style=\"font-size:50px;\">I am big</p>\n" +
            "\n" +
            "</body>\n" +
            "</html>\n"

    @GetMapping("html")
    fun getHTML() = testHTML

}