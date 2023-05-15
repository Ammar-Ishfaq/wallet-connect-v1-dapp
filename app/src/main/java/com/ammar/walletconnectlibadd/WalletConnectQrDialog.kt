package com.ammar.walletconnectlibadd

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ammar.walletconnectlibadd.databinding.DialogQrWalletConnectBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder

class WalletConnectQrDialog constructor(
    private val qrCode: String,
    private val positiveClickClosure: () -> Unit,
    private val negativeClickClosure: () -> Unit = {},
) : DialogFragment() {
    private lateinit var binding: DialogQrWalletConnectBinding

    override fun onStart() {
        super.onStart()
        requireDialog().window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireDialog().window?.setBackgroundDrawableResource(R.drawable.bg_round_corner_white)
        requireDialog().window?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#BF303030")))

        isCancelable = true;

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = DialogQrWalletConnectBinding.inflate(inflater, container, false)
        //getting text from input text field.
        val myText: String = qrCode//
        Log.e("######", "QR_CODE => $qrCode")
        //initializing MultiFormatWriter for QR code
        val mWriter = MultiFormatWriter()
        try {
            //BitMatrix class to encode entered text and set Width & Height
            val mMatrix = mWriter.encode(myText, BarcodeFormat.QR_CODE, 400, 400)
            val mEncoder = BarcodeEncoder()
            val mBitmap = mEncoder.createBitmap(mMatrix) //creating bitmap of code
            binding.idIVQrcode.setImageBitmap(mBitmap) //Setting generated QR code to imageView

        } catch (e: WriterException) {
            e.printStackTrace()
        }
        return binding.root
    }
}