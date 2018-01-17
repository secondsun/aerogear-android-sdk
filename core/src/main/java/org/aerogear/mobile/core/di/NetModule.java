package org.aerogear.mobile.core.di;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.aerogear.mobile.core.auth.AuthInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

@Module
public class NetModule {


    // Constructor needs one parameter to instantiate.
    public NetModule() {
    }

    // Dagger will only look for methods annotated with @Provides
    @Provides
    @Singleton
    // Application reference must come from AppModule.class
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    //TODO it may be signgleton with custom rul
    OkHttpClient provideOkHttpClient(Cache cache, AuthInterceptor authInterceptor) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        // TODO add network interceptor logic once is there
        // client.addNetworkInterceptor(authInterceptor);

        // Users can override this on build time by adding additional elements to dependency
        // client.proxy() etc.
        client.cache(cache);
        return client.build();
    }

}