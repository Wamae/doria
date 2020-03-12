package com.company.doria.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    /* val gson = Gson()

     @TypeConverter
     fun stringToUserDetails(json: String?): User? {
         return gson.fromJson(json, User::class.java)
     }

     @TypeConverter
     fun userDetailsToString(user: User?): String? {
         return gson.toJson(user, object : TypeToken<User?>() {}.type)
     }

     @TypeConverter
     fun stringToConsignment(json: String?): Consignment? {
         return gson.fromJson(json, Consignment::class.java)
     }

     @TypeConverter
     fun consignmentToString(consignment: Consignment?): String? {
         return gson.toJson(consignment, object : TypeToken<Consignment?>() {}.type)
     }

     @TypeConverter
     fun stringToStringList(json: String): List<String> {
         return gson.fromJson(json, Array<String>::class.java).asList()
     }

     @TypeConverter
     fun stringListToString(ints: List<String>): String {
         return gson.toJson(ints, object : TypeToken<List<String>>() {}.type)
     }

     @TypeConverter
     fun stringToDrivers(json: String): List<Driver> {
         return gson.fromJson(json, Array<Driver>::class.java).asList()
     }

     @TypeConverter
     fun driversToString(driver: List<Driver>): String? {
         return gson.toJson(driver, object : TypeToken<List<Driver>>() {}.type)
     }

     @TypeConverter
     fun stringToInventoryDetails(json: String): List<InventoryDetails> {
         return gson.fromJson(json, Array<InventoryDetails>::class.java).asList()
     }

     @TypeConverter
     fun inventoryDetailsToString(details: List<InventoryDetails>): String? {
         return gson.toJson(details, object : TypeToken<List<Driver>>() {}.type)
     }

     @TypeConverter
     fun stringToJobDetails(json: String): JobDetail {
         return gson.fromJson(json, JobDetail::class.java)
     }

     @TypeConverter
     fun jobDetailsToString(details: JobDetail): String? {
         return gson.toJson(details, object : TypeToken<JobDetail>() {}.type)
     }


     @TypeConverter
     fun stringToAccessToken(value: String): AccessToken {
         return gson.fromJson(value, AccessToken::class.java)
     }

     @TypeConverter
     fun accessTokenToString(accessToken: AccessToken): String {
         return gson.toJson(accessToken, AccessToken::class.java)
     }

     @TypeConverter
     fun stringToNIOTDevice(value: String): NIOTDevice? {
         return gson.fromJson(value, NIOTDevice::class.java)
     }

     @TypeConverter
     fun NIOTDeviceToString(accessToken: NIOTDevice?): String {
         return gson.toJson(accessToken, NIOTDevice::class.java)
     }

 *//*    @TypeConverter
    fun stringToBaseMessage(value: String): BaseMessage {
        return gson.fromJson(value, BaseMessage::class.java)
    }

    @TypeConverter
    fun baseMessageToString(message: BaseMessage): String {
        return gson.toJson(message, BaseMessage::class.java)
    }*//*

    @TypeConverter
    fun stringToBaseMessageList(json: String?): List<String>? {
        return gson.fromJson(json, Array<String>::class.java)?.asList()
    }

    @TypeConverter
    fun baseMessageListToString(accessToken: List<String>?): String {
        return gson.toJson(accessToken, object : TypeToken<List<String>?>() {}.type)
    }*/


}