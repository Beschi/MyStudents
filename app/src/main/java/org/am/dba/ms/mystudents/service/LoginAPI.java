package org.am.dba.ms.mystudents.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by Beschi Antony D  on 8/6/2016.
 */
public interface LoginAPI {
    @Headers({
            "User-Agent: MyStudents-App"
    })
    @GET("login/{username}/{pass}")
    Call<String> getUserValidated(@Path("username") String username, @Path("pass") String pass);
}
