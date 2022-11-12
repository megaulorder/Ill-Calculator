package com.megaulorder.illcalculator

import java.math.BigInteger

class ResultMapperController(
	private val base: Base,
	private val result: BooleanArray,
) {
	fun mapResult(): String = mapNumber(result, base)

	private fun mapNumber(number: BooleanArray, base: Base): String =
		BigInteger(toBinaryString(number), 2).toString(base.num)

	private fun toBinaryString(number: BooleanArray): String =
		number.map { if (it) 1 else 0 }.toIntArray().joinToString("")
}