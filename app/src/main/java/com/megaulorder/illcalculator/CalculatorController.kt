package com.megaulorder.illcalculator

import kotlin.math.absoluteValue

class CalculatorController(
	private val operation: DataOperation,
) {
	fun calculate(): String {
		var numberOne = operation.numberOne
		var numberTwo = operation.numberTwo

		val difference = numberOne.size - numberTwo.size
		if (difference > 0) {
			numberTwo = makeSameLength(difference, numberTwo)
		} else {
			numberOne = makeSameLength(difference, numberOne)
		}

		return ResultMapperController(
			base = operation.base,
			result = when (operation.operator) {
				Operator.AND -> and(numberOne, numberTwo)
				Operator.OR -> or(numberOne, numberTwo)
				Operator.XOR -> xor(numberOne, numberTwo)
			}
		).mapResult()
	}

	private fun and(numberOne: BooleanArray, numberTwo: BooleanArray): BooleanArray {
	}

	private fun or(numberOne: BooleanArray, numberTwo: BooleanArray): BooleanArray {
	}

	private fun xor(numberOne: BooleanArray, numberTwo: BooleanArray): BooleanArray {
	}

	private fun makeSameLength(difference: Int, number: BooleanArray): BooleanArray {
		val result = MutableList(difference.absoluteValue) { false }
		number.forEach { result.add(it) }
		return result.toBooleanArray()
	}
}