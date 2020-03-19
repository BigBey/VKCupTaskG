package ru.bey_sviatoslav.android.vk_cup_task_g.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException
import ru.bey_sviatoslav.android.vk_cup_task_g.MainActivity
import ru.bey_sviatoslav.android.vk_cup_task_g.R
import ru.bey_sviatoslav.android.vk_cup_task_g.fragments.CitiesFragment
import ru.bey_sviatoslav.android.vk_cup_task_g.models.VKCity
import ru.bey_sviatoslav.android.vk_cup_task_g.requests.VKCitiesRequest
import ru.bey_sviatoslav.android.vk_cup_task_g.requests.VKMarketsRequest
import ru.bey_sviatoslav.android.vkcuptask1.models.VKGroup

class CitiesAdapter(private val activity: MainActivity, private var cities : MutableList<VKCity>, private var checked : Array<Int?> = arrayOfNulls(cities.size)) : RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.city_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = cities.size

    override fun onBindViewHolder(holder: CitiesAdapter.ViewHolder, position: Int) {
        val vkCity = cities.get(position)
        holder.bind(vkCity, position)
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){

        var cityTitle: TextView? = null
        init {
            cityTitle = itemView!!.findViewById(R.id.cityTextView)
        }
        fun bind(vkCity: VKCity, position: Int){
           cityTitle!!.text = vkCity.title
            itemView.setOnClickListener {
                activity.supportFragmentManager.apply {
                    val citiesFragment = findFragmentByTag(MainActivity.citiesFragmentTAG) as CitiesFragment
                    VK.execute(VKMarketsRequest(vkCity.id), object: VKApiCallback<List<VKGroup>> {
                        override fun success(result: List<VKGroup>) {
                            citiesFragment.setCityId(vkCity.id)
                            citiesFragment.initGroupsRecyclerView(result as ArrayList<VKGroup>)
                            citiesFragment.hideBottomSheetDialog()
                        }
                        override fun fail(error: VKApiExecutionException) {
                            val a = 1
                        }
                    })
                }
            }
        }
    }
}