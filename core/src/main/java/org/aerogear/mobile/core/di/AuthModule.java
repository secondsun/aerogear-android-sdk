package org.aerogear.mobile.core.di;

import android.app.Application;

import org.aerogear.mobile.core.auth.AuthInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AuthModule {

    // Constructor needs one parameter to instantiate.
    public AuthModule(String baseUrl) {
    }

    // Dagger will only look for methods annotated with @Provides
    @Provides
    @Singleton
    AuthInterceptor providesAuthInterceptor(Application application) {
        // TODO should pass configuration
        return new AuthInterceptor();
    }
 }
