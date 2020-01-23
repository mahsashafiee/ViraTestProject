package ir.maktabsharif.viratestproject.network;

import android.content.Context;

import ir.maktabsharif.viratestproject.utils.Constants;
import ir.maktabsharif.viratestproject.utils.Utils;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ServiceGenerator {

    private static Retrofit sRetrofit = null;
    private static long sCacheSize = 10 * 1024 * 1024;
    private static OkHttpClient sOkHttpClient;


    private static OkHttpClient getClient(Context context) {
        if (sOkHttpClient == null)
            sOkHttpClient = new OkHttpClient.Builder()
                    .cache(new Cache(context.getCacheDir(), sCacheSize))
                    .addInterceptor(chain -> {
                        Request request = chain.request();
                        if (Utils.isNetworkAvailableAndConected(context))
                            request = request.newBuilder().addHeader("Cache-Control", "public, max-age=" + 5).build();
                        else
                            request = request.newBuilder().addHeader("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();

                        return chain.proceed(request);
                    })
                    .build();

        return sOkHttpClient;

    }

    public static <T> T createService(Class<T> serviceClass, Context context) {

        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .client(getClient(context))
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build();
        }
        return sRetrofit.create(serviceClass);
    }

}
