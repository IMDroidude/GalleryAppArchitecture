package gallery.app.architecture.prefs

import com.google.gson.reflect.TypeToken


/**
 * @author Zar E Ahmer
 * @since 1.0
 */

interface PrefStore {

    fun getString(key: String, def: String = ""): String
    fun getBoolean(key: String, def: Boolean = false): Boolean

    fun saveString(key: String, value: String?)
    fun saveBoolean(key: String, value: Boolean?)

    fun getInt(key: String, value: Int = 0): Int
    fun saveInt(key: String, value: Int)

    fun getLong(key: String, value: Long = 0): Long
    fun saveLong(key: String, value: Long)

    fun <T> readAnyTypeOfObject(key: String,typeToken: TypeToken<T>) : T
    fun <T> saveAnyTypeOfObject(key: String,value: T)

    fun <T> saveAnyTypeOfList(key: String, mList: List<T>)///, clazz: Class<T>)
    fun <T> readAnyTypeOfList(key: String,typeToken: TypeToken<List<T>>) : List<T>

    fun remove(key: String)

}