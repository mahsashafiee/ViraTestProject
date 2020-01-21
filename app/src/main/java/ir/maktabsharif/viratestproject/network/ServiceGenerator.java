package ir.maktabsharif.viratestproject.network;

import android.content.Context;

import com.squareup.moshi.Moshi;

import ir.maktabsharif.viratestproject.utils.Constants;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ServiceGenerator {

    private static Retrofit sRetrofit = null;
    private static Moshi sMoshi = new Moshi.Builder().build();
    private static long sCacheSize = 5 * 1024 * 1024;
    private static OkHttpClient sOkHttpClient;
    private static OkHttpClient.Builder sOkHttpClientBuilder;

    private static HttpLoggingInterceptor sHttpLoggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);


    private static OkHttpClient.Builder getClient() {
        if (sOkHttpClientBuilder == null)
            sOkHttpClientBuilder = new OkHttpClient.Builder()
                    .addInterceptor(sHttpLoggingInterceptor)
                    .addInterceptor(chain -> {
                        Request request = chain.request().newBuilder().build();
                        return chain.proceed(request);
                    });
        return sOkHttpClientBuilder;

    }

    public static <T> T createService(Class<T> serviceClass, Context context) {

        sOkHttpClient = getClient().cache(new Cache(context.getCacheDir(), sCacheSize)).build();

        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .client(sOkHttpClient)
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create(sMoshi))
                    .build();
        }
        return sRetrofit.create(serviceClass);
    }

}
