package com.amal.webcrawler.controllers;

import com.amal.webcrawler.services.WebCrawler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Amal on 25/01/2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(WebCrawlerController.class)
public class WebCrawlerControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    WebCrawler webCrawler;

    @Test
    public void should_return_200_response_when_service_call_success() throws Exception {
        mvc.perform(get("/v1/crawl?rootUrl=http://www.google.com&maxDepth=2")).andExpect(status().isOk());
    }

    @Test
    public void should_return_4xx_response_when_invalid_rootUrl() throws Exception {
        mvc.perform(get("/v1/crawl?rootUrl=abc&maxDepth=2")).andExpect(status().is4xxClientError());
    }
}
