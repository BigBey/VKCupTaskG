package ru.bey_sviatoslav.android.vk_cup_task_g.requests

import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject
import ru.bey_sviatoslav.android.vk_cup_task_g.models.VKCity
import ru.bey_sviatoslav.android.vkcuptask1.models.VKGroup

class VKMarketsRequest: VKRequest<List<VKGroup>> {
    constructor(cityId : Int) : super("groups.search") {
        addParam("q", " ")
        addParam("country_id", 1)
        addParam("city_id", cityId)
        addParam("market", 1)
    }

    override fun parse(r: JSONObject): List<VKGroup> {
        val response = r.getJSONObject("response")
        val groups = response.getJSONArray("items")
        val result = ArrayList<VKGroup>()
        for (i in 0 until groups.length()) {
            result.add(VKGroup.parse(groups.getJSONObject(i)))
        }
        return result
    }
}