package com.amal.webcrawler.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import static org.hamcrest.Matchers.is;

/**
 * Created by Amal on 29/01/2018.
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class PageTest {

    @Test
    public void equals_should_return_true_when_same_obj_reference_passed() {
        Page page = new Page("www.google.com");
        boolean result = page.equals(page);
        assertThat(result, is(true));
    }

    @Test
    public void equals_should_return_true_when_rootUrls_are_equal() {
        Page page1 = new Page("wwww.google.com");
        Page page2 = new Page("wwww.google.com");
        boolean result = page1.equals(page2);
        assertThat(result, is(true));
    }

    @Test
    public void equals_should_return_false_when_rootUrls_are_no_equal() {
        Page page1 = new Page("wwww.google.com");
        Page page2 = new Page("wwww.yahoo.com");
        boolean result = page1.equals(page2);
        assertThat(result, is(false));
    }

    @Test
    public void equals_should_return_false_when_unequal_classes_are_compared() {
        Page page1 = new Page("wwww.google.com");
        String string1 = "www.google.com";
        boolean result = page1.equals(string1);
        assertThat(result, is(false));
    }

    @Test
    public void hashCode_should_be_equal_when_objects_are_equal() {
        Page page1 = new Page("wwww.google.com");
        Page page2 = new Page("wwww.google.com");
        assertThat(page1.hashCode(), is(page2.hashCode()));
    }
    @Test
    public void hashCode_should_be_differ_when_objects_differ() {
        Page page1 = new Page("wwww.google.com");
        Page page2 = new Page("wwww.yahoo.com");
        assertThat(page1.hashCode(), not(page2.hashCode()));
    }
}
