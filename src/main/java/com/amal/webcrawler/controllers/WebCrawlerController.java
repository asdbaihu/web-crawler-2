package com.amal.webcrawler.controllers;

import com.amal.webcrawler.model.Page;
import com.amal.webcrawler.services.WebCrawler;
import com.amal.webcrawler.utils.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Amal on 20/01/2018.
 */
@RequestMapping(path = "/v1/crawl")
@RestController
public class WebCrawlerController {

    @Autowired
    WebCrawler webCrawler;
    private static final Logger log = LoggerFactory.getLogger(WebCrawlerController.class);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Object crawl(@RequestParam String rootUrl, @RequestParam(required = false, defaultValue = "${crawler.default.maxDepth}") int maxDepth) {
        log.info("Received request to crawl rootUrl : {}", rootUrl);
        ValidatorUtil.isUrlValid(rootUrl);
        return webCrawler.getCrawlResults(new Page(rootUrl), maxDepth);
    }

}
