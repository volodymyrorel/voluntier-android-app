package uzhnu.volodymyrorel.voluntier.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uzhnu.volodymyrorel.voluntier.data.answer.AnswerRepositoryImpl
import uzhnu.volodymyrorel.voluntier.data.auth.AuthRepositoryImpl
import uzhnu.volodymyrorel.voluntier.data.demand.DemandRepositoryImpl
import uzhnu.volodymyrorel.voluntier.data.organization.OrganizationRepositoryImpl
import uzhnu.volodymyrorel.voluntier.domain.answer.AnswerRepository
import uzhnu.volodymyrorel.voluntier.domain.auth.AuthRepository
import uzhnu.volodymyrorel.voluntier.domain.demand.DemandRepository
import uzhnu.volodymyrorel.voluntier.domain.organization.OrganizationRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    internal abstract fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Singleton
    @Binds
    internal abstract fun provideDemandRepository(impl: DemandRepositoryImpl): DemandRepository

    @Singleton
    @Binds
    internal abstract fun provideOrganizationRepository(impl: OrganizationRepositoryImpl): OrganizationRepository

    @Singleton
    @Binds
    internal abstract fun provideAnswerRepository(impl: AnswerRepositoryImpl): AnswerRepository
}