package com.thunderlight.sdk.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.thunderlight.sdk.constant.ConstantsStr.LISTENER
import com.thunderlight.sdk.constant.ConstantsStr.RRN
import com.thunderlight.sdk.constant.ConstantsStr.TEXT_HINT
import com.thunderlight.sdk.databinding.FragmentInputDialogBinding

/**
 * @author Created by M.Moradikia
 * @date  11/7/2022
 * @company Thunder-Light
 */

class InputDialogFragment : DialogFragment() {

    private val TAG = "InputDialogFragment"
    private lateinit var binding: FragmentInputDialogBinding
    private var rrn: String? = null
    private var textHint: String? = null
    private var listener: InputDialogDataCallBack? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            rrn = it.getString(RRN)
            textHint = it.getString(TEXT_HINT)
            listener = it.getSerializable(LISTENER) as InputDialogDataCallBack
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputDialogBinding.inflate(layoutInflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        binding.apply {
            etInput.hint = textHint
            btnOk.setOnClickListener {

                etInput.error = null
                val firstValue = etInput.text.toString()

                if (firstValue.isNotEmpty()) {
                    etInput.clearFocus()

                    Log.i(TAG, "initView: $firstValue")
                    listener?.getData(firstValue)
                    dismiss()
                } else {
                    etInput.error = "مقدار صحیح وارد نمایید"
                }
            }
        }
    }
}