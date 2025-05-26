package edu.regis.soconnor005.starwarsdatabank.data

import java.io.Serializable

data class Entry(val category: String, val name: String, val description: String) : Serializable
