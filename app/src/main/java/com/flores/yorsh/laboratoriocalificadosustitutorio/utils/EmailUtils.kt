package com.flores.yorsh.laboratoriocalificadosustitutorio.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

object EmailUtils {
    fun sendEmail(context: Context, emailAddress: String, subject: String, body: String) {
        try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$emailAddress")
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, body)
            }

            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            } else {
                Toast.makeText(
                    context,
                    "No email app found",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } catch (e: Exception) {
            Toast.makeText(
                context,
                "Error sending email: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}