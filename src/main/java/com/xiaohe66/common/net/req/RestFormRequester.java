package com.xiaohe66.common.net.req;

import com.xiaohe66.common.net.AbstractCallback;
import com.xiaohe66.common.util.BeanUtils;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author xiaohe
 * @time 2020.07.19 19:00
 */
public abstract class RestFormRequester<P, C extends AbstractCallback> extends RestRequester<P, C> {

    public RestFormRequester(String baseUrl, String queryUrl, OkHttpClient httpClient) {
        super(baseUrl, queryUrl, httpClient);
    }

    @Override
    protected Request buildPostRequest(P param) {

        return new Request.Builder()
                .url(baseUrl + queryUrl)
                .post(createFormBody(param))
                .build();
    }

    @Override
    protected Request buildPutRequest(P param) {

        return new Request.Builder()
                .url(baseUrl + queryUrl)
                .post(createFormBody(param))
                .build();
    }

    protected RequestBody createFormBody(P param) {

        FormBody.Builder builder = new FormBody.Builder();
        BeanUtils.eachField(param, (key, val) ->
                builder.add(key,val == null ? "" : val.toString()));

        return builder.build();
    }

}
