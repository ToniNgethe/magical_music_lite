package com.margicalmusic.feature_settings

class NativeLib {

    /**
     * A native method that is implemented by the 'feature_settings' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'feature_settings' library on application startup.
        init {
            System.loadLibrary("feature_settings")
        }
    }
}