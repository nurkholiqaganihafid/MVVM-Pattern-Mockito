/*
author: Nurkholiq Agani Hafid
 */

package com.example.mvvmpatternmockito

class MainViewModel(private var modelKubus: ModelKubus) {
    fun getCircumference(): Double = modelKubus.getCircumference()

    fun getSurfaceArea(): Double = modelKubus.getSurfaceArea()

    fun getVolume(): Double = modelKubus.getVolume()

    fun save (l: Double, w:Double, h: Double) {
        modelKubus.save(l, w, h)
    }
}
