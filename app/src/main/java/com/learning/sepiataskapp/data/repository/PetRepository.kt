package com.learning.sepiataskapp.data.repository

import android.util.Log
import com.google.gson.Gson
import com.learning.sepiataskapp.data.model.ConfigResponse
import com.learning.sepiataskapp.data.model.PetResponse
import com.learning.sepiataskapp.utils.Constants
import com.learning.sepiataskapp.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
        val currentTime = Calendar.getInstance()
        val currentDay = currentTime.get(Calendar.DAY_OF_WEEK)
        if (!workHours.isNullOrBlank()) {
            try {
                val spitWorkHrs = workHours.split(" ")
                if (spitWorkHrs.size == 4) {
                    val workingDays = spitWorkHrs[0]
                    val splitDays = workingDays.split("-")
                    if (splitDays.size == 2) {
                        if (currentDay < Utils.getDay(splitDays[0]) || currentDay > Utils.getDay(splitDays[1]))
                            return false
                    }

                    val startHrsMin = spitWorkHrs[1].split(":")
                    val endHrsMin = spitWorkHrs[3].split(":")

                    val startHrs = startHrsMin[0].toInt()
                    val startMin = startHrsMin[1].toInt()
                    val startTime = Calendar.getInstance()
                    startTime.set(Calendar.HOUR_OF_DAY, startHrs)
                    startTime.set(Calendar.MINUTE, startMin)

                    val endHrs = endHrsMin[0].toInt()
                    val endMin = endHrsMin[1].toInt()
                    val endTime = Calendar.getInstance()
                    endTime.set(Calendar.HOUR_OF_DAY, endHrs)
                    endTime.set(Calendar.MINUTE, endMin)

                    if ((currentTime.after(startTime) && currentTime.before(endTime)) || currentTime.equals(startTime) || currentTime.equals(endTime)) {
                        return true
                    }
                }
            } catch (iob: IndexOutOfBoundsException) {
                iob.printStackTrace()
            } catch (nfe: NumberFormatException) {
                nfe.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return false
    }
}