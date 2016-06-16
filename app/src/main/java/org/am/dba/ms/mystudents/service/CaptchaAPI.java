package org.am.dba.ms.mystudents.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Beschi Antony D on 14/6/2016.
 */
public interface CaptchaAPI {
    @GET
    Call<ResponseBody> getCaptcha(@Url String url);

/*    Call<ResponseBody> call = captchaAPI.getCaptcha(url);
    call.enqueue(new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    // display the image data in a ImageView or save it
                    Bitmpa bm = BitmapFactory.decodeStream(response.body().byteStream());
                    msCaptcha.setImageBitmap(bm);
                } else {
                    // TODO
                }
            } else {
                // TODO
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            // TODO
        }
    });*/
}
