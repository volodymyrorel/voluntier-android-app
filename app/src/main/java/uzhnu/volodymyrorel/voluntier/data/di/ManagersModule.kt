package uzhnu.volodymyrorel.voluntier.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uzhnu.volodymyrorel.voluntier.data.auth.AuthHelperImpl
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthHelper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ManagersModule {

    @Singleton
    @Binds
    internal abstract fun provideAuthHelper(impl: AuthHelperImpl): AuthHelper

}