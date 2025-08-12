package com.cairosquad.evolvefit.domain.usecase.nutrition

import com.cairosquad.evolvefit.domain.repository.NutritionRepository
import com.cairosquad.evolvefit.entity.nutrition.ConsumedMeal
import com.cairosquad.evolvefit.entity.nutrition.DailyCalorieSummary
import com.cairosquad.evolvefit.entity.nutrition.DailyWaterSummary
import com.cairosquad.evolvefit.entity.nutrition.Meal
import com.cairosquad.evolvefit.entity.nutrition.SuggestedMeal

class ManageNutritionUseCase(private val nutritionRepository: NutritionRepository) {
    suspend fun getSuggestedMeals(): List<SuggestedMeal>{
        return nutritionRepository.getSuggestedMeals()
    }
    suspend fun getFavouriteMeals(): List<SuggestedMeal>{
        return nutritionRepository.getFavouriteMeals()
    }
    suspend fun getMealHistory(): List<ConsumedMeal>{
        return nutritionRepository.getMealHistory()
    }
    suspend fun getConsumedMealsByDate(startDate: String, endDate: String): List<ConsumedMeal>{
        return nutritionRepository.getConsumedMealsByDate(startDate, endDate)
    }
    suspend fun getMealById(id: String): Meal{
        return nutritionRepository.getMealById(id)
    }
    suspend fun saveConsumedMeal(consumedMeal: ConsumedMeal): Boolean{
        return nutritionRepository.saveConsumedMeal(consumedMeal)
    }
    suspend fun getDailyCalorieSummary(): DailyCalorieSummary{
        return nutritionRepository.getDailyCalorieSummary()
    }
    suspend fun saveConsumedWater(amountLiters: Float): Boolean{
        return nutritionRepository.saveConsumedWater(amountLiters)
    }
    suspend fun getDailyWaterSummary(): DailyWaterSummary{
        return nutritionRepository.getDailyWaterSummary()
    }
}