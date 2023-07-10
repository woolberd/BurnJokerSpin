package bolli.adom.desa.white.utils

import android.app.Application

class App : Application() {

    companion object {
        val preferenceHelper = PreferenceHelper()
    }

    override fun onCreate() {
        super.onCreate()
        initPref()
    }

    private fun initPref() {
        preferenceHelper.unit(this)
    }
}