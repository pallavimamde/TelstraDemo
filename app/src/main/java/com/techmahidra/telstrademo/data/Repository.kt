package com.techmahidra.telstrademo.data

class Repository private constructor(private val featureDao : DummyFeatureDao){
    fun setFeatureList(feature : FeaturesRow){
        featureDao.setFeatureList(feature)
    }

    fun getFeatureList() = featureDao.getFeatureList()
    
    companion object{
        @Volatile private var instance : Repository? = null
        fun getInstance(featureDao : DummyFeatureDao)=
            instance?: synchronized(this){
                instance?: Repository(featureDao).also { instance=it }
            }
        }
    }


