package com.cairosquad.evolvefit.logger
import platform.Foundation.NSLog

actual fun debugLog(tag: String, message: String) {
    NSLog("$tag: $message")
}