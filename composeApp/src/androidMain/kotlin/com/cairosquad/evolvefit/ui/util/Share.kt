@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.cairosquad.evolvefit.ui.util

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.cairosquad.evolvefit.MainActivity
import androidx.core.net.toUri
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.copy_failled
import evolvefit.composeapp.generated.resources.link_copied
import org.jetbrains.compose.resources.StringResource

actual object Share {
    private val context: Context get() = MainActivity.instance

    actual fun shareOnMessenger(url: String, onDismiss: () -> Unit) {
        try {
            val messengerIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, url)
                setPackage("com.facebook.orca")
            }

            if (isAppInstalled("com.facebook.orca")) {
                context.startActivity(messengerIntent)
            } else {
                shareToApp("com.facebook.orca", url, "Messenger")
            }
        } catch (e: Exception) {
        }
        onDismiss()
    }

    actual fun shareOnWhatsApp(url: String, onDismiss: () -> Unit) {
        try {
            if (isAppInstalled("com.whatsapp")) {
                shareToApp("com.whatsapp", url, "WhatsApp")
            } else {
                openWhatsAppWeb(url)
            }
        } catch (e: Exception) {
        }
        onDismiss()
    }

    actual fun shareOnTelegram(url: String, onDismiss: () -> Unit) {
        try {
            if (isAppInstalled("org.telegram.messenger")) {
                shareToApp("org.telegram.messenger", url, "Telegram")
            } else {
                if (isAppInstalled("org.thunderdog.challegram")) {
                    shareToApp("org.thunderdog.challegram", url, "Telegram X")
                } else {
                    openTelegramWeb(url)
                }
            }
        } catch (e: Exception) {
        }
        onDismiss()
    }

    actual fun shareOnInstagram(url: String, onDismiss: () -> Unit) {
        try {
            if (isAppInstalled("com.instagram.android")) {
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, url)
                }
                val chooser = Intent.createChooser(intent, "Share via Instagram")
                context.startActivity(chooser)
            } else {
            }
        } catch (e: Exception) {
        }
        onDismiss()
    }

    actual fun shareOnFacebook(url: String, onDismiss: () -> Unit) {
        try {
            if (isAppInstalled("com.facebook.katana")) {
                shareToApp("com.facebook.katana", url, "Facebook")
            } else {
                openFacebookWeb(url)
            }
        } catch (e: Exception) {

        }
        onDismiss()
    }

    actual fun shareOnX(url: String, onDismiss: () -> Unit) {
        try {
            if (isAppInstalled("com.twitter.android")) {
                shareToApp("com.twitter.android", url, "X (Twitter)")
            } else {
                openXWeb(url)
            }
        } catch (e: Exception) {
        }
        onDismiss()
    }

    actual fun copyLink(workoutUrl: String, onDismiss: (StringResource, Boolean) -> Unit) {
        try {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Series link", workoutUrl)
            clipboard.setPrimaryClip(clip)
            onDismiss(Res.string.link_copied, true)
        } catch (e: Exception) {
            onDismiss(Res.string.copy_failled, false)
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun shareToApp(packageName: String, text: String, appName: String = "") {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
            setPackage(packageName)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        val resolved = context.packageManager.queryIntentActivities(sendIntent, 0)
        if (resolved.isNotEmpty()) {
            context.startActivity(sendIntent)
        } else {
            val chooser = Intent.createChooser(sendIntent, "Share via $appName").apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(chooser)
        }
    }

    private fun isAppInstalled(packageName: String): Boolean {
        return try {
            context.packageManager.getPackageInfo(packageName, 0)
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun openWhatsAppWeb(text: String) {
        try {
            val encodedText = Uri.encode(text)
            val intent = Intent(Intent.ACTION_VIEW, "https://wa.me/?text=$encodedText".toUri()).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            fallbackShare(text, "WhatsApp")
        }
    }

    private fun openTelegramWeb(text: String) {
        try {
            val encodedText = Uri.encode(text)
            val intent = Intent(Intent.ACTION_VIEW,
                "https://t.me/share/url?url=$encodedText".toUri()).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            fallbackShare(text, "Telegram")
        }
    }

    private fun openFacebookWeb(text: String) {
        try {
            val encodedText = Uri.encode(text)
            val intent = Intent(Intent.ACTION_VIEW,
                "https://www.facebook.com/sharer/sharer.php?u=$encodedText".toUri()).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            fallbackShare(text, "Facebook")
        }
    }

    private fun openXWeb(text: String) {
        try {
            val encodedText = Uri.encode(text)
            val intent = Intent(Intent.ACTION_VIEW,
                "https://twitter.com/intent/tweet?text=$encodedText".toUri()).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            fallbackShare(text, "X")
        }
    }

    private fun fallbackShare(text: String, appName: String) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        val chooser = Intent.createChooser(shareIntent, "Share via $appName")
        context.startActivity(chooser)
    }
}