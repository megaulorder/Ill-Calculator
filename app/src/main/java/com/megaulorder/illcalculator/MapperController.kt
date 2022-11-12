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

	private fun mapNumber(number: String, base: Base): BooleanArray =
		toBooleanArray(BigInteger(number, base.num).toString(2))

	private fun toBooleanArray(string: String): BooleanArray {
		return string.toCharArray().map { it == '1' }.toBooleanArray()
	}
}