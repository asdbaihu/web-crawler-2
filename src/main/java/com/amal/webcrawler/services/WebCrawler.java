package com.amal.webcrawler.services;

import com.amal.webcrawler.model.Page;

/**
 * Created by Amal on 20/01/2018.
 */
public interface WebCrawler {

    /**
     *
     * @param rootPage
     * @param maxDepth
     * @return RootPage is populated with nodes upto required level.
     */
    Page getCrawlResults(Page rootPage, int maxDepth);
}
