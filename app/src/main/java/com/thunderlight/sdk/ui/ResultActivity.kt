package com.thunderlight.sdk.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thunderlight.sdk.constant.ConstantsStr.POS_DATA
import com.thunderlight.sdk.constant.ConstantsStr.TRANSACTION_DATA
import com.thunderlight.sdk.databinding.ActivityResultBinding
import com.thunderlight.sdk.utils.toString2
import com.thunderlight.thundersmartsdk.data.PosData
import com.thunderlight.thundersmartsdk.data.TransactionData

/**
 * @author Created by M.Moradikia
 * @date  10/31/2022
 * @company Thunder-Light
 */

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
    }

    private fun initData() {
        val transactionData: TransactionData
        val posData: PosData
        intent?.let {
            if (it.hasExtra(TRANSACTION_DATA)) {
                transactionData = it.getParcelableExtra(TRANSACTION_DATA)!!
                loadTransactionData(transactionData)
            }

            if (it.hasExtra(POS_DATA)) {
                posData = it.getParcelableExtra(POS_DATA)!!
                loadPosData(posData)
            }
        }
    }

    private fun loadTransactionData(transactionData: TransactionData) {
        binding.apply {
            var value = transactionData.toString().replace(",", "\n")

            //Extra Data
            if (transactionData.txnType == "CHARGE_PIN" || transactionData.txnType == "BILL" || transactionData.txnType == "CHARGE_TOP_UP") {
                value += transactionData.extraData!!.toString2()
            }

            tvResult.text = value
        }
    }

    private fun loadPosData(posData: PosData) {
        binding.apply {
            tvResult.text = posData.toString().replace(",", "\n")
        }
    }
}