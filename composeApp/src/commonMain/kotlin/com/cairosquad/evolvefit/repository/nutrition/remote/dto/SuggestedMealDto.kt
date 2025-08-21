package com.cairosquad.evolvefit.repository.nutrition.remote.dto

import com.cairosquad.evolvefit.domain.model.MealType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SuggestedMealDto(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: MealType,
    @SerialName("calories")
    val calories: Int,
    @SerialName("imageUrl")
    val imageUrl:String
)

@Serializable
data class FavouriteMealDto(
    @SerialName("mealId")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("mealType")
    val type: MealType,

    @SerialName("calories")
    val calories: Int,

    @SerialName("imageUrl")
    val imageUrl: String
)
