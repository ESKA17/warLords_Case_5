package kz.jusan.tinderformentors.presentation.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kz.jusan.tinderformentors.R
import kz.jusan.tinderformentors.presentation.models.Post
import kz.jusan.tinderformentors.util.Constants

@AndroidEntryPoint
class AddPostBottomSheet : BottomSheetDialogFragment() {

    private val newsViewModel: NewsViewModel by viewModels({ requireParentFragment() })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.dialog_bottom_sheet_add_post,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etMessage = view.findViewById<EditText>(R.id.etMessage)
        val ibSendPost = view.findViewById<ImageButton>(R.id.ibSendPost)
        val ibCloseAddPostDialog = view.findViewById<ImageButton>(R.id.ibCloseAddPostDialog)

        ibCloseAddPostDialog.setOnClickListener {
            dismiss()
        }

        ibSendPost.setOnClickListener {
            val post = Post(
                id = newsViewModel.getPostsCount() + 1,
                avatar = R.drawable.ic_profile,
                authorName = "Batyrkhan Tokmyrza",
                university = "Nazarbayev University",
                message = etMessage.text.toString()
            )
            newsViewModel.addPost(post)
            dismiss()
        }

    }

}