package com.cairosquad.evolvefit.repository.profile.remote

import com.cairosquad.evolvefit.repository.execption.callApi
import com.cairosquad.evolvefit.repository.profile.dto.ProfileGetDto
import com.cairosquad.evolvefit.repository.profile.dto.ProfilePostDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType

class RemoteProfileDataSourceImpl(
    private val httpClient: HttpClient
) : RemoteProfileDataSource {
    override suspend fun getProfile(): ProfileGetDto {
        return callApi {
            httpClient.get("user/profile").body()
        }
    }

    override suspend fun editProfile(profileRequest: ProfilePostDto): ProfileGetDto {
        println("data source REQUEST: ${profileRequest.name}")

        val response = callApi<ProfileGetDto> {
            httpClient.put("user/profile") {
                contentType(ContentType.Application.Json)
                setBody(profileRequest)
            }
        }

        println("data source RESPONSE: ${response.name}")
        println("data source RESPONSE full: $response")

        return response
    }

    override suspend fun uploadProfileImage(fileBytes: ByteArray, fileName: String): ProfileGetDto {
        return callApi {
            httpClient.submitFormWithBinaryData(
                url = "user/profile/image",
                formData = formData {
                    append(
                        key = "file",
                        value = fileBytes,
                        headers = Headers.build {
                            append(HttpHeaders.ContentType, "image/jpeg")
                            append(
                                HttpHeaders.ContentDisposition,
                                "form-data; name=\"file\"; filename=\"$fileName\""
                            )
                        }
                    )
                }
            ).body()
        }
    }
}