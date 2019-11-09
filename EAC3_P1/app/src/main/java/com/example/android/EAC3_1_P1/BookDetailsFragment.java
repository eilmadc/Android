package com.example.android.EAC3_1_P1;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.EAC3_1_P1.content.BookUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookDetailsFragment extends Fragment {

    public BookUtils.book mBook;

    public BookDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(BookUtils.book_ID_KEY)) {
            // Load the content specified by the fragment arguments.
            mBook = BookUtils.book_ITEMS.get(getArguments().getInt(BookUtils.book_ID_KEY));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.book_detail, container, false);

        //  Show the detail information in a TextView.
        if (mBook != null) {
            ((TextView) rootView.findViewById(R.id.book_detail)).setText(mBook.details);
        }
        return rootView;
    }

    public static BookDetailsFragment newInstance (int selectedBook) {
        BookDetailsFragment fragment = new BookDetailsFragment();
        // Set the bundle arguments for the fragment.
        Bundle arguments = new Bundle();
        arguments.putInt(BookUtils.book_ID_KEY, selectedBook);
        fragment.setArguments(arguments);
        return fragment;
    }
}
