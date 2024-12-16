package com.example.dokizone.ui.application

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.util.DebugLogger
import dagger.hilt.android.HiltAndroidApp
import okio.Path.Companion.toPath

@HiltAndroidApp
class DokiZoneApplication : Application(), SingletonImageLoader.Factory {
    override fun newImageLoader(context: PlatformContext): ImageLoader {
        return ImageLoader
            .Builder(context = context)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache
                    .Builder()
                    .maxSizePercent(
                        context = context,
                        percent = 0.1
                    )
                    .strongReferencesEnabled(true)
                    .build()
            }
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache
                    .Builder()
                    .maxSizeBytes(
                        size = (50 * 1024 * 1024).toLong()
                    )
                    .directory(cacheDir.path.toPath())
                    .build()
            }
            .logger(DebugLogger())
            .build()
    }
}