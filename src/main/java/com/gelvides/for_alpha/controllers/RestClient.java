package com.gelvides.for_alpha.controllers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${spring.application.name}", url = "${spring.request.openexchangerates.server-request}")
public interface RestClient {
    @GetMapping("${spring.request.openexchangerates.latest-cource}?${spring.request.openexchangerates.application-id}")
    String getCource();

    @GetMapping("${spring.request.openexchangerates.historical-cource}/{yesterday-date}.json?${spring.request.openexchangerates.application-id}")
    String getCource(@PathVariable("yesterday-date") String date);
}
