package com.quanxiaoha.weblog.web.controller;

import com.quanxiaoha.weblog.common.aspect.ApiOperationLog;
import com.quanxiaoha.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(tags = "首页模块")
public class TestController {

    @PostMapping("/admin/update")
    @ApiOperation(value = "测试更新接口")
    @ApiOperationLog(description = "测试更新接口")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response testUpdate() {
        log.info("更新成功...");
        return Response.success();
    }

}
