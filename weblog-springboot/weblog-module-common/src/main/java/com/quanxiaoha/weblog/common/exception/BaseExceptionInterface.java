package com.quanxiaoha.weblog.common.exception;

/**
 * @author newone
 * @date 2023/09/17
 * @description 通用异常接口
 */
public interface BaseExceptionInterface {
    String getErrorCode();

    String getErrorMessage();
}
