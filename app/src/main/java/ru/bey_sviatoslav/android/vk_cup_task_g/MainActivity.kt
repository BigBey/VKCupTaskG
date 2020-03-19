package ru.bey_sviatoslav.android.vk_cup_task_g

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope
import com.vk.api.sdk.exceptions.VKApiExecutionException
import com.vk.api.sdk.utils.VKUtils.getCertificateFingerprint
import ru.bey_sviatoslav.android.vk_cup_task_g.fragments.CitiesFragment
import ru.bey_sviatoslav.android.vk_cup_task_g.fragments.ProductFragment
import ru.bey_sviatoslav.android.vk_cup_task_g.fragments.ProductsFragment
import ru.bey_sviatoslav.android.vk_cup_task_g.models.VKCity
import ru.bey_sviatoslav.android.vk_cup_task_g.models.VKProduct
import ru.bey_sviatoslav.android.vk_cup_task_g.requests.VKMarketsRequest
import ru.bey_sviatoslav.android.vk_cup_task_g.requests.VKProductsRequest
import ru.bey_sviatoslav.android.vkcuptask1.models.VKGroup


class MainActivity : AppCompatActivity() {

    companion object{
        val citiesFragmentTAG = "CitiesFragmentTAG"
        val productsFragmentTAG = "ProductsFragmentTAG"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        loginVK()

    }

    private fun loginVK(){
        if(!VK.isLoggedIn())
            VK.login(this, arrayListOf(VKScope.MARKET, VKScope.GROUPS))
        else
            showCitiesFragment(-1)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object: VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                showCitiesFragment(-1)
            }

            override fun onLoginFailed(errorCode: Int) {
                // User didn't pass authorization
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    internal fun showCitiesFragment(cityId : Int){
        supportFragmentManager.apply {
            beginTransaction()
                .replace(R.id.forFragmentLayout, CitiesFragment(cityId), citiesFragmentTAG)
                .commitNowAllowingStateLoss()
        }
    }

    internal fun showProductFragment(groupId : Int, cityId: Int){
        supportFragmentManager.apply {
            beginTransaction()
                .replace(R.id.forFragmentLayout, ProductsFragment(groupId,cityId), productsFragmentTAG)
                .commitNowAllowingStateLoss()
        }
    }

    internal fun showProduct_Fragment(vkProduct: VKProduct){
        supportFragmentManager.apply {
            beginTransaction()
                .replace(R.id.forFragmentLayout, ProductFragment(vkProduct), "product")
                .commitNowAllowingStateLoss()
        }
    }
}
