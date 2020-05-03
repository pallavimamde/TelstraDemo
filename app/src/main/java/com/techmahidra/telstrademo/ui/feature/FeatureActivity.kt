package com.techmahidra.telstrademo.ui.feature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.techmahidra.telstrademo.R
import com.techmahidra.telstrademo.utilties.FragmentTransUtil.addFragment

/* *
* FeatureActivity - Launcher activity
* Load @FeatureFragment
* */
class FeatureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature)
        if (savedInstanceState == null)
            addFragment(FeatureFragment(), R.id.fragment_container) // add fragment
    }

}