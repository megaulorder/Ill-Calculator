package com.megaulorder.illcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val widget = ScreenWidget(
			findViewById(R.id.base),
			findViewById(R.id.number_one),
			findViewById(R.id.number_two),
			findViewById(R.id.operator),
			findViewById(R.id.calculate),
			findViewById(R.id.result),
		)

		val controller = ScreenController(widget)
	}
}