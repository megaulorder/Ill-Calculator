package com.megaulorder.illcalculator

import java.math.BigInteger

class MapperController(
	private val operation: UiOperation,
) {
	fun map(): String = CalculatorController(
		DataOperation(
			base = operation.base!!,
			numberOne = mapNumber(operation.numberOne!!, operation.base),
			numberTwo = mapNumber(operation.numberTwo!!, operation.base),
			operator = operation.operator!!,
		)
	).calculate()

	private fun mapNumber(number: String, base: Base): BooleanArray = when (base) {
		Base.BINARY -> mapFromBinary(number)
		Base.DECIMAL -> mapFromDecimal(number)
		Base.HEXADECIMAL -> mapFromHexadecimal(number)
	}

	private fun mapFromDecimal(number: String): BooleanArray =
		toBooleanArray(BigInteger(number, 10).toString(2))

	private fun mapFromHexadecimal(number: String): BooleanArray =
		toBooleanArray(BigInteger(number, 16).toString(2))

	private fun mapFromBinary(number: String): BooleanArray =
		toBooleanArray(number)

	private fun toBooleanArray(string: String): BooleanArray {
		return string.toCharArray().map { it == '1' }.toBooleanArray()
	}
}