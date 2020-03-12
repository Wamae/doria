package com.company.doria.api

import androidx.lifecycle.LiveData
import com.company.doria.api.auth.LoginRequest
import com.company.doria.api.auth.LoginResponse
import com.company.doria.api.incident.IncidentRequest
import com.company.doria.db.persistence.incident.Incident
import com.company.doria.db.persistence.incident_type.IncidentType
import retrofit2.Call
import retrofit2.http.*

interface DoriaApiService {

    @Headers("No-Authentication: true")
    @POST("login.json")
    fun login(@Body credentials: LoginRequest): LiveData<ApiResponse<LoginResponse>>

    @GET("incident_types")
    fun getIncidentTypesSync(): Call<List<IncidentType>>

    @GET("incidents")
    fun getIncidentsSync(): Call<List<Incident>>

    @GET("incidents")
    fun getIncidents(): LiveData<ApiResponse<List<Incident>>>

    @POST("incidents")
    fun createIncident(@Body request: IncidentRequest): LiveData<ApiResponse<Incident>>

/*    @POST("getAllConsignments/{id}")
    fun getAllConsignments(@Path("id") userId: Long): LiveData<ApiResponse<List<ConsignmentBase>>>

    @GET("tag/getUnAssignedTags/{id}")
    fun getUnAssignedTags(@Path("id") userId: Long): @GET("incidents")
    fun getIncidentsSync(@Header("Authorization") accessToken: String): Call<List<Incident>>

    @GET("tag/getUnAssignedTags/{id}")
    fun getUnassignedTagsSync(@Header("Authorization") accessToken: String, @Path("id") userId: Long): Call<List<QuupaTag>>

    @GET("space/getUnAssignedSpaces/{id}")
    fun getUnAssignedSpaces(@Path("id") userId: Long): LiveData<ApiResponse<List<QuupaSpace>>>

    @GET("space/getUnAssignedSpaces/{id}")
    fun getUnassignedSpacesSync(@Header("Authorization") accessToken: String, @Path("id") userId: Long): Call<List<QuupaSpace>>

    @GET("forklift/getAllForklifts/{id}")
    fun getAllForklifts(): LiveData<ApiResponse<List<Forklift>>>

    @GET("forklift/getAllForklifts")
    fun getForkliftsSync(@Header("Authorization") accessToken: String): Call<List<Forklift>>

    @POST("inventory/entry")
    fun createInventory(@Body entry: CreateInventoryBaseRequest): LiveData<ApiResponse<BaseResponse>>

    @POST("inventory/entry")
    fun createInventorySync(@Header("Authorization") accessToken: String): Call<BaseResponse>

//    TODO: Benson Change below to correct endpoints

    @GET("users")
    fun getAllDrivers(): LiveData<ApiResponse<DriversResponseBase>>

//    @GET("user/getAllForkliftDrivers/{id}")
//    fun getAllDrivers(@Path("id") userId: Long): LiveData<ApiResponse<DriversResponseBase>>

    @GET("users")
    fun getAllDriversSync(@Header("Authorization") accessToken: String): Call<DriversResponseBase>

    *//*    @GET("user/getAllForkliftDrivers/{id}")
        fun getAllDriversSync(@Header("Authorization") accessToken: String, @Path("id") userId: Long): Call<DriversResponseBase>*//*
    @GET("jobs")
    fun getAllJobs(@Query("userId") userId: Long): LiveData<ApiResponse<List<JobBase>>>

    @GET("jobs")
    fun getAllJobsSync(@Header("Authorization") accessToken: String, @Query("userId") userId: Long?): Call<List<JobBase>>

    *//*@POST("job/start")
    fun startJob(@Body jobRequest: JobRequest): LiveData<ApiResponse<BaseResponse>>*//*

    @POST("job/start")
    fun startJob(@Query("jobId") jobId: String, @Query("driverId") userId: Long): LiveData<ApiResponse<BaseResponse>>
*//*
    @POST("job/end")
    fun finishJob(@Body jobRequest: JobRequest): LiveData<ApiResponse<BaseResponse>>*//*

    @POST("job/end")
    fun finishJob(@Query("jobId") jobId: String, @Query("driverId") userId: Long): LiveData<ApiResponse<BaseResponse>>*/
}

