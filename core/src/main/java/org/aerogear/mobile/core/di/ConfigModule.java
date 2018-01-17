package org.aerogear.mobile.core.di;


import android.app.Application;

import org.aerogear.mobile.core.configuration.ServiceConfiguration;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ConfigModule {


    // Constructor needs one parameter to instantiate.
    public ConfigModule(String baseUrl) {
    }

    @Provides
    @Singleton
    ServiceConfiguration providesServiceConfiguration(Application application) {
        // TODO migrate configuration setup from bootstrap
        return new ServiceConfiguration();
    }
}