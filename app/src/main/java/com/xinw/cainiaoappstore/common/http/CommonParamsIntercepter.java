package com.xinw.cainiaoappstore.common.http;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.xinw.cainiaoappstore.common.constant.Constant;
import com.xinw.cainiaoappstore.common.util.ACache;
import com.xinw.cainiaoappstore.common.util.DensityUtil;
import com.xinw.cainiaoappstore.common.util.DeviceUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * byD9ing on 2017/8/17.
 * Describe: 公共参数请求拦截器
 * good luck
 */

public class CommonParamsIntercepter implements Interceptor {
    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private Context mContext;
    private Gson mGson;

    public CommonParamsIntercepter(Context mContext, Gson mGson) {
        this.mContext = mContext;
        this.mGson = mGson;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        try {
            String method = request.method();
            HashMap<String, Object> commomParamsMap = new HashMap<>();
            commomParamsMap.put(Constant.IMEI, DeviceUtils.getIMEI(mContext));
            commomParamsMap.put(Constant.MODEL, DeviceUtils.getModel());
            commomParamsMap.put(Constant.LANGUAGE, DeviceUtils.getLanguage());
            commomParamsMap.put(Constant.os, DeviceUtils.getBuildVersionIncremental());
            commomParamsMap.put(Constant.RESOLUTION, DensityUtil.getScreenW(mContext) + "*" + DensityUtil.getScreenH(mContext));
            commomParamsMap.put(Constant.SDK, DeviceUtils.getBuildVersionSDK() + "");
            commomParamsMap.put(Constant.DENSITY_SCALE_FACTOR, mContext.getResources().getDisplayMetrics().density + "");

            // TODO: send taken to Server
            String token = ACache.get(mContext).getAsString(Constant.TOKEN);
            commomParamsMap.put(Constant.TOKEN, token == null ? "" : token);


            if (method.equals("GET")) {
                HttpUrl httpUrl = request.url();


                // TODO: add common params to HashMap
                HashMap<String, Object> rootMap = new HashMap<>();
                // TODO: get All query Parameter
                Set<String> parameterNames = httpUrl.queryParameterNames();
                for (String key : parameterNames) {
                    // TODO: select destin PARAM
                    // TODO: 如果包含指定的PARAM 则插入到map中
                    if (Constant.PARAM.equals(key)) {
                        // TODO: oldParamJson
                        String oldParamJson = httpUrl.queryParameter(Constant.PARAM);
                        if (oldParamJson != null) {
                            // TODO: get Origin Params Map
                            HashMap<String, Object> p = mGson.fromJson(oldParamJson, HashMap.class);
                            if (p != null) {
                                for (Map.Entry<String, Object> entry : p.entrySet()) {
                                    rootMap.put(entry.getKey(), entry.getValue());
                                }
                            }

                        }
                    } else {
                        rootMap.put(key, httpUrl.queryParameter(key));
                    }
                }
                // TODO: add publicParams
                rootMap.put("publicParams", commomParamsMap);
                // TODO: cover to jsonString
                String newJsonParam = mGson.toJson(rootMap);
                String url = httpUrl.toString();
                // TODO: change Url
                int index = url.indexOf("?");
                if (index > 0) {
                    url = url.substring(0, index);
                }
                url = url + "?" + Constant.PARAM + "=" + newJsonParam; //  http://112.124.22.238:8081/course_api/cniaoplay/featured?p= {"page":0,"publicParams":{"imei":'xxxxx',"sdk":14,.....}}

                // TODO: Build new Request
                request = request.newBuilder().url(url).build();


            } else if (method.equals("POST")) {
//                RequestBody body = request.body();
//                HashMap<String, Object> rootMap = new HashMap<>();
//                Buffer buffer = new Buffer();
//                body.writeTo(buffer);
//                String oldJsonParams = buffer.readUtf8();
//                rootMap = mGson.fromJson(oldJsonParams, HashMap.class);
//                //重新组装
//                rootMap.put("publicParams", rootMap);
//                String newParamJson = mGson.toJson(rootMap);
//                //重新构造请求
//                request = request.newBuilder().post(RequestBody.create(JSON, newParamJson)).build();


                RequestBody body = request.body();
                HashMap<String, Object> rootMap = new HashMap<>();
                if (body instanceof FormBody) { // form 表单
                    for (int i = 0; i < ((FormBody) body).size(); i++) {
                        rootMap.put(((FormBody) body).encodedName(i), ((FormBody) body).encodedValue(i));
                    }
                } else {
                    Buffer buffer = new Buffer();
                    body.writeTo(buffer);
                    String oldJsonParams = buffer.readUtf8();
                    rootMap = mGson.fromJson(oldJsonParams, HashMap.class); // 原始参数
                    rootMap.put("publicParams", commomParamsMap); // 重新组装
                    String newJsonParams = mGson.toJson(rootMap); // {"page":0,"publicParams":{"imei":'xxxxx',"sdk":14,.....}}
                    request = request.newBuilder().post(RequestBody.create(JSON, newJsonParams)).build();
                }
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        // TODO: new Request
        return chain.proceed(request);
    }
}
