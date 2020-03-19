package ru.bey_sviatoslav.android.vk_cup_task_g.requests

import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject
import ru.bey_sviatoslav.android.vk_cup_task_g.models.VKCity
import ru.bey_sviatoslav.android.vk_cup_task_g.models.VKProduct

class VKProductsRequest: VKRequest<List<VKProduct>> {
    constructor(ownerId : Int) : super("market.get") {
        addParam("owner_id", -ownerId)
        addParam("count", 200)
    }

    override fun parse(r: JSONObject): List<VKProduct> {
        val response = r.getJSONObject("response")
        val products = response.getJSONArray("items")
        val result = ArrayList<VKProduct>()
        for (i in 0 until products.length()) {
            result.add(VKProduct.parse(products.getJSONObject(i)))
        }
        return result
    }
}