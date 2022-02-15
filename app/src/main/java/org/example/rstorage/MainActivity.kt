package org.example.rstorage

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        writeToDownloads()
    }

    private fun writeToDownloads() {
        val sub = "Prompts"
        try {
            val dir: File =
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
                    File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), sub)
                else
                    File(Environment.getExternalStorageDirectory(), sub)
            if(!dir.exists()) {
                val success = dir.mkdirs()
                if(!success)
                    throw Exception("Directory not created")
            }
            val target = File(dir, "file.txt")
            val out = PrintWriter(FileWriter(target))
            out.println("hello, world!")
            out.flush()
            out.close()
        } catch (e: Exception) {
            Log.e("DownloadTest", "Exception writing to Downloads/", e)
        }
    }
}