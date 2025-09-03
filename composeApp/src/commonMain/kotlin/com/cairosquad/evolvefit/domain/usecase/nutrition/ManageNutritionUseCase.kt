package com.cairosquad.evolvefit.domain.usecase.nutrition

import com.cairosquad.evolvefit.domain.entity.ConsumedMeal
import com.cairosquad.evolvefit.domain.entity.DailyCalorieSummary
import com.cairosquad.evolvefit.domain.entity.DailyWaterSummary
import com.cairosquad.evolvefit.domain.entity.Meal
import com.cairosquad.evolvefit.domain.entity.SuggestedMeal
import com.cairosquad.evolvefit.domain.exception.ExceededCaloriesException
import com.cairosquad.evolvefit.domain.exception.ExceededWaterLimitException
import com.cairosquad.evolvefit.domain.exception.InvalidNumberFormatException
import com.cairosquad.evolvefit.domain.exception.LengthTooLargeException
import com.cairosquad.evolvefit.domain.exception.MealNotFoundException
import com.cairosquad.evolvefit.domain.exception.NumberTooLargeException
import com.cairosquad.evolvefit.domain.model.FieldType
import com.cairosquad.evolvefit.domain.repository.NutritionRepository
import com.cairosquad.evolvefit.domain.usecase.utils.keepOneDecimal
import com.cairosquad.evolvefit.domain.usecase.utils.validateNumberInput

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
        return nutritionRepository.getMealHistory(startDate, endDate)
    }

    suspend fun getConsumedMealsByDate(startDate: String, endDate: String): List<ConsumedMeal> {
        return nutritionRepository.getConsumedMealsByDate(startDate, endDate)
    }

    suspend fun getMealById(id: String): Meal {
        return nutritionRepository.getMealById(id)
            ?: throw MealNotFoundException(message = "Meal with id=$id not found")
    }

    suspend fun getDailyCalorieSummary(): DailyCalorieSummary {
        return nutritionRepository.getDailyCalorieSummary()
    }

    suspend fun getDailyWaterSummary(): DailyWaterSummary {
        return nutritionRepository.getDailyWaterSummary()
    }

    suspend fun saveConsumedMeal(
        consumedMealCaloriesInput: String,
        consumedMeal: ConsumedMeal,
        remainingCalories: Int
    ): Boolean {
        validateCaloriesInput(consumedMealCaloriesInput)
        validateMealName(consumedMeal.name)
        val enteredCalories = parseCalories(consumedMealCaloriesInput)
        validateRemainingCalories(enteredCalories, remainingCalories)
        val updatedMeal = consumedMeal.copy(calories = enteredCalories)
        return nutritionRepository.saveConsumedMeal(updatedMeal)
    }

    suspend fun saveConsumedWater(amountInput: String, remainingWater: Float): Boolean {
        val trimmedInput = amountInput.trim()
        validateNumberInput(trimmedInput, FieldType.WATER_INPUT)
        if (trimmedInput.length > 4) throw NumberTooLargeException(FieldType.WATER_INPUT)
        val enteredLiters = trimmedInput.toFloat().keepOneDecimal()
        validateRemainingWater(enteredLiters, remainingWater)
        return nutritionRepository.saveConsumedWater(enteredLiters)
    }

    private fun validateRemainingWater(amount: Float, remainingWater: Float) {
        if (amount > remainingWater) throw ExceededWaterLimitException()
    }

    private fun validateCaloriesInput(input: String) {
        validateNumberInput(input, FieldType.MEAL_CALORIES)
        if (input.length > 5) throw NumberTooLargeException(FieldType.MEAL_CALORIES)
    }

    private fun validateMealName(name: String) {
        if (name.length > 40) throw LengthTooLargeException(FieldType.MEAL_NAME)
    }

    private fun parseCalories(input: String): Int {
        return input.toIntOrNull() ?: throw InvalidNumberFormatException(FieldType.MEAL_CALORIES)
    }

    private fun validateRemainingCalories(calories: Int, remainingCalories: Int) {
        if (calories >remainingCalories) throw ExceededCaloriesException()
    }


}