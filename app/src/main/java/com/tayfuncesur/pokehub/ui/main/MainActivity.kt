package com.tayfuncesur.pokehub.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import com.tayfuncesur.pokehub.R
import com.tayfuncesur.pokehub.base.BaseDaggerActivity
import com.tayfuncesur.pokehub.base.Resource
import com.tayfuncesur.pokehub.databinding.ActivityMainBinding
import com.tayfuncesur.pokehub.ui.detail.PokemonDetailFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseDaggerActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainViewModel: MainViewModel

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        val liveData = mainViewModel.pokemonLiveData
        binding.data = liveData
        binding.lifecycleOwner = this

        recycler.layoutManager = GridLayoutManager(this, 2)

        liveData.observe(this, Observer {
            if (it is Resource.Success) {
                val adapter = it.data?.let { it1 ->
                    MainAdapter(it1) { pokemon ->
                        pokemon.id().let { it2 -> PokemonDetailFragment.newInstance(it2) }.let { it3 ->
                            supportFragmentManager.beginTransaction().setCustomAnimations(
                                android.R.anim.fade_in,
                                android.R.anim.fade_out,
                                android.R.anim.fade_in,
                                android.R.anim.fade_out
                            ).add(
                                R.id.mainActivityRoot,
                                it3
                            ).addToBackStack(PokemonDetailFragment::class.java.name).commit()
                        }
                    }
                }
                recycler.adapter = adapter
            }
        })
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}
