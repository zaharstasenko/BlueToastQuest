package com.bluetoastquest.di

import com.bluetoastquest.core.ResourceProvider
import com.bluetoastquest.core.ResourceProviderImpl
import com.bluetoastquest.greeting.GreetingViewModel
import com.bluetoastquest.preferences.Storage
import com.bluetoastquest.preferences.StorageImpl
import com.bluetoastquest.quest.QuestViewModel
import com.bluetoastquest.questlist.QuestListViewModel
import com.bluetoastquest.questrepository.QuestRepository
import com.bluetoastquest.questrepository.QuestRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val commonModule = module {
    single<Storage> { StorageImpl(androidContext()) }
    single<QuestRepository> { QuestRepositoryImpl(get<Storage>()) }
    single<ResourceProvider> { ResourceProviderImpl(androidContext()) }
}

val questListModule = module {
    single { QuestListViewModel(get<QuestRepository>(), get<Storage>()) }
    single { QuestViewModel(get<QuestRepository>(), get<ResourceProvider>()) }
    single { GreetingViewModel() }
}