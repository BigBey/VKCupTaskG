package ru.bey_sviatoslav.android.vk_cup_task_g.requests

import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject
import ru.bey_sviatoslav.android.vk_cup_task_g.models.VKCity

class VKCitiesRequest: VKRequest<List<VKCity>> {
    constructor() : super("database.getCities") {
        addParam("country_id", 1)
    }

    override fun parse(r: JSONObject): List<VKCity> {
        val response = r.getJSONObject("response")
        val cities = response.getJSONArray("items")
        val result = ArrayList<VKCity>()
        for (i in 0 until cities.length()) {
            result.add(VKCity.parse(cities.getJSONObject(i)))
        }
        return result
    }
}