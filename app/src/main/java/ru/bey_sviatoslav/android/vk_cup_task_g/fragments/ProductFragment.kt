package ru.bey_sviatoslav.android.vk_cup_task_g.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_product.*
import org.w3c.dom.Text

import ru.bey_sviatoslav.android.vk_cup_task_g.R
import ru.bey_sviatoslav.android.vk_cup_task_g.models.VKProduct

class ProductFragment(private val vkProduct: VKProduct, private val groupId : Int = -1, private val cityId : Int = -1) : Fragment() {

    private lateinit var productCover : ImageView
    private lateinit var productTiitle : TextView
    private lateinit var productPrice : TextView
    private lateinit var productDescription : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product, container, false)
        productCover = view.findViewById(R.id.productCover)
        productTiitle = view.findViewById(R.id.productTitle)
        productPrice = view.findViewById(R.id.productPrice)
        productDescription = view.findViewById(R.id.productDescription)

        Glide.with(view).load(vkProduct.thumbPhoto).into(productCover)
        productTiitle.text = vkProduct.title
        productPrice.text = activity!!.resources.getString(R.string.productPrice, vkProduct.price)
        productDescription.text = vkProduct.description
        return view
    }

}
