package ru.nm17.narodmon.appNarodMonApiClient

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.datetime.Instant
import kotlinx.coroutines.sync.Semaphore
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference

private class PermissionSlip

class RateLimitingSemaphore<T>(
    val availablePermits: AtomicInteger,
    var lastPermitHanded: AtomicInteger,
) {


    suspend fun acquire() {
        TODO("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun release() {
        val current = availablePermits.getAcquire()
        if (current > 0) {
            availablePermits.setRelease(current - 1)
        }
    }

    fun tryAcquire(): Boolean {
        TODO("Not yet implemented")
    }
}