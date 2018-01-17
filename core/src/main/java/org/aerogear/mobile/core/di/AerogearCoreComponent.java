/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.aerogear.mobile.core.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        AuthModule.class,
        ConfigModule.class,
        NetModule.class
})
public interface AerogearCoreComponent {

    // This will be used to inject top level Application
    // Usage
    /**
     AerogearCoreComponent.builder()
         // list of modules that are part of this component need to be created here
         .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
            .netModule(new NetModule())
            .build();
     **/



}
