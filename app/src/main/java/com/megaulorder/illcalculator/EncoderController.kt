package com.megaulorder.illcalculator

import kotlin.math.floor
import kotlin.math.pow

/**
 * мне лееь поэтому оно поддерживает ток англ буквы не различает регистр и не больше 13 символов соре
 */
class EncoderController(
	private val widget: EncoderWidget,
) {
	init {
		widget.encodeOnClickListener = { encode(widget.getDecodedText()) }
		widget.decodeOnClickListener = { decode(widget.getEncodedText()) }
	}

	private fun encode(text: String): String {
		if (text.isEmpty() || text.length > 13) {
			return "max characters: 13"
		}

		val result: Long = text
			.toCharArray()
			.map { (it.code - 'a'.code).toLong() }
			.toLongArray()
			.foldIndexed(0) zhopa@{ index, a, b ->
				return@zhopa (a + b * 26.0
					.pow((text.length - 1 - index))).toLong()
			}

		return result.toString()
	}

	private fun decode(text: String): String {
		if (text.isEmpty() || text.length > 8) {
			return "max characters: 8"
		}

		var nums = text.toLong()

		val chars = mutableListOf<Char>()

		for (i in 4 downTo 0) {
			val n: Long = floor(nums.toDouble() / 26.0.pow(i)).toLong()
			chars.add((n + 'a'.code).toChar())
			nums -= n * 26.0.pow(i).toLong()
		}

		return chars.joinToString("")
	}
}