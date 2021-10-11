package com.example.bookingapp.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.bookingapp.R
import com.example.bookingapp.databinding.FragmentSigninBinding
import gun0912.tedkeyboardobserver.TedRxKeyboardObserver
import kotlinx.android.synthetic.main.fragment_signin.*

class SignInFragment : Fragment() {

    private lateinit var signInBinding: FragmentSigninBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signInBinding = FragmentSigninBinding.inflate(inflater)
        return signInBinding.root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val translateAnim = AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.vvvv
        )
        container.startAnimation(translateAnim);
        TedRxKeyboardObserver(requireActivity())
            .listen()
            .subscribe({ isShow ->
                if (isShow) {
                    container.translationY = -350f
                    card_view.translationY = -200f
                } else {
                    container.translationY = -200f
                    card_view.translationY = -200f
                }
            }, { throwable -> throwable.printStackTrace() })
    }
}