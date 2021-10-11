package com.example.bookingapp.ui.base.progress

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class Loading : FrameLayout {
    //UI
    private var pbLoading: ContentLoadingProgressBar? = null

    /**
     * This is the default view constructor. It requires a Context, and holds a reference to it.
     * If not cleaned up properly, memory will leak.
     *
     * @param context The Activity Context
     */
//    constructor(context: Context) : super(context, null, R.attr.alertStyle) {
//        initView()
//    }

    /**
     * This is the default view constructor. It requires a Context, and holds a reference to it.
     * If not cleaned up properly, memory will leak.
     *
     * @param context The Activity Context
     * @param attrs   View Attributes
     */
//    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, R.attr.alertStyle) {
//        initView()
//    }

    /**
     * This is the default view constructor. It requires a Context, and holds a reference to it.
     * If not cleaned up properly, memory will leak.
     *
     * @param context      The Activity Context
     * @param attrs        View Attributes
     * @param defStyleAttr Styles
     */
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
    }

    private fun initView() {
        //inflate(context, R.layout.loadinger_loading_view, this)
        isHapticFeedbackEnabled = true
       // pbLoading = findViewById(R.id.pbLoading)
       // pbLoading.setOnViewDisplayEvent { this.visibility = VISIBLE }
       // pbLoading.setOnViewHideEvent { this@Loading.visibility = GONE }
    }
    /* Clean Up Methods */
    /**
     * Cleans up the currently showing alert view.
     */
    fun hide() {
        pbLoading!!.hide()
    }

    fun show() {
        pbLoading!!.show()
    }
}