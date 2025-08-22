package com.cairosquad.evolvefit.repository.nutrition.remote.dto

import com.cairosquad.evolvefit.domain.model.MealType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealDto(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("calories")
    val calories: Int,
    @SerialName("carbs")
    val carbs: Int,
    @SerialName("protein")
    val protein: Int,
    @SerialName("fat")
    val fat: Int,
    @SerialName("type")
    val type: MealType,
    @SerialName("ingredients")
    val ingredients: List<String>,
    @SerialName("imageUrl")
    val imageUrl: String,
)