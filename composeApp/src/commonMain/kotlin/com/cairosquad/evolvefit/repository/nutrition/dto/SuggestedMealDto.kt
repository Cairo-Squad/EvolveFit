package com.cairosquad.evolvefit.repository.nutrition.dto

import com.cairosquad.evolvefit.domain.entity.nutrition.MealType
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