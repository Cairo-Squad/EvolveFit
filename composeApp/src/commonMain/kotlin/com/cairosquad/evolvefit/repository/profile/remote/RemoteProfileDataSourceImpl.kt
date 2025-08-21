package com.cairosquad.evolvefit.repository.profile.remote

import com.cairosquad.evolvefit.repository.execption.callApi
import com.cairosquad.evolvefit.repository.profile.remote.dto.ProfileGetDto
import com.cairosquad.evolvefit.repository.profile.remote.dto.ProfilePostDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.post
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
            httpClient.get(USER_PROFILE_PATH).body()
        }
    }

    override suspend fun editProfile(profileRequest: ProfilePostDto): ProfileGetDto {
        val response = callApi<ProfileGetDto> {
            httpClient.put(USER_PROFILE_PATH) {
                contentType(ContentType.Application.Json)
                setBody(profileRequest)
            }
        }

        return response
    }

    override suspend fun uploadProfileImage(fileBytes: ByteArray, fileName: String): String {
        return callApi<String> {
            val response = httpClient.post("${USER_PROFILE_PATH}/image") {
                setBody(
                    MultiPartFormDataContent(
                        formData {
                            append(
                                key = "file",
                                value = fileBytes,
                                headers = Headers.build {
                                    append(HttpHeaders.ContentType, "multipart/form-data")
                                    append(HttpHeaders.ContentDisposition, "filename=\"$fileName\"")
                                }
                            )
                        }
                    ))
            }
            response.body()
        }
    }

    private companion object {
        const val USER_PROFILE_PATH = "user/profile"
    }
}