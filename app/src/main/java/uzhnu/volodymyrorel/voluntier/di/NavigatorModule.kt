package uzhnu.volodymyrorel.voluntier.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uzhnu.volodymyrorel.voluntier.data.navigation.NavigatorImpl
import uzhnu.volodymyrorel.voluntier.domain.navigation.Navigator
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigatorModule {

    @Singleton
    @Binds
    internal abstract fun provideNavigator(impl: NavigatorImpl): Navigator
}