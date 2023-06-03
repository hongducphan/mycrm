package com.ducph.mycrm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
@Slf4j
public class KafkaTestController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/produce")
    public void produce(@RequestParam String message) {
        kafkaTemplate.send("ducphTopic", message);
    }

    @KafkaListener(topics = "ducphTopic", groupId = "foo")
    public void listenGroupFoo(String message) {
        log.info("Received Message in group foo: {}", message);
    }
}
