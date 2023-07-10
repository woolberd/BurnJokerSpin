package bolli.adom.desa.white.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper {

    private var sharedPreferences: SharedPreferences? = null

    fun unit(context: Context) {
        sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    }

    var saveNum: String?
        get() = sharedPreferences?.getString("key", "")
        set(value) = sharedPreferences?.edit()?.putString("key", value)!!.apply()
}