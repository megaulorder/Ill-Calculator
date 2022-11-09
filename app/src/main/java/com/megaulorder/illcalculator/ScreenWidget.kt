package com.megaulorder.illcalculator

import android.widget.*
import androidx.core.widget.addTextChangedListener

class ScreenWidget(
	private val baseView: RadioGroup,
	private val numberOneView: EditText,
	private val numberTwoView: EditText,
	private val operatorView: RadioGroup,
	private val calculateView: Button,
	private val resultView: TextView,
) {

	var baseProvider: (() -> Base)? = null
		private set
	var numberOneProvider: (() -> String)? = null
		private set
	var numberTwoProvider: (() -> String)? = null
		private set
	var operatorProvider: (() -> Operator)? = null
		private set

	var resultOnClickListener: (() -> String)? = null

	init {
		baseProvider = {
			Base.getByText(
				baseView.findViewById<RadioButton>(baseView.checkedRadioButtonId).text.toString()
					.lowercase()
			)
		}
		baseView.setOnCheckedChangeListener { group, checkedId ->
			val button: RadioButton = group.findViewById(checkedId)
			baseProvider = { Base.getByText(button.text.toString().lowercase()) }
		}

		numberOneView.addTextChangedListener { numberOneProvider = { it.toString() } }

		numberTwoView.addTextChangedListener { numberTwoProvider = { it.toString() } }

		operatorProvider = {
			Operator.getByText(
				operatorView.findViewById<RadioButton>(operatorView.checkedRadioButtonId).text.toString()
					.lowercase()
			)
		}
		operatorView.setOnCheckedChangeListener { group, checkedId ->
			val button: RadioButton = group.findViewById(checkedId)
			operatorProvider = { Operator.getByText(button.text.toString().lowercase()) }
		}

		calculateView.setOnClickListener { setResult(resultOnClickListener?.invoke()) }
	}

	fun getOperation(): UiOperation = UiOperation(
		baseProvider?.invoke(),
		numberOneProvider?.invoke(),
		numberTwoProvider?.invoke(),
		operatorProvider?.invoke(),
	)

	fun setResult(result: String?) {
		resultView.text = result
	}
}