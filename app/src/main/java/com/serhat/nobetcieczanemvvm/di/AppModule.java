package com.serhat.nobetcieczanemvvm.di;

import android.content.Context;

import androidx.room.Room;

import com.serhat.nobetcieczanemvvm.api.ApiInterface;
import com.serhat.nobetcieczanemvvm.api.ApiUtils;
import com.serhat.nobetcieczanemvvm.data.repository.EczaneRepository;
import com.serhat.nobetcieczanemvvm.data.repository.IlceRepository;
import com.serhat.nobetcieczanemvvm.room.DB;
import com.serhat.nobetcieczanemvvm.room.IlceDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public EczaneRepository provideEczaneRepository(ApiInterface apiService) {
        return new EczaneRepository(apiService);
    }

    @Provides
    @Singleton
    public ApiInterface provideApiInterface() {
        return ApiUtils.getApiInterface();
    }

    @Provides
    @Singleton
    public IlceRepository provideIlceRepository(IlceDao iDao) {
        return new IlceRepository(iDao);
    }

    @Provides
    @Singleton
    public IlceDao provideIlceDao(@ApplicationContext Context context) {
        DB db = Room.databaseBuilder(context, DB.class, "nobetci_eczane.sqlite")
                .createFromAsset("nobetci_eczane.sqlite")
                .build();

        return db.getIlceDao();
    }
}
