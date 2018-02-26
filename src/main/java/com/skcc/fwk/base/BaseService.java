package com.skcc.fwk.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


public abstract class BaseService extends BaseObject{

    protected static Logger log = (Logger) LoggerFactory.getLogger("eachService");

    @Autowired
    ApplicationContext context;

}
