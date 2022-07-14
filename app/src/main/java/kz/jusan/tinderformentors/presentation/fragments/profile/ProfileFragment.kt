package kz.jusan.tinderformentors.presentation.fragments.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kz.jusan.tinderformentors.R
import kz.jusan.tinderformentors.databinding.FragmentProfileBinding
import kz.jusan.tinderformentors.presentation.auth.login.LoginActivity
import kz.jusan.tinderformentors.presentation.auth.registration.RegistrationDetailsActivity

const val SELECT_PICTURE_CODE = 200

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        createSearchHistoryChips("Math")
        createSearchHistoryChips("Physics")
        createSearchHistoryChips("Informatics")

        binding.ivUserAvatar.setOnClickListener {
            val iGallery = Intent(Intent.ACTION_PICK)
            iGallery.type = "image/*"
            iGallery.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            this.startActivityForResult(iGallery, SELECT_PICTURE_CODE)
        }

        binding.ratingBar.rating = 5f

        binding.ibEditPersonalData.setOnClickListener {
            val intent = Intent(requireActivity(), RegistrationDetailsActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createSearchHistoryChips(chipText: String) {
        val searchHistoryChipGroup = binding.chipGroupSearchHistory
        (layoutInflater.inflate(R.layout.chip_choice, searchHistoryChipGroup, false) as? Chip)?.let { chip ->
            chip.id = View.generateViewId()
            chip.text = chipText
            searchHistoryChipGroup.addView(chip)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE_CODE) {
                binding.ivUserAvatar.setImageURI(data?.data)
            }
        }
    }

}