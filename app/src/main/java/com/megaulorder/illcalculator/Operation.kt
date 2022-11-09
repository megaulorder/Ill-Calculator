package com.megaulorder.illcalculator

data class UiOperation(
	val base: Base?,
	val numberOne: String?,
	val numberTwo: String?,
	val operator: Operator?,
	val result: String? = null,
)

data class DataOperation(
	val base: Base,
	val numberOne: Array<Boolean>,
	val numberTwo: Array<Boolean>,
	val operator: Operator,
	val result: Long? = null,
)

enum class Base(val text: String) {
	DECIMAL("decimal"),
	BINARY("binary"),
	HEXADECIMAL("hexadecimal");

	companion object {
		fun getByText(title: String): Base = values().find { it.text == title }
			?: throw IllegalArgumentException("No Base for $title")
	}
}

enum class Operator(val text: String) {
	AND("and"),
	OR("or"),
	XOR("xor");

	companion object {
		fun getByText(title: String): Operator = values().find { it.text == title }
			?: throw IllegalArgumentException("No Operator for $title")
	}
}