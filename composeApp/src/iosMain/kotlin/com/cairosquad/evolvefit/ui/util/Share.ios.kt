@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.cairosquad.evolvefit.ui.util

import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.copy_failled
import evolvefit.composeapp.generated.resources.link_copied
import kotlinx.cinterop.BetaInteropApi
import org.jetbrains.compose.resources.StringResource
import platform.Foundation.NSCharacterSet
import platform.Foundation.NSString
import platform.Foundation.create
import platform.Foundation.stringByAddingPercentEncodingWithAllowedCharacters
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication
import platform.UIKit.UIPasteboard
import platform.UIKit.UIWindow

actual object Share {

    actual fun shareOnMessenger(url: String, onDismiss: () -> Unit) {
        shareText(url, onDismiss)
    }

    actual fun shareOnWhatsApp(url: String, onDismiss: () -> Unit) {
        shareText(url, onDismiss)
    }

    actual fun shareOnTelegram(url: String, onDismiss: () -> Unit) {
        shareText(url, onDismiss)
    }

    actual fun shareOnInstagram(url: String, onDismiss: () -> Unit) {
        shareText(url, onDismiss)
    }

    actual fun shareOnFacebook(url: String, onDismiss: () -> Unit) {
        shareText(url, onDismiss)
    }

    actual fun shareOnX(url: String, onDismiss: () -> Unit) {
        shareText(url, onDismiss)
    }

    actual fun copyLink(workoutUrl: String, onDismiss: (StringResource, Boolean) -> Unit) {
        try {
            UIPasteboard.generalPasteboard().string = workoutUrl
            onDismiss(Res.string.link_copied, true)
        } catch (e: Throwable) {
            onDismiss(Res.string.copy_failled, false)
        }
    }

    private fun shareText(text: String, onDismiss: () -> Unit) {
        val items = listOf(text)
        val activityController = UIActivityViewController(items, null)

        val rootController = (UIApplication.sharedApplication.windows.toList().firstOrNull() as? UIWindow)
            ?.rootViewController
            ?: return
        activityController.setCompletionWithItemsHandler { _, _, _, _ ->
            onDismiss()
        }

        rootController.presentViewController(activityController, animated = true, completion = null)
    }

    @OptIn(BetaInteropApi::class)
    private fun String.encodeForURL(): String {
        return NSString.create(string = this)
            .stringByAddingPercentEncodingWithAllowedCharacters(
                NSCharacterSet.characterSetWithCharactersInString("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_.~")
            ) ?: this
    }
}
