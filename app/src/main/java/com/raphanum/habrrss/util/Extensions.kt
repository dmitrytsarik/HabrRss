package com.raphanum.habrrss.util

import java.util.regex.Pattern

fun String.removeImgTags() = this.replace(Regex("(<img src=\"(\\S*)\" ?[\\w|=|\"|\\s|\\/]*>)"), "")

fun String.extractImg(): ExtractedImg? {
    val text = this
    val pattern = Pattern.compile("(<img src=\"(\\S*)\" ?[\\w|=|\"|\\s|\\/]*>)")
    val matcher = pattern.matcher(text)
    return if (matcher.find()) {
        var fullMatch: String = matcher.group(1)
        var link: String = matcher.group(2)
        ExtractedImg(link, fullMatch)
    } else {
        null
    }
}

data class ExtractedImg(
    val link: String,
    val fullMatch: String
)