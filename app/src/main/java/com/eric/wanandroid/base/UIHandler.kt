package com.eric.wanandroid.base

import android.os.Handler
import java.lang.ref.WeakReference

/**
 * Created by eric on 20-9-21
 */
open class UIHandler<T> constructor(
    t: T
): Handler() {

    protected var weakReference: WeakReference<T>
    init {
        weakReference = WeakReference(t)
    }

    fun get(): T = weakReference.get()!!
}