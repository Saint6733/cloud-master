package com.cloud.common.Response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 统一返回数据结构
 * @author 冯亚鹏
 */
@Getter
@RequiredArgsConstructor
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = 1944249509295130512L;
    @Setter
    private Boolean success = Boolean.TRUE;
    private final String resultCode;
    private final String resultMsg;
    private final T resultData;

}
