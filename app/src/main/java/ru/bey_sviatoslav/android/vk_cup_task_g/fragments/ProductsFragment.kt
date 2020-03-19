package ru.bey_sviatoslav.android.vk_cup_task_g.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException
import ru.bey_sviatoslav.android.vk_cup_task_g.MainActivity

import ru.bey_sviatoslav.android.vk_cup_task_g.R
import ru.bey_sviatoslav.android.vk_cup_task_g.adapters.GroupsAdapter
import ru.bey_sviatoslav.android.vk_cup_task_g.adapters.ProductsAdapter
import ru.bey_sviatoslav.android.vk_cup_task_g.models.VKCity
import ru.bey_sviatoslav.android.vk_cup_task_g.models.VKProduct
import ru.bey_sviatoslav.android.vk_cup_task_g.requests.VKCitiesRequest
import ru.bey_sviatoslav.android.vk_cup_task_g.requests.VKProductsRequest

class ProductsFragment(private val groupId: Int, private val cityId : Int) : Fragment() {

    private lateinit var productsRecyclerView: RecyclerView
    private lateinit var productsAdapter: ProductsAdapter

    private lateinit var backImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_products, container, false)
        initViews(view)
        loadProducts(view)
        return view
    }

    private fun initProductsRecyclerView(view: View, vkProducts : ArrayList<VKProduct>){
        productsRecyclerView = view.findViewById<RecyclerView>(R.id.productsRecyclerView)
        productsRecyclerView.setLayoutManager(GridLayoutManager(activity, 2))
        productsAdapter = ProductsAdapter(activity as MainActivity, vkProducts)
        productsRecyclerView.adapter = productsAdapter

        productsRecyclerView.setHasFixedSize(true)
        productsRecyclerView.setItemViewCacheSize(20)
        productsRecyclerView.isDrawingCacheEnabled = true
    }

    private fun loadProducts(view: View){
        VK.execute(VKProductsRequest(groupId), object: VKApiCallback<List<VKProduct>> {
            override fun success(result: List<VKProduct>) {
                initProductsRecyclerView(view, result as ArrayList<VKProduct>)
            }
            override fun fail(error: VKApiExecutionException) {
                val a = 1
            }
        })
    }

    private fun initViews(view: View){
        backImageView = view.findViewById(R.id.backImageView)
        backImageView.setOnClickListener {
            (activity as MainActivity)!!.showCitiesFragment(cityId)
        }
    }
}
