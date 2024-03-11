package com.chilun.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;


/**
 * @author 齿轮
 * @date 2024-02-21-12:08
 */
@RestController
@RequestMapping("/mock")
public class MockController {
    @PostMapping("/stream")
    public ResponseEntity<StreamingResponseBody> mockStream(@RequestBody String question, HttpServletResponse response, HttpServletRequest request) throws IOException, InterruptedException {
        String answer = "这是问题：{" + question + "}的mock回答";
        response.setCharacterEncoding("UTF-8");
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(outputStream -> {
                    PrintWriter writer = new PrintWriter(outputStream, true, StandardCharsets.UTF_8);
                    for (int i = 0; i < answer.length(); i++) {
                        writer.print(answer.charAt(i));
                        writer.flush();
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
