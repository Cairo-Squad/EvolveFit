package com.cairosquad.evolvefit.repository.exercise.remote

import com.cairosquad.evolvefit.repository.execption.callApi
import com.cairosquad.evolvefit.repository.exercise.remote.dto.ExerciseDto
import com.cairosquad.evolvefit.repository.exercise.remote.dto.ExerciseResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ExerciseRemoteDataSourceImpl(private val client: HttpClient) : ExerciseRemoteDataSource {
    override suspend fun createExercise(exercise: ExerciseDto): ExerciseResponseDto {
        return callApi<ExerciseResponseDto> {
            client.post("exercises") {
                contentType(ContentType.Application.Json)
                setBody(exercise)
            }
        }
    }
    override suspend fun getAllExercises(): List<ExerciseResponseDto> {
        return callApi<List<ExerciseResponseDto>> {
            client.get("exercise/all") {
                contentType(ContentType.Application.Json)
            }
        }
    }
}