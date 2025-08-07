package com.cairosquad.evolvefit.repository

import com.cairosquad.evolvefit.domain.repository.MealRepository
import com.cairosquad.evolvefit.entity.DailySummary
import com.cairosquad.evolvefit.entity.Meal
import com.cairosquad.evolvefit.entity.SuggestedMeal
import com.cairosquad.evolvefit.remote.RemoteMealDataSource
import com.cairosquad.evolvefit.remote.toEntity
import com.cairosquad.evolvefit.remote.toRequestDto
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class MealRepositoryImpl(  private val remote: RemoteMealDataSource): MealRepository {
    override suspend fun getSuggestedMeals(): List<SuggestedMeal> {
        return remote.getSuggestedMeals()
    }

    override suspend fun getMealHistoryForToday(): List<Meal> {
        val currentMoment = Clock.System.now()
        val localDateTime = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())
        val startDate = "${localDateTime.date}T00:00:00"
        val endDate = "${localDateTime.date}T23:59:59"

        return remote.getMeals(startDate, endDate)
            .map { it.toEntity() }
    }

    override suspend fun addMeal(meal: Meal): Boolean {
        return remote.addMeal(meal.toRequestDto())
    }

    override suspend fun getDailySummary(): DailySummary {
        return remote.getDailySummary().toEntity()
    }
    fun formatKotlinxDateTime(dateTime: kotlinx.datetime.LocalDateTime): String {
        return buildString {
            append(dateTime.year.toString().padStart(4, '0'))
            append("-")
            append(dateTime.monthNumber.toString().padStart(2, '0'))
            append("-")
            append(dateTime.dayOfMonth.toString().padStart(2, '0'))
            append("T")
            append(dateTime.hour.toString().padStart(2, '0'))
            append(":")
            append(dateTime.minute.toString().padStart(2, '0'))
            append(":")
            append(dateTime.second.toString().padStart(2, '0'))
        }
    }
}