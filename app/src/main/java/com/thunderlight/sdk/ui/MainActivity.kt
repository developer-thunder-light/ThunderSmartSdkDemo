package com.thunderlight.sdk.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.thunderlight.sdk.R
import com.thunderlight.sdk.constant.ConstantsStr
import com.thunderlight.sdk.constant.ConstantsStr.LISTENER
import com.thunderlight.sdk.constant.ConstantsStr.POS_DATA
import com.thunderlight.sdk.constant.ConstantsStr.TEXT_HINT
import com.thunderlight.sdk.constant.ConstantsStr.TRANSACTION_DATA
import com.thunderlight.sdk.databinding.ActivityMainBinding
import com.thunderlight.sdk.ui.service.PaymentServicesAdapter
import com.thunderlight.sdk.ui.service.PaymentServicesViewModel
import com.thunderlight.sdk.ui.service.model.PaymentServiceItem
import com.thunderlight.thundersmartsdk.constant.HostApp
import com.thunderlight.thundersmartsdk.constant.RequestType
import com.thunderlight.thundersmartsdk.constant.TxnInquiryType
import com.thunderlight.thundersmartsdk.data.PosData
import com.thunderlight.thundersmartsdk.data.TransactionData
import com.thunderlight.thundersmartsdk.generalManager.GeneralSDKManager
import com.thunderlight.thundersmartsdk.generalManager.GeneralSDKManagerInterface
import com.thunderlight.thundersmartsdk.generalManager.PosDataCallBack
import com.thunderlight.thundersmartsdk.generalManager.ResultCallBack
import com.thunderlight.thundersmartsdk.generalManager.TransactionCallBack

/**
 * @author Created by M.Moradikia
 * @date  10/29/2022
 * @company Thunder-Light
 */

open class MainActivity : AppCompatActivity() {

    private val TAG = "SDK MainActivity"
    private lateinit var binding: ActivityMainBinding
    private val viewModel: PaymentServicesViewModel by viewModels()
    private val serviceAdapter by lazy { PaymentServicesAdapter() }

    lateinit var transactionCallBack: TransactionCallBack
    lateinit var resultCallBack: ResultCallBack
    lateinit var sdkManager: GeneralSDKManagerInterface
    lateinit var host: HostApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCallBacks()
        initSdk()
        initViewModel()
        initView()
    }

    private fun initSdk() {
        sdkManager = GeneralSDKManager()
        host = sdkManager.init(this@MainActivity)
    }

    private fun initViewModel() {
        viewModel.menuList.observe(this@MainActivity) {
            if (it.isNotEmpty())
                initRecyclerView(it)
        }
        viewModel.getMenuItems()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        binding.topLogo.tvHostName.text = " Host App: Smart ${host.value.substring(0, 1).uppercase() + host.value.substring(1)}"
    }

    @SuppressLint("SetTextI18n")
    private fun initRecyclerView(appList: ArrayList<PaymentServiceItem>) {
        binding.apply {
            recyclerView.apply {
                serviceAdapter.setData(appList)
                layoutManager = GridLayoutManager(context, 2)
                adapter = serviceAdapter

                serviceAdapter.setOnItemClickListener { order, txnType ->
                    if (order == ConstantsStr.ACTION_OPEN) {
                        when (txnType) {
                            RequestType.REQUEST_TYPE_BALANCE -> {
                                sdkManager.inquiryBalance(transactionCallBack)
                            }

                            RequestType.REQUEST_TYPE_SALE -> {
                                doSaleSample()
                            }

                            RequestType.REQUEST_TYPE_DO_APPROVE -> {
                                doApproveSample()
                            }

                            RequestType.REQUEST_TYPE_DO_REVERSE -> {
                                doReverseSample()
                            }

                            RequestType.REQUEST_TYPE_BILL -> {
                                payBillSample()
                            }

                            RequestType.REQUEST_TYPE_CHARGE -> {
                                buyChargeSample()
                            }

                            RequestType.REQUEST_TYPE_INQUIRY_TRANSACTION -> {
                                inquiryTransactionSample()
                            }

                            RequestType.REQUEST_TYPE_DO_KEY_CHANGE -> {
                                sdkManager.doConfiguration(resultCallBack)
                            }

                            RequestType.REQUEST_TYPE_INQUIRY_POS_DATA -> {
                                inquiryPosDataSample()
                            }

                            RequestType.REQUEST_TYPE_PRINT_BITMAP -> {
                                doPrintSample()
                            }

                            else -> {
                            }
                        }
                    }
                }
            }
        }
    }

    private fun initCallBacks() {
        // Result Call Back
        resultCallBack = object : ResultCallBack {
            override fun onSuccess() {
                Log.i(TAG, "CallBack onSuccess ")
                Toast.makeText(this@MainActivity, "--------- Result Success ---------", Toast.LENGTH_LONG).show()
            }

            override fun onError(errorCode: String, errorMsg: String) {
                Log.i(TAG, "CallBack onError: $errorCode :  $errorMsg ")
                Toast.makeText(this@MainActivity, "error: $errorCode : $errorMsg ", Toast.LENGTH_LONG).show()
            }
        }

        transactionCallBack = object : TransactionCallBack {
            override fun onSuccess(transactionData: TransactionData) {
                Log.i(TAG, "transactionCallBack onReceive: $transactionData") // toString() of transactionData
                val intent = Intent(this@MainActivity, ResultActivity::class.java)
                intent.putExtra(TRANSACTION_DATA, transactionData)
                startActivity(intent)
            }

            override fun onUndeterminedStateOfPreviousTxn(transactionData: TransactionData) {
                // در صورت فراخوانی این متد، 3RD party  اجازه شروع تراکنش جدید را ندارد، تا زمانی که تراکنش بازگستی را تعیین وضعیت کند

                Log.i(
                    TAG,
                    "onUndeterminedStateOfPreviousTxn: traceNo: ${transactionData.trace},rrn: ${transactionData.rrn}, respCode: ${transactionData.responseCode}"
                )

                val resultCallBack = object : ResultCallBack {
                    override fun onSuccess() {
                        Log.i(TAG, "onSuccess: ")
                    }

                    override fun onError(errorCode: String, errorMsg: String) {
                        Log.i(TAG, "onError: $errorCode: $errorMsg")
                    }
                }

                val theProductWasPresented = true

                //اگر 3RD party موفق به ارائه محصول شده است جهت نهایی سازی ارسال تاییدیه میکند
                if (theProductWasPresented)
                    sdkManager.doApprove220(transactionData.rrn, resultCallBack)

                //اگر 3RD party موفق به ارائه محصول نشد جهت نهایی سازی ارسال اصلاحیه میکند
                if (!theProductWasPresented)
                    sdkManager.doReverse420(transactionData.trace, resultCallBack)

                Log.i(TAG, "transactionCallBack onUndeterminedStateOfPreviousTxn: $transactionData") // toString() of transactionData
                val intent = Intent(this@MainActivity, ResultActivity::class.java)
                intent.putExtra(TRANSACTION_DATA, transactionData)
                startActivity(intent)
            }

            override fun onError(errorCode: String, errorMsg: String) {
                Log.i(TAG, "transactionCallBack onError: $errorCode :  $errorMsg ")
                Toast.makeText(this@MainActivity, "error: $errorCode : $errorMsg ", Toast.LENGTH_LONG).show()
            }

            override fun onCanceled() {
                Log.i(TAG, "transactionCallBack onCanceled: ")
                Toast.makeText(this@MainActivity, "Transaction Canceled", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun doSaleSample() {
        val reserveNumber = "0123456879"//شناسه پرداخت

        val listener = object : InputDialogDataCallBack {
            override fun getData(amount: String) {
                sdkManager.doSaleTransaction(amount, reserveNumber, false, transactionCallBack)
            }

            override fun onCancel() {}
        }

        val dialog = InputDialogFragment()
        val extraData = Bundle()
        extraData.putSerializable(LISTENER, listener)
        extraData.putString(TEXT_HINT, "مبلغ")
        dialog.arguments = extraData
        dialog.show(supportFragmentManager, "InputDialogFragment")
    }

    private fun doApproveSample() {
        val listener = object : InputDialogDataCallBack {
            override fun getData(rrn: String) {
                sdkManager.doApprove220(rrn, resultCallBack)
            }

            override fun onCancel() {}
        }

        val dialog = InputDialogFragment()
        val extraData = Bundle()
        extraData.putSerializable(LISTENER, listener)
        extraData.putString(TEXT_HINT, "شماره مرجع")
        dialog.arguments = extraData
        dialog.show(supportFragmentManager, InputDialogFragment().tag)
    }

    private fun doReverseSample() {
        val listener = object : InputDialogDataCallBack {
            override fun getData(trace: String) {
                sdkManager.doReverse420(trace, resultCallBack)
            }

            override fun onCancel() {}
        }

        val dialog = InputDialogFragment()
        val extraData = Bundle()
        extraData.putSerializable(LISTENER, listener)
        extraData.putString(TEXT_HINT, "شماره پیگیری")
        dialog.arguments = extraData
        dialog.show(supportFragmentManager, InputDialogFragment().tag)
    }

    private fun buyChargeSample() {
        sdkManager.doServiceTransaction(RequestType.REQUEST_TYPE_CHARGE, false, transactionCallBack)
    }

    private fun payBillSample() {
        sdkManager.doServiceTransaction(RequestType.REQUEST_TYPE_BILL, false, transactionCallBack)
    }

    private fun inquiryTransactionSample() {
        val listener = object : InputDialogDataCallBack {
            override fun getData(rrn: String) {
                // TxnInquiryType به صورت enum تعریف شده است، که براساس نیاز میتوانید مقدار آنرا تغییر دهید
                val inquiryType = TxnInquiryType.REQUEST_TYPE_INQUIRY_BY_RRN

                sdkManager.inquiryTransactionData(inquiryType, rrn, true, transactionCallBack) // 1

                //sdkManager.inquiryTransactionData( inquiryType, trace, true, transactionCallBack) // 2

                //sdkManager.inquiryTransactionData( inquiryType, reserveNumber, true, transactionCallBack) // 3
            }

            override fun onCancel() {}
        }

        val dialog = InputDialogFragment()
        val extraData = Bundle()
        extraData.putSerializable(LISTENER, listener)
        extraData.putString(TEXT_HINT, "شماره مرجع")
        dialog.arguments = extraData
        dialog.show(supportFragmentManager, InputDialogFragment().tag)
    }

    private fun inquiryPosDataSample() {
        val posDataCallBack = object : PosDataCallBack {
            override fun onReceive(posData: PosData) {
                Log.i(TAG, "posDataCallBack onReceive: $posData")
                val intent = Intent(this@MainActivity, ResultActivity::class.java)
                intent.putExtra(POS_DATA, posData)
                startActivity(intent)
            }

            override fun onError(errorCode: String, errorMsg: String) {
                Log.i(TAG, "posDataCallBack onError: $errorCode :  $errorMsg ")
                Toast.makeText(this@MainActivity, "error: $errorCode : $errorMsg ", Toast.LENGTH_LONG).show()
            }
        }

        sdkManager.inquiryPosData(posDataCallBack)
    }

    private fun doPrintSample() {
        // Attention: width of bitmap must be 384 px
        val options = BitmapFactory.Options()
        options.inScaled = false
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.img_384, options)

        sdkManager.printBitmap(bitmap, resultCallBack)
    }
}