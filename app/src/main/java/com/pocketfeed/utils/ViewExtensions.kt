package com.pocketfeed.utils

import android.view.View

/**
 * Shows the system software keyboard.
 *
 * @param requestFocus whether to request the focus on the specified [View].
 */
internal fun View.showKeyboard(requestFocus: Boolean = true) {
    if (requestFocus) {
        requestFocus()
    }

    this.context.showKeyboard(this)
}

/**
 * Hides the system software keyboard.
 *
 * @param clearFocus whether to remove the focus from the input field
 */
internal fun View.hideKeyboard(clearFocus: Boolean = true) {
    if (clearFocus) {
        clearFocus()
    }

    this.context.hideKeyboard(this)
}