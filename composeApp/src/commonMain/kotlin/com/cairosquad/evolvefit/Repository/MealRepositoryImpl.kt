package com.cairosquad.evolvefit.repository

import com.cairosquad.evolvefit.domain.repository.MealRepository
import com.cairosquad.evolvefit.entity.DailyCalorieSummary
import com.cairosquad.evolvefit.entity.ConsumedMeal
import com.cairosquad.evolvefit.entity.SuggestedMeal
import com.cairosquad.evolvefit.remote.RemoteMealDataSource
import com.cairosquad.evolvefit.remote.mapper.toEntity
import com.cairosquad.evolvefit.remote.mapper.toRequestDto
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class MealRepositoryImpl(private val remote: RemoteMealDataSource) : MealRepository {
    override suspend fun getSuggestedMeals(): List<SuggestedMeal> {
        return remote.getSuggestedMeals()
    }

    override suspend fun getConsumedMealsForToday(): List<ConsumedMeal> {
        val currentMoment = Clock.System.now()
        val localDateTime = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())
        val startDate = "2025-08-01${startDayTime}"
        val endDate = "${localDateTime.date}${endDayTime}"
        val meals = remote.getMealsHistoryForToday(startDate, endDate)
            .map { it.toEntity() }
        println("grouped  repo: ${meals.toString()}")
        return meals
    }

    override suspend fun getAllMealsHistory(): List<ConsumedMeal> {
        val currentMoment = Clock.System.now()
        val localDateTime = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())
        val startDate = "${oldDate}${startDayTime}"
        val endDate = "${localDateTime.date}${endDayTime}"
        val meals = remote.getMealsHistoryForToday(startDate, endDate)
            .map { it.toEntity() }
        return meals
    }

    override suspend fun addConsumedMeal(consumedMeal: ConsumedMeal): Boolean {
        return remote.addMeal(consumedMeal.toRequestDto())
    }

    override suspend fun getDailyCalorieSummary(): DailyCalorieSummary {
        return remote.getDailySummary().toEntity()
    }

    private companion object {
        const val oldDate="2025-07-01"
        const val startDayTime = "T00:00:00"
        const val endDayTime = "T23:59:59"
    }
}