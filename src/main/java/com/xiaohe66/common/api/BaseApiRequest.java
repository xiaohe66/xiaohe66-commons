package com.xiaohe66.common.api;

import java.util.Collections;
import java.util.Map;

/**
 * @author xiaohe
 * @time 2021.07.14 14:29
 */
public abstract class BaseApiRequest<E extends BaseApiResponse> implements IApiRequest<E> {

    private final Method method;
    private Map<String, String> header = Collections.emptyMap();
    private IApiModel model;

    public BaseApiRequest(Method method) {
        this.method = method;
    }

    @Override
    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    @Override
    public IApiModel getModel() {
        return model;
    }

    public void setModel(IApiModel model) {
        this.model = model;
    }

    @Override
    public Method getMethod() {
        return method;
    }

}
