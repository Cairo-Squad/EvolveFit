package com.cairosquad.evolvefit.repository.exercise.remote

import com.cairosquad.evolvefit.repository.execption.callApi
import com.cairosquad.evolvefit.repository.exercise.remote.dto.ExerciseDto
import com.cairosquad.evolvefit.repository.exercise.remote.dto.ExerciseResponseDto
import com.cairosquad.evolvefit.repository.utils.HttpClientHolder
import io.ktor.client.call.body
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.parameter
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType

class ExerciseRemoteDataSourceImpl(private val client: HttpClientHolder) : ExerciseRemoteDataSource {
    override suspend fun createExercise(exercise: ExerciseDto):ExerciseResponseDto {
      return  callApi<ExerciseResponseDto> {
            client.post("${EXERCISE_PATH}/create") {
                contentType(ContentType.Application.Json)
                setBody(exercise)
            }
        }
    }

    override suspend fun uploadExerciseImage(
        fileBytes: ByteArray,
        fileName: String,
        exerciseId : String
    ): String {
        return callApi<String> {
            val response = client.put("${EXERCISE_PATH}/image") {
                parameter("exerciseId",exerciseId)
                setBody(
                    MultiPartFormDataContent(
                        formData {
                            append(
                                key = "image",
                                value = fileBytes,
                                headers = Headers.build {
                                    append(HttpHeaders.ContentType, "multipart/form-data")
                                    append(HttpHeaders.ContentDisposition, "filename=\"$fileName\"")
                                }
                            )
                        }
                    )
                )
            }
            response.body()
        }
    }

    override suspend fun getExercisesByQuery(query: String): List<ExerciseResponseDto> {
        return callApi<List<ExerciseResponseDto>> {
            client.get("${EXERCISE_PATH}/search").body()
        }
    }

    override suspend fun getAllExercises(): List<ExerciseResponseDto> {
        return callApi<List<ExerciseResponseDto>> {
            client.get("${EXERCISE_PATH}/all") {
                contentType(ContentType.Application.Json)
            }
        }
    }

    private companion object {
        const val EXERCISE_PATH = "exercise"
    }
}