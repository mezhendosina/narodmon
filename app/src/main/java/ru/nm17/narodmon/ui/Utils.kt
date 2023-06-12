package ru.nm17.narodmon.ui

fun String.toChipTitle(): String {
    return if (length >= 20) {
        this.slice(0..16) + ".."
    } else {
        this
    }
}