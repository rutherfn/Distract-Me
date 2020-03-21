package com.nicholasrutherford.distractme.helpers

import android.content.Context
import android.graphics.Typeface
import android.widget.TextView

class Typeface {

    fun setTypefaceForHeaderRegular(textViewType: TextView, mContext: Context) {
        val typeface = Typeface.createFromAsset(mContext.assets, "Poppins-Regular.otf")
        textViewType.typeface = typeface
    }

    fun setTypefaceForHeaderBold(textViewType: TextView, mContext: Context) {
        val typeface = Typeface.createFromAsset(mContext.assets, "Poppins-Bold.otf")
        textViewType.typeface = typeface
    }

    fun setTypefaceForSubHeaderRegular(textViewType: TextView, mContext: Context) {
        val typeface = Typeface.createFromAsset(mContext.assets, "Raleway-Regular.ttf")
        textViewType.typeface = typeface
    }

    fun setTypefaceForSubHeaderBold(textViewType: TextView, mContext: Context) {
        val typeface = Typeface.createFromAsset(mContext.assets, "Raleway-Bold.ttf")
        textViewType.typeface = typeface
    }

    fun setTypefaceForBodyRegular(textViewType: TextView, mContext: Context) {
        val typeface = Typeface.createFromAsset(mContext.assets, "OpenSans-Regular.ttf")
        textViewType.typeface = typeface
    }

    fun setTypefaceForBodyRegularBold(textViewType: TextView, mContext: Context) {
        val typeface = Typeface.createFromAsset(mContext.assets, "OpenSans-Bold.ttf")
        textViewType.typeface = typeface
    }

}