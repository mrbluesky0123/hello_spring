package com.skcc.start.part.common.service;


import com.skcc.fwk.base.BaseService;
import com.skcc.start.entity.common.Code;
import com.skcc.start.repository.jpa.CodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CodeService extends BaseService{


    @Autowired
    CodeRepository repoCode;


    public Collection<Code> getCodes(){

        List<Code> all = repoCode.findAll();
//        MDC.put("logServiceFileName", "a");
//        log.info("result:{}", all);
//
//        MDC.put("logServiceFileName", "b");
        log.info("result:{}", all);
        return all;

    }

    public void postCode(Code code) {

        repoCode.save(code);

    }
}
