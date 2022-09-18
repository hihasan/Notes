package com.hihasan.notes.utils.base

import android.app.Activity
import android.app.DownloadManager
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.hihasan.notes.R
import com.hihasan.notes.constant.ApplicationConstants
import com.hihasan.notes.constant.DatabaseConstants
import com.hihasan.notes.utils.DialogUtil
import com.hihasan.notes.utils.LocaleContextWrapper
import com.hihasan.notes.utils.Singleton
import java.io.ByteArrayOutputStream
import java.io.File

open class BaseFragment : Fragment() {
    //base of all fragment class
    var dialogUtil: DialogUtil? = null
    //    var loadingDialogUtil: LoadingDialogUtil? = null
    var BaseContext: Context? = null
    var bitmap: Bitmap? = null
    var database: BaseDatabase? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initialize dialog utils class for getting dialog util object
        dialogUtil = DialogUtil(activity)
//        loadingDialogUtil = LoadingDialogUtil(requireActivity())
        BaseContext = activity

        database = let {
            Room.databaseBuilder(requireActivity().applicationContext, BaseDatabase::class.java, DatabaseConstants.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
        }
    }

    fun hideKeyboard(context: Context, edt: AppCompatEditText) {
        val imm = context?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(edt.windowToken, 0)
    }

    //method for fragment replace getChildFragmentManager()
    protected fun initFragment(fragment: Fragment, id: String, resId: Int) {
        childFragmentManager
            .beginTransaction()
            .add(resId, fragment, id)
            .addToBackStack(null)
            .commit()
    }

    fun replaceFragment(fragment: Fragment?, newid: String?, oldId: String?, resId: Int) {
        Singleton.getInstance().whichFragmentItIs = fragment
        fragmentManager
            ?.beginTransaction()
            ?.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
            ?.replace(resId, fragment!!, newid)
            ?.addToBackStack(oldId)
            ?.commit()
    }
    fun showTurnOnInternetAlartDialog(){
        val alertDialogBuilder: MaterialAlertDialogBuilder = MaterialAlertDialogBuilder(requireContext())
        alertDialogBuilder.setMessage("Please check your internet connection or turn on Online")
        alertDialogBuilder.setCancelable(true)

        alertDialogBuilder.setPositiveButton(
            getString(android.R.string.ok)
        ) { dialog, _ ->
            dialog.cancel()
        }

        val alertDialog: androidx.appcompat.app.AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    //method for network available or not checking
    val isNetworkAvailable: Boolean
        get() {
            val connectivityManager = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
        }

    //method for string concat
    fun concat(A: Array<String?>, B: Array<String?>): Array<String?> {
        val aLen = A.size
        val bLen = B.size
        val C = arrayOfNulls<String>(aLen + bLen)
        System.arraycopy(A, 0, C, 0, aLen)
        System.arraycopy(B, 0, C, aLen, bLen)
        return C
    }

    //method for load data from array
    fun loadArray(arrayName: String, mContext: Context): Array<String?> {
        val prefs = mContext.getSharedPreferences("preferencename", 0)
        val size = prefs.getInt(arrayName + "_size", 0)
        val array = arrayOfNulls<String>(size)
        for (i in 0 until size) array[i] = prefs.getString(arrayName + "_" + i, null)
        return array
    }

    //method for save data in array
    fun saveArray(array: Array<String?>, arrayName: String, mContext: Context): Boolean {
        val prefs = mContext.getSharedPreferences("preferencename", 0)
        val editor = prefs.edit()
        editor.putInt(arrayName + "_size", array.size)
        for (i in array.indices) editor.putString(arrayName + "_" + i, array[i])
        return editor.commit()
    }

    //method for convert bitmap to biye array
    protected fun convertBitmaptoByteArray(relativeLayout: ScrollView): ByteArray {
        bitmap = getBitmapFromView(relativeLayout, relativeLayout.getChildAt(0).height, relativeLayout.getChildAt(0).width)
        val bStream = ByteArrayOutputStream()
        bitmap!!.compress(Bitmap.CompressFormat.PNG, 100, bStream)
        return bStream.toByteArray()
    }

    //method for get bitmap
    private fun getBitmapFromView(view: View, height: Int, width: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) bgDrawable.draw(canvas) else canvas.drawColor(Color.WHITE)
        view.draw(canvas)
        return bitmap
    }

    //method for return screen size
    val screenSIze: IntArray
        get() {
            val displaymetrics = DisplayMetrics()
            activity?.windowManager?.defaultDisplay?.getMetrics(displaymetrics)
            val h = displaymetrics.heightPixels
            val w = displaymetrics.widthPixels
            return intArrayOf(w, h)
        }

    companion object {
        @JvmStatic
        fun showToast(ctx: Context?, msg: String?) {
            Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
        }
        fun errorToast(ctx: Context?) {
            Toast.makeText(ctx, "Something went wrong! Please try again later!", Toast.LENGTH_SHORT).show()
        }
    }

    open fun reloadPage() {}


    open fun showWait(msg: String?, context: Context?) {
        dialogUtil = DialogUtil(context)
        dialogUtil!!.showProgressDialog(msg)
    }

    open  fun hideWait() {
        if (dialogUtil == null) return
        dialogUtil!!.dismissProgress()
    }

    open fun setLocal(activity: Activity, langCode: String){
        val context: Context =
            LocaleContextWrapper.wrap(activity, langCode)
        resources.updateConfiguration(
            context.resources.configuration,
            context.resources.displayMetrics
        )
    }

    protected open fun downloadImage(url: String?): String? {
        var path = ""
        if (url != null) {
            val filename = url.substring(url.lastIndexOf("/") + 1)
            val file =
                File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).path + ApplicationConstants.NOTES + filename)
            path = file.path
            if (file.exists()) {
            } else {
                val request = DownloadManager.Request(Uri.parse(url))
                    .setTitle(filename)
                    .setDescription("Downloading")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                    .setDestinationUri(Uri.fromFile(file))
                    .setAllowedOverMetered(true)
                    .setAllowedOverRoaming(true)
                val downloadManager =
                    activity?.getSystemService(AppCompatActivity.DOWNLOAD_SERVICE) as DownloadManager
                val referenceID = downloadManager.enqueue(request)
            }
        }
        return path
    }

    open fun getPathFromUri(context: Context, uri: Uri): String? {
        val isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).toTypedArray()
                val type = split[0]
                if ("primary".equals(type, ignoreCase = true)) {
                    return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                }

            } else if (isDownloadsDocument(uri)) {
                val id = DocumentsContract.getDocumentId(uri)
                val contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id)
                )
                return getDataColumn(context, contentUri, null, null)
            } else if (isMediaDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).toTypedArray()
                val type = split[0]
                var contentUri: Uri? = null
                if ("image" == type) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if ("video" == type) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if ("audio" == type) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
                val selection = "_id=?"
                val selectionArgs = arrayOf(
                    split[1]
                )
                return getDataColumn(context, contentUri, selection, selectionArgs)
            }
        } else if ("content".equals(uri.scheme, ignoreCase = true)) {

            // Return the remote address
            return if (isGooglePhotosUri(uri)) uri.lastPathSegment else getDataColumn(
                context,
                uri,
                null,
                null
            )
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            return uri.path
        }
        return null
    }

    open fun getDataColumn(
        context: Context, uri: Uri?, selection: String?,
        selectionArgs: Array<String>?
    ): String? {
        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(
            column
        )
        try {
            cursor = context.contentResolver.query(
                uri!!, projection, selection, selectionArgs,
                null
            )
            if (cursor != null && cursor.moveToFirst()) {
                val index: Int = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(index)
            }
        } finally {
            if (cursor != null) cursor.close()
        }
        return null
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    open fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    open fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    open fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    open fun isGooglePhotosUri(uri: Uri): Boolean {
        return "com.google.android.apps.photos.content" == uri.authority
    }

    fun removeSpecialCharacter(dirtyString: String): String {
        val re = Regex("[^A-Za-z0-9 ]")
        return re.replace(dirtyString, "")
    }
}