package com.qalabs.common;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;



public class BaseElement {
    protected WebElement root;
    protected final static Logger logger = Logger.getLogger(BaseElement.class);
    public BaseElement(WebElement root) {
        this.root = root;
    }
}
