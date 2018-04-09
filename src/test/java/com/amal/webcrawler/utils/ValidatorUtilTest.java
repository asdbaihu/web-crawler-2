package com.amal.webcrawler.utils;

import com.amal.webcrawler.Exceptions.RootUrlNotValidException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Amal on 25/01/2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ValidatorUtilTest {

    @Test
    public void should_return_true_for_valid_url() {
        String validUrl = "http://wwww.google.com";
        boolean result = ValidatorUtil.isUrlValid(validUrl);
        Assert.assertTrue(result);
    }

    @Test(expected = RootUrlNotValidException.class)
    public void should_throw_exception_when_url_not_valid() {
        String invalidUrl = "invalidUrl";
        ValidatorUtil.isUrlValid(invalidUrl);
    }
}
