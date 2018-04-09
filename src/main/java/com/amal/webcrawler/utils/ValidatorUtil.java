package com.amal.webcrawler.utils;

import com.amal.webcrawler.Exceptions.RootUrlNotValidException;
import org.apache.commons.validator.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Amal on 20/01/2018.
 */
public class ValidatorUtil {

    private static final Logger log = LoggerFactory.getLogger(ValidatorUtil.class);

    /**
     * Validates a url.
     *
     * @param url
     * @return
     */
    public static boolean isUrlValid(String url) {
        String[] schemes = {"http", "https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        if (url != null && urlValidator.isValid(url)) {
            log.debug("The url is valid : {}", url);
            return true;
        }
        log.error("The specified root-url is not valid : {} ", url);
        throw new RootUrlNotValidException();
    }
}
