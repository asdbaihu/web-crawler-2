package com.amal.webcrawler.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Amal on 20/01/2018.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Root url is not valid. Start the url with http or https.")
public class RootUrlNotValidException extends RuntimeException {

}
