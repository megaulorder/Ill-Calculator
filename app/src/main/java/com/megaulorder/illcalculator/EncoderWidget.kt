package com.megaulorder.illcalculator

import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class EncoderWidget(
	private val decodedTextView: EditText,
	private val encodedTextView: EditText,
	encodeButton: Button,
	decodeButton: Button,
) {

	var encodeOnClickListener: (() -> String)? = null
	var decodeOnClickListener: (() -> String)? = null

	init {
		encodeButton.setOnClickListener { setEncodedText(encodeOnClickListener?.invoke()) }
		decodeButton.setOnClickListener { setDecodedText(decodeOnClickListener?.invoke()) }
	}

	fun getDecodedText(): String = decodedTextView.text.toString()

	fun getEncodedText(): String = encodedTextView.text.toString()

	private fun setDecodedText(text: String?) {
		decodedTextView.setText(text, TextView.BufferType.EDITABLE)
	}

	private fun setEncodedText(text: String?) {
		encodedTextView.setText(text, TextView.BufferType.EDITABLE)
	}
}