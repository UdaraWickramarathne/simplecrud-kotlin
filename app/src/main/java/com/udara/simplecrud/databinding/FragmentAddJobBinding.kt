package com.udara.simplecrud.databinding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.viewbinding.ViewBinding
import com.google.android.material.button.MaterialButton
import com.udara.simplecrud.R

class FragmentAddJobBinding private constructor(
    private val rootView: LinearLayout
) : ViewBinding {

    override fun getRoot(): LinearLayout = rootView

    val etJobTitle: EditText = rootView.findViewById(R.id.etJobTitle)
    val spinnerEmploymentType: Spinner = rootView.findViewById(R.id.spinnerEmploymentType)
    val etSalary: EditText = rootView.findViewById(R.id.etSalary)
    val etLocation: EditText = rootView.findViewById(R.id.etLocation)
    val btnAddJob: MaterialButton = rootView.findViewById(R.id.btnLogin)

    companion object {
        fun inflate(
            inflater: LayoutInflater,
            parent: ViewGroup?,
            attachToParent: Boolean = false
        ): FragmentAddJobBinding {
            val view = inflater.inflate(R.layout.fragment_add_job, parent, attachToParent)
            return bind(view)
        }

        fun bind(rootView: View): FragmentAddJobBinding {
            return FragmentAddJobBinding(rootView as LinearLayout)
        }
    }
}
