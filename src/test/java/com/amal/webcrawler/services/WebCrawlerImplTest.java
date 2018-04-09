package com.amal.webcrawler.services;

import com.amal.webcrawler.model.Page;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;

/**
 * Created by Amal on 25/01/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class WebCrawlerImplTest {

    @Test
    public void getCrawlResults_processes_levels_as_per_supplied_depth(){
        WebCrawlerImpl crawlerImpl = new WebCrawlerImpl();
        WebCrawlerImpl crawler = Mockito.spy(crawlerImpl);
        doReturn(null).when(crawler).processLevel(any());
        crawler.getCrawlResults(new Page("www.google.com"), 2);
        Mockito.verify(crawler,times(2)).processLevel(any());

    }

    @Test
    public void getNextLevelPages_should_return_empty_list_when_error(){
        Page page = new Page("http://wwww.87878hfkdhf888.com");//non-existent url to generate error
        WebCrawlerImpl crawler = new WebCrawlerImpl();
        List<Page> nextLevelPages = crawler.getNextLevelPages(page);
        Assert.assertEquals(nextLevelPages.size(),0);
    }

    @Test
    public void getNextLevelPages_should_return_non_empty_list_when_page_is_valid(){
        Page page = new Page("http://www.google.com");
        WebCrawlerImpl crawler = new WebCrawlerImpl();
        List<Page> nextLevelPages = crawler.getNextLevelPages(page);
        Assert.assertNotEquals(nextLevelPages.size(),0);
    }

    @Test
    public void processLevel_correctly_adds_nodes_and_returns_childNodes(){
        WebCrawlerImpl crawlerImpl = new WebCrawlerImpl();
        WebCrawlerImpl crawler = Mockito.spy(crawlerImpl);
        doReturn(getChildPagesTestData()).when(crawler).getNextLevelPages(any());
        List<Page> nextLevelPages = crawler.processLevel(getCurrentPagesTestData());
        Assert.assertEquals(nextLevelPages.size(),9);
        Mockito.verify(crawler,times(3)).getNextLevelPages(any());
    }
    private List<Page> getCurrentPagesTestData(){
        List<Page> currentPages = new ArrayList<>(3);
        currentPages.add(new Page("www.currentPage1.com"));
        currentPages.add(new Page("www.currentPage2.com"));
        currentPages.add(new Page("www.currentPage3.com"));
        return currentPages;
    }

    private List<Page> getChildPagesTestData(){
        List childPages = new ArrayList(3);
        childPages.add(new Page("www.childPage1.com"));
        childPages.add(new Page("www.childPage2.com"));
        childPages.add(new Page("www.childPage3.com"));
        return childPages;
    }
}
