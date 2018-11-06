package com.raphanum.habrrss.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import org.simpleframework.xml.Text

@Root(strict = false)
data class RssContainer @JvmOverloads constructor(
    @field:Element var channel: Channel? = null
)

@Root(strict = false)
data class Channel @JvmOverloads constructor(
    @field:ElementList(inline = true) var items: List<HabrItem>? = null
)

@Root(strict = false, name = "item")
data class HabrItem @JvmOverloads constructor(
    @field:Element() var title: String? = null,
    @field:Element() var description: String? = null,
    @field:Element(name = "pubDate") var date: String? = null,
    @field:Element(name = "creator") var author: String? = null,
    @field:ElementList(inline = true, required = false) var categories: List<Category>? = null
)

@Root(strict = false, name = "category")
data class Category @JvmOverloads constructor(
    @field:Text var text: String? = null
)

