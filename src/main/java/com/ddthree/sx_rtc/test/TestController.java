package com.ddthree.sx_rtc.test;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Api(tags = "测试接口", description = "测试文档说明", hidden = true)
public class TestController {

    @RequestMapping(value = "/swaggerDemo", method = {RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "测试接口1", notes = "测试接口1说明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "sw", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "age", value = "18", dataType = "int", paramType = "query")
    })
    @ApiResponse(response = String.class, code = 200, message = "接口返回对象参数")
    public String swaggerDemo(String name, String age) {
        return "name = " + name + ", age = " + age;
    }

}
