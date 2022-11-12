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
	val numberOne: BooleanArray,
	val numberTwo: BooleanArray,
	val operator: Operator,
	val result: Long? = null,
)

enum class Base(val text: String, val num: Int, val regex: Regex) {
	BINARY("binary", 2, "\\b[01]+\\b".toRegex()),
	TERNARY("ternary", 3, "\\b[012]+\\b".toRegex()),
	QUATERNARY("quaternary", 4, "\\b[0123]+\\b".toRegex()),
	QUINARY("quinary", 5, "\\b[01234]+\\b".toRegex()),
	SENARY("senary", 6, "\\b[012345]+\\b".toRegex()),
	SEPTENARY("septenary", 7, "\\b[0123456]+\\b".toRegex()),
	OCTAL("octal", 8, "\\b[01234567]+\\b".toRegex()),
	NONARY("nonary", 9, "\\b[012345678]+\\b".toRegex()),
	DECIMAL("decimal", 10, "\\b[0123456789]+\\b".toRegex()),
	UNDECIMAL("undecimal", 11, "\\b[0123456789a]+\\b".toRegex()),
	DUODECIMAL("duodecimal", 12, "\\b[0123456789ab]+\\b".toRegex()),
	TRIDECIMAL("tridecimal", 13, "\\b[0123456789abc]+\\b".toRegex()),
	TETRADECIMAL("tetradecimal", 14, "\\b[0123456789abcd]+\\b".toRegex()),
	PENTADECIMAL("pentadecimal", 15, "\\b[0123456789abcde]+\\b".toRegex()),
	HEXADECIMAL("hexadecimal", 16, "\\b[0123456789abcdef]+\\b".toRegex());

	companion object {
		fun getByText(title: String): Base = values().find { it.text == title.lowercase() }
			?: throw IllegalArgumentException("No Base for $title")

		fun validate(number: String, base: Base): Boolean = number.matches(base.regex)
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