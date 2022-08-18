package com.margicalmusic.core_utils

class NativeLib {

    /**
     * A native method that is implemented by the 'core_utils' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'core_utils' library on application startup.
        init {
            System.loadLibrary("core_utils")
        }
    }
}