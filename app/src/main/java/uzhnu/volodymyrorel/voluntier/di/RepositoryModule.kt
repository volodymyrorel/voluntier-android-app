package uzhnu.volodymyrorel.voluntier.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uzhnu.volodymyrorel.voluntier.data.auth.AuthRepositoryImpl
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    internal abstract fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}