package com.megaulorder.illcalculator

import java.math.BigInteger

class ResultMapperController(
	private val base: Base,
	private val result: BooleanArray,
) {
	fun mapResult(): String = mapNumber(result, base)

	private fun mapNumber(number: BooleanArray, base: Base): String = when (base) {
		Base.BINARY -> mapToBinary(number)
		Base.DECIMAL -> mapToDecimal(number)
		Base.HEXADECIMAL -> mapToHexadecimal(number)
	}

	private fun mapToDecimal(number: BooleanArray): String =
		BigInteger(toBinaryString(number), 2).toString(10)

	private fun mapToHexadecimal(number: BooleanArray): String =
		BigInteger(toBinaryString(number), 2).toString(16)

	private fun mapToBinary(number: BooleanArray): String =
		toBinaryString(number)

	private fun toBinaryString(number: BooleanArray): String =
		number.map { if (it) 1 else 0 }.toIntArray().joinToString("")
}