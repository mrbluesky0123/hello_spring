package com.skcc.fwk.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public abstract class BaseController extends BaseObject{



    @Autowired
    ApplicationContext context;

}
