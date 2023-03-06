package com.learning.sepiataskapp.data.repository

import com.google.gson.Gson
import com.learning.sepiataskapp.data.model.ConfigResponse
import com.learning.sepiataskapp.data.model.PetResponse
import com.learning.sepiataskapp.utils.Constants
import com.learning.sepiataskapp.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class PetRepository {

    /** Use this function to perform your network or database operation for fetching the pet list **/
    suspend fun fetchPetList(): PetResponse =
        withContext(Dispatchers.IO) {
            Gson().fromJson(Constants.PET_RESPONSE, PetResponse::class.java)
        }

    suspend fun allowAppAccess(): Boolean =
        withContext(Dispatchers.IO) {
            val response = Gson().fromJson(Constants.CONFIG, ConfigResponse::class.java)
            val config = response.settings
            checkAccess(config?.workHours)
        }

    private fun checkAccess(workHours: String?): Boolean {
        val calendar = Calendar.getInstance()
        val currentDay = calendar.get(Calendar.DAY_OF_WEEK)
        if (!workHours.isNullOrBlank()) {
            try {
                val dateFormat = SimpleDateFormat("HH:mm", Locale.ENGLISH)
                val spitWorkHrs = workHours.split(" ")
                if (spitWorkHrs.size == 4) {
                    val workingDays = spitWorkHrs[0]
                    val splitDays = workingDays.split("-")
                    if (splitDays.size == 2) {
                        if (currentDay < Utils.getDay(splitDays[0]) || currentDay > Utils.getDay(splitDays[1])){}
                            //return false
                    }
                    val startTime = Calendar.getInstance()
                    val parsedStartTime = dateFormat.parse(spitWorkHrs[1])
                    startTime
                    val endTime = Calendar.getInstance()
                    val parsedEndTime = dateFormat.parse(spitWorkHrs[3])
                    if (startTime != null && endTime != null && startTime.before(calendar.time) && endTime.after(calendar.time)) {
                        return true
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return false
    }
}