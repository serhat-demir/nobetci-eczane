package com.serhat.nobetcieczanemvvm.api;

import com.serhat.nobetcieczanemvvm.BuildConfig;

public class ApiUtils {
    public static final String BASE_URL = "https://api.collectapi.com/health/";
    public static final String API_KEY = BuildConfig.API_KEY;

    public static ApiInterface getApiInterface() {
        return ApiClient.getClient(BASE_URL).create(ApiInterface.class);
    }
}
