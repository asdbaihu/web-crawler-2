package com.amal.webcrawler.model;

import java.util.List;

/**
 * Created by Amal on 22/01/2018.
 */
public class Page {

    private String url;
    private String title;
    private List<Page> nodes;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Page> getNodes() {
        return nodes;
    }

    public void setNodes(List<Page> nodes) {
        this.nodes = nodes;
    }

    public Page(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Page)) {
            return false;
        }
        Page page = (Page) obj;
        if (this.getUrl().equals(page.getUrl())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 1234 * this.url.hashCode();
    }
}
