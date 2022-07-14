package kz.jusan.tinderformentors.presentation.fragments.requests.chat

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kz.jusan.tinderformentors.R

class ReportDialog : DialogFragment(R.layout.dialog_report) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(view) {
            findViewById<Button>(R.id.btnCancelDeletion).setOnClickListener {
                dismiss()
            }
            findViewById<Button>(R.id.btnConfirmDeletion).setOnClickListener {
                dismiss()

                val viewMainActivity = requireActivity().findViewById<View>(android.R.id.content)
                val bottomBar = viewMainActivity.findViewById<BottomNavigationView>(R.id.nav_view)

                val userReportedSnackbar = Snackbar.make(
                    viewMainActivity.findViewById(R.id.container),
                    "Жалоба отправлена",
                    Snackbar.LENGTH_SHORT
                )
                userReportedSnackbar.anchorView = bottomBar
                userReportedSnackbar.show()
            }
        }
    }

}