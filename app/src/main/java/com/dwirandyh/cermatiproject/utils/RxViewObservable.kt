package com.dwirandyh.cermatiproject.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class RxViewObservable {
    companion object {

        fun fromTextView(textView: TextView): Observable<String> {
            val subject = PublishSubject.create<String>()

            textView.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    subject.onNext(charSequence.toString())
                }
            })
            return subject
        }
    }
}