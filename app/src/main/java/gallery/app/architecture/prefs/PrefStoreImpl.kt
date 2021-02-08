package gallery.app.architecture.prefs

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

// FIXME: 2/8/21 put this class to common lib
class PrefStoreImpl @Inject constructor(@ApplicationContext context: Context) : PrefStore {

    private val sharedPrefs = context.getSharedPreferences("tkh_prefs", Context.MODE_PRIVATE)

    override fun getInt(key: String, value: Int): Int = sharedPrefs.getInt(key, value)

    override fun saveInt(key: String, value: Int) {
        sharedPrefs.edit().putInt(key, value).apply()
    }

    override fun getLong(key: String, value: Long): Long = sharedPrefs.getLong(key,value)
    override fun saveLong(key: String, value: Long) {
        sharedPrefs.edit().putLong(key,value).apply()
    }

    override fun <T> readAnyTypeOfObject(key: String, typeToken: TypeToken<T>): T {
        val json: String = getString(key, "{}")
        return Gson().fromJson(json, typeToken.type)
    }

    override fun <T> saveAnyTypeOfObject(key: String, value: T) {
        val json = Gson().toJson(value)
        saveString(key,json)
    }

    override fun <T> saveAnyTypeOfList(key: String, mList: List<T>) {
        val gson = GsonBuilder().create()
        val jsonArray = gson.toJsonTree(mList).asJsonArray
        saveString(key,jsonArray.toString())
    }

    override fun <T> readAnyTypeOfList(key: String, typeToken: TypeToken<List<T>>): List<T> {
        val gson = Gson()
        return gson.fromJson(getString(key, "[]"), typeToken.getType())
    }

    override fun remove(key: String) {
        sharedPrefs.edit().remove(key).apply()
    }

    override fun getBoolean(key: String, def: Boolean): Boolean {
        return sharedPrefs.getBoolean(key, def)
    }

    override fun saveString(key: String, value: String?) {
        sharedPrefs.edit().putString(key, value).apply()
    }

    override fun saveBoolean(key: String, value: Boolean?) {
        sharedPrefs.edit().putBoolean(key, value ?: false).apply()
    }

    override fun getString(key: String, def: String): String {
        return sharedPrefs.getString(key, def) ?: def
    }
}