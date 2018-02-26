package com.skcc.start.part.common.controller;

import com.skcc.fwk.base.BaseController;
import com.skcc.start.entity.common.Code;
import com.skcc.start.part.common.service.CodeService;
import com.skcc.start.part.common.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/codes")
@Api(value="코드 컨트롤러", description = "코드값 관리")
public class CodeController extends BaseController{


    @Autowired
    CodeService codeService;

    @Autowired
    UserService userService;

    // @GetMapping(value = "/codes") 랑 다른점?
    @GetMapping(value = "")
    @ApiOperation(value = "코드 전체 조회", notes = "입력값 없음")
    public Collection<Code> getCodes(){

        userService.test();
//        commons.getArea().getIp();
        return codeService.getCodes();

    }

    // @PostMapping(value="/{groupCode}/{code}")
    // public void postCode(@PathVariable String groupCode, @PathVariable String code){
    // @PathVariable URL을 변수로 맵핑
    @PostMapping(value="/{codeName}")
    public void postCode(@PathVariable String codeName, @RequestBody Code code){

        codeService.postCode(code);

    }
}
