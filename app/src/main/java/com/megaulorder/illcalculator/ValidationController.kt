package com.megaulorder.illcalculator

class ValidationController(
	private val widget: ScreenWidget,
) {

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
			"Enter two numbers"
		} else if (
			Base.validate(operation.numberOne, operation.base!!)
			&& Base.validate(operation.numberTwo, operation.base)
		) {
			MapperController(operation).map()
		} else {
			"Numbers are not ${operation.base.text}"
		}
	}
}