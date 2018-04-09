package com.amal.webcrawler.services;

import com.amal.webcrawler.model.Page;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Amal on 22/01/2018.
 */
@Service
public class WebCrawlerImpl implements WebCrawler {
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";
    public static final Logger log = LoggerFactory.getLogger(WebCrawlerImpl.class);


    @Override
    public Page getCrawlResults(Page rootPage, int maxDepth) {
        int currentDepth = 1;
        List<Page> currentLevelPages = new ArrayList();
        currentLevelPages.add(rootPage);
        while (currentDepth <= maxDepth) {
            currentLevelPages = processLevel(currentLevelPages);
            currentDepth++;
        }
        return rootPage;
    }

    /**
     *
     * @param currentLevelPages
     * @return List of pages on the next level.
     */
    protected List<Page> processLevel(List<Page> currentLevelPages) {
        List<Page> nextLevelPages = new ArrayList<>();
        currentLevelPages.parallelStream().forEach(page -> {
            List<Page> childpageList = getNextLevelPages(page);
            synchronized (nextLevelPages) {
                nextLevelPages.addAll(childpageList);
            }
            log.debug("Number of next level pages is {} for page {} ", childpageList.size(), page.getUrl());
            page.setNodes(childpageList);
        });
        return nextLevelPages;
    }

    /**
     * Populates the nodes of a page and returns the next level pages.
     * @param page
     * @return
     */
    @Cacheable("pages")
    protected List<Page> getNextLevelPages(Page page) {
        List<Page> pageList = new ArrayList<>();
        Connection connection = Jsoup.connect(page.getUrl()).userAgent(USER_AGENT);
        Document htmlPage = null;
        try {
            htmlPage = connection.get();
            page.setTitle(htmlPage.title());
        } catch (IOException e) {
            //ignoring errors and allowing processing of other pages to continue.
            page.setTitle(page.getTitle() + "- Error occurred in processing " + e.getMessage());
            return pageList;
        }
        Elements linksOnPage = htmlPage.select("a[href]");
        linksOnPage.forEach(link -> {
            String pageLink = link.absUrl("href");
            if (pageLink != null && !pageLink.isEmpty()) {
                Page childPage = new Page(pageLink);
                pageList.add(childPage);
            }
        });
        return pageList;
    }

}
