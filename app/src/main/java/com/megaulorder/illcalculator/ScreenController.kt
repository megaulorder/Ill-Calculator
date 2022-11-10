package com.megaulorder.illcalculator

class ScreenController(
	private val widget: ScreenWidget,
) {
	private val binaryRegex: Regex = "\\b[01]+\\b".toRegex()
	private val decimalRegex: Regex = "\\b[0123456789]+\\b".toRegex()
	private val hexadecimalRegex: Regex = "\\b[0123456789abcdef]+\\b".toRegex()

	init {
		widget.resultOnClickListener = { validate() }
	}

	private fun validate(): String {
		val operation = UiOperation(
			base = widget.baseProvider?.invoke(),
			numberOne = widget.numberOneProvider?.invoke(),
			numberTwo = widget.numberTwoProvider?.invoke(),
			operator = widget.operatorProvider?.invoke(),
		)

		return if (operation.numberOne == null || operation.numberTwo == null) {
			"Enter two numbers !!"
		} else if (operation.base == Base.BINARY
			&& !(operation.numberOne.matches(binaryRegex) && operation.numberTwo.matches(binaryRegex))
		) {
			"Numbers are not binary"
		} else if (operation.base == Base.DECIMAL
			&& !(operation.numberOne.matches(decimalRegex) && operation.numberTwo.matches(
				decimalRegex
			))
		) {
			"Numbers are not decimal"
		} else if (operation.base == Base.HEXADECIMAL
			&& !(operation.numberOne.matches(hexadecimalRegex) && operation.numberTwo.matches(
				hexadecimalRegex
			))
		) {
			"Numbers are not hexadecimal"
		} else {
			MapperController(operation).map()
		}
	}
}