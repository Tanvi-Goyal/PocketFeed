package com.pocketfeed.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Shows the software keyboard (if possible).
 */
fun Context.showKeyboard(view: View) {
    (this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?)?.showSoftInput(
        view,
        0
    )
}


/**
 * Hides the software keyboard (if possible)
 */
fun Context.hideKeyboard(view: View?) {
    if (view != null)
        (this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?)?.hideSoftInputFromWindow(
            view.windowToken,
            0
        )
}