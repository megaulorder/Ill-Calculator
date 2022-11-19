package com.megaulorder.illcalculator

class EncoderController(
	private val widget: EncoderWidget,
) {
	init {
		widget.encodeOnClickListener = { encode(widget.getDecodedText()) }
		widget.decodeOnClickListener = { decode(widget.getEncodedText()) }
	}

	private fun encode(text: String): String {
		if (text.isEmpty()) {
			return "nothing to encode"
		}

		val maxBitsPerLetter: Int = (26).toString(2).length

		val result = mutableListOf<String>()
		for (i in text) {
			val upperCaseBit: String = if (i.isUpperCase()) "1" else "0"
			val binaryCode: String = (i.lowercaseChar().code - 'a'.code).toString(2)
			var prefix: String = upperCaseBit
			if (binaryCode.length < (26).toString(2).length) {
				prefix = upperCaseBit + "0".repeat(maxBitsPerLetter - binaryCode.length)
			}
			result.add(prefix + binaryCode)
		}

		return result.joinToString(" ")
	}

	private fun decode(text: String): String {
		if (text.isEmpty()) {
			return "nothing to decode"
		}

		val numbers = text.split(" ")

		val result = mutableListOf<Char>()
		for (i in numbers) {
			val isUpperCase: Boolean = i[0] == '1'
			val decimal: Int = Integer.parseInt(i.substring(1), 2)
			val letter: Char = (decimal + 'a'.code).toChar()
			result.add(if (isUpperCase) letter.uppercaseChar() else letter)
		}

		return result.joinToString("")
	}
}