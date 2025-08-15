package com.cairosquad.evolvefit.repository.workout.remote.dto

import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.repository.exercise.remote.dto.ExerciseDto
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorkoutDetailsDto(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("durationSeconds")
    val durationSeconds: Int,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("description")
    val description: String = "",
    @SerialName("level")
    val level: String = "",
    @SerialName("exercises")
    val exercises: List<ExerciseDto> = emptyList()
)

@Serializable
data class CreateWorkoutRequest(
    val name: String,
    val description: String,
    val level: String,
    val createdBy: String = "USER",
    val exercises: List<String>
)

@Serializable
data class WorkoutDto(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("durationSeconds")
    val durationSeconds: Int,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("focusArea")
    val focusArea: List<FocusArea>
)

