package com.techmahidra.telstrademo.data

class FeatureDB private constructor(){

    var featureDao = DummyFeatureDao()
        private set

    companion object{
        @Volatile private var instance : FeatureDB? = null

        fun getInstance()=
            instance?: synchronized(this){
                instance?: FeatureDB().also { instance=it }
            }
        }
    }
