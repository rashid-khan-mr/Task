package com.appointment.myapplication.data.remote


import android.util.Log
import com.appointment.myapplication.data.model.DrugInfo
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.JsonPrimitive
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.flow.*
import okhttp3.ResponseBody
import okhttp3.internal.wait
import retrofit2.Response
import java.io.StringReader

inline fun <ResultType> networkOnly(
    crossinline fetch: suspend () -> Response<ResponseBody>,
    crossinline onFetchFailed: (Throwable) -> Unit
) = flow<Resource<List<DrugInfo>>> {
    emit(Resource.loading(null))
    Log.v("calling", "inside network only")

    try {
        val response = fetch()
        Log.v("calling", "response")

        if (response.isSuccessful) {
            response.body()?.let { responseBody ->
                // Read the response body as a string
                val jsonString = responseBody.string()
                Log.v("RawResponse", "jsonString = $jsonString")

                val jsonReader = JsonReader(StringReader(jsonString))
                jsonReader.isLenient = true // Allow lenient parsing of malformed JSON

                // Parse the entire response as a JSON element
                val jsonElement = JsonParser.parseReader(jsonReader)

                // Log the full JSON structure to ensure we're parsing the entire response
                Log.v("RawResponse", "jsonelement =  "+jsonElement.toString())
                val drugInfoList = mutableListOf<DrugInfo>()

                // Check if the "problems" field is a JsonArray
                if (jsonElement is JsonObject) {
                    val jsonObject = jsonElement.asJsonObject
                    Log.v("Problems object ", "object : "+jsonObject.toString())
                    val problemsArray: JsonArray = jsonObject["problems"].asJsonArray

                    // Log the problemsArray to see its contents
                    Log.v("Problems Array", "array "+problemsArray.toString())

                    if (problemsArray != null) {
                        // Iterate through each problem entry (e.g., Diabetes, Asthma)
                        for (problemElement in problemsArray) {
                            if (problemElement is JsonObject) {
                                // Loop through each disease (e.g., "Diabetes", "Asthma")
                                for ((key, value) in problemElement.entrySet()) {
                                    // If there are medications, loop through the medication data
                                    val diseaseMedications = value.asJsonArray
                                    for (medicationEntry in diseaseMedications) {
                                        if (medicationEntry is JsonObject) {
                                            val medicationsArray = medicationEntry["medications"]?.asJsonArray
                                            medicationsArray?.forEach { medication ->
                                                if (medication is JsonObject) {
                                                    val medicationsClasses = medication["medicationsClasses"]?.asJsonArray
                                                    medicationsClasses?.forEach { medicationClass ->
                                                        if (medicationClass is JsonObject) {
                                                            // Loop through each className (e.g., "className", "className2")
                                                            medicationClass.entrySet().forEach { (classNameKey, classNameValue) ->
                                                                val associatedDrugs = classNameValue.asJsonArray
                                                                // Loop through each associatedDrug (e.g., associatedDrug, associatedDrug#2)
                                                                associatedDrugs.forEach { associatedDrug ->
                                                                    if (associatedDrug is JsonObject) {
                                                                        associatedDrug.entrySet().forEach { (classNameKey, drug) ->
                                                                            val drugList: List<DrugInfo> = Gson().fromJson(drug, object : TypeToken<List<DrugInfo>>() {}.type)

                                                                            drugInfoList.addAll(drugList)

                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        Log.e("Error", "Problems array is missing or null.")
                    }


                    Log.v("DrugInfoList", "size"+drugInfoList.size)
                    drugInfoList.forEach {
                        Log.v("DrugInfoList", "name ="+it.name)
                        Log.v("DrugInfoList", "dose ="+it.dose)
                        Log.v("DrugInfoList", "strength ="+it.strength)
                    }
                } else {
                    // Handle case where the root element is not a JsonObject
                    Log.e("Error", "Expected a JSON object but got a different structure")
                }
                val drugList = drugInfoList.mapIndexed { index, drugInfo ->
                    drugInfo.copy(id = index)
                }.toMutableList()
                emit(Resource.success(data = drugList))

            } ?: emit(Resource.error("Response body is null", null, null))
        } else {
            emit(
                Resource.error(
                    msg = response.message(),
                    statusCode = response.code(),
                    data = null
                )
            )
        }
    } catch (e: Exception) {
        Log.v("calling", "exception occurred")
        onFetchFailed(e)
        emit(Resource.error(msg = "error: ${e.localizedMessage}", statusCode = null, data = null))
    }
}
