package ru.bey_sviatoslav.android.vk_cup_task_g.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.bey_sviatoslav.android.vk_cup_task_g.MainActivity
import ru.bey_sviatoslav.android.vk_cup_task_g.R
import ru.bey_sviatoslav.android.vk_cup_task_g.models.VKProduct
import ru.bey_sviatoslav.android.vkcuptask1.models.VKGroup

class ProductsAdapter(private val activity: MainActivity, private var products : MutableList<VKProduct>, private var checked : Array<Int?> = arrayOfNulls(products.size)) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.product_item_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductsAdapter.ViewHolder, position: Int) {
        val vkProduct = products.get(position)
        holder.bind(vkProduct, position)
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){

        var productIconImageView: ImageView? = null
        var productTitleTextView: TextView? = null
        var productPriceTextView: TextView? = null
        init {
            productIconImageView = itemView!!.findViewById(R.id.productCoverImageView)
            productTitleTextView = itemView!!.findViewById(R.id.productTitleTextView)
            productPriceTextView = itemView!!.findViewById(R.id.productPriceTextView)
        }
        fun bind(vkProduct: VKProduct, position: Int){
            Glide.with(activity).load(vkProduct.thumbPhoto).into(productIconImageView!!)
            productTitleTextView!!.text = vkProduct.title
            productPriceTextView!!.text = activity.resources.getString(R.string.productPrice, vkProduct.price)
            itemView.setOnClickListener {
                (activity as MainActivity).showProduct_Fragment(vkProduct)
            }
        }
    }
}