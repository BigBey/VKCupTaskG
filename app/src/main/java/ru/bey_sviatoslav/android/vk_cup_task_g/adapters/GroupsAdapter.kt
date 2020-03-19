package ru.bey_sviatoslav.android.vk_cup_task_g.adapters

import android.graphics.ImageFormat
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.bey_sviatoslav.android.vk_cup_task_g.MainActivity
import ru.bey_sviatoslav.android.vk_cup_task_g.R
import ru.bey_sviatoslav.android.vk_cup_task_g.models.VKCity
import ru.bey_sviatoslav.android.vkcuptask1.models.VKGroup

class GroupsAdapter(private val activity: MainActivity, private var groups : MutableList<VKGroup>, private val cityId : Int = -1, private var checked : Array<Int?> = arrayOfNulls(groups.size)) : RecyclerView.Adapter<GroupsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.group_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = groups.size

    override fun onBindViewHolder(holder: GroupsAdapter.ViewHolder, position: Int) {
        val vkGroup = groups.get(position)
        holder.bind(vkGroup, position)
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){

        var groupIconImageView: ImageView? = null
        var groupTitleTextView: TextView? = null
        var groupIsOpenTextView: TextView? = null
        init {
            groupIconImageView = itemView!!.findViewById(R.id.groupIconImageView)
            groupTitleTextView = itemView!!.findViewById(R.id.groupTitleTextView)
            groupIsOpenTextView = itemView!!.findViewById(R.id.isGroupOpenTextView)
        }
        fun bind(vkGroup: VKGroup, position: Int){
            Glide.with(activity).load(vkGroup.photo).into(groupIconImageView!!)
            groupTitleTextView!!.text = vkGroup.name
            if(vkGroup.isGroupClosed == 0) {
                groupIsOpenTextView!!.setText(R.string.open_group)
            }else{
                groupIsOpenTextView!!.setText(R.string.closed_group)
            }
            itemView.setOnClickListener {
                activity.showProductFragment(vkGroup.id, cityId)
            }
        }
    }
}