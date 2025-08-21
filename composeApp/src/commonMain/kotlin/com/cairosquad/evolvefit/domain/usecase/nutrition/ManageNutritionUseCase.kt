package com.cairosquad.evolvefit.domain.usecase.nutrition

import com.cairosquad.evolvefit.domain.exceptions.ExceededCaloriesException
import com.cairosquad.evolvefit.domain.exceptions.ExceededWaterLimitException
import com.cairosquad.evolvefit.domain.exceptions.InvalidNumberFormatException
import com.cairosquad.evolvefit.domain.exceptions.MealNotFoundException
import com.cairosquad.evolvefit.domain.repository.NutritionRepository
import com.cairosquad.evolvefit.entity.nutrition.ConsumedMeal
import com.cairosquad.evolvefit.entity.nutrition.DailyCalorieSummary
import com.cairosquad.evolvefit.entity.nutrition.DailyWaterSummary
import com.cairosquad.evolvefit.entity.nutrition.Meal
import com.cairosquad.evolvefit.entity.nutrition.SuggestedMeal

class ManageNutritionUseCase(private val nutritionRepository: NutritionRepository) {
    suspend fun getSuggestedMeals(): List<SuggestedMeal> {
        return nutritionRepository.getSuggestedMeals()
    }

    suspend fun getFavouriteMeals(): List<SuggestedMeal> {
        return nutritionRepository.getFavouriteMeals()
    }
    suspend fun addFavouriteMealById(mealId: String) {
        return nutritionRepository.addFavouriteMealById(mealId)
    }
    suspend fun deleteFavouriteMeal(mealId: String) {
        return nutritionRepository.deleteFavouriteMeal(mealId)
    }

    suspend fun getMealHistory(startDate: String, endDate: String): List<ConsumedMeal> {
        return nutritionRepository.getMealHistory(startDate,endDate)
    }

    suspend fun getConsumedMealsByDate(startDate: String, endDate: String): List<ConsumedMeal> {
        return nutritionRepository.getConsumedMealsByDate(startDate, endDate)
    }

    suspend fun getMealById(id: String): Meal {
        return nutritionRepository.getMealById(id)
            ?: throw MealNotFoundException(message = "Meal with id=$id not found")
    }

    suspend fun saveConsumedMeal(
        consumedMealCaloriesInput: String,
        consumedMeal: ConsumedMeal,
        remainingCalories: Float
    ): Boolean {
        validateNumberInput(consumedMealCaloriesInput)

        val enteredCalories = consumedMealCaloriesInput.toFloatOrNull()
            ?: throw InvalidNumberFormatException()

        if (enteredCalories > remainingCalories) {
            throw ExceededCaloriesException()
        }
        val updatedMeal = consumedMeal.copy(calories = enteredCalories.toInt())

        return nutritionRepository.saveConsumedMeal(updatedMeal)
    }
    suspend fun saveConsumedWater(amountInput: String, remainingWater: Float): Boolean {
        validateNumberInput(amountInput)

        val amountLiters = amountInput.toFloatOrNull()
            ?: throw InvalidNumberFormatException()

        if (amountLiters > remainingWater) {
            throw ExceededWaterLimitException()
        }

        return nutritionRepository.saveConsumedWater(amountLiters)
    }


    suspend fun getDailyCalorieSummary(): DailyCalorieSummary {
        return nutritionRepository.getDailyCalorieSummary()
    }


    suspend fun getDailyWaterSummary(): DailyWaterSummary {
        return nutritionRepository.getDailyWaterSummary()
    }
}

private fun validateNumberInput(value: String) {
    val regex = Regex("^[0-9]*\\.?[0-9]*$")
    if (!regex.matches(value)) {
        throw InvalidNumberFormatException()
    }
}