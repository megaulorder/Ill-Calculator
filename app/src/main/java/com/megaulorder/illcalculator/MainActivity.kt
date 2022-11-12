package com.megaulorder.illcalculator

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val base: Spinner = findViewById(R.id.base)
		ArrayAdapter.createFromResource(
			this,
			R.array.base,
			android.R.layout.simple_spinner_item
		).also { adapter ->
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
			base.adapter = adapter
		}
		base.setSelection(8)

		val widget = ScreenWidget(
			base,
			findViewById(R.id.number_one),
			findViewById(R.id.number_two),
			findViewById(R.id.operator),
			findViewById(R.id.calculate),
			findViewById(R.id.result),
		)

		val controller = ValidationController(widget)
	}
}