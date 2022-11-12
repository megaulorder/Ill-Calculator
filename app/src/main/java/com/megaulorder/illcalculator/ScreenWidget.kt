package com.megaulorder.illcalculator

import android.view.View
import android.widget.*
import androidx.core.widget.addTextChangedListener

class ScreenWidget(
	private val baseView: Spinner,
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
		baseProvider = { Base.getByText(baseView.selectedItem.toString()) }

		baseView.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
			override fun onItemSelected(
				parent: AdapterView<*>?,
				view: View?,
				position: Int,
				id: Long
			) {
				baseProvider =
					{ Base.getByText(parent?.getItemAtPosition(position).toString()) }
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {
				setResult("Select base !!!")
			}
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

	fun setResult(result: String?) {
		resultView.text = result
	}
}