/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.EAC3_1_P1;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.EAC3_1_P1.content.BookUtils;

import java.util.List;

/**
 * An activity representing a list of book titles (items). When one is
 * touched, an intent starts {@link BookDetailActivity} representing
 * book details.
 */
public class MainActivity extends AppCompatActivity {

    private boolean mTwoPane = false;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */

    /**
     * Sets up a book list as a RecyclerView.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        // Set the toolbar as the app bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        // Get the book list as a RecyclerView.
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.book_list);
        recyclerView.setAdapter
                (new SimpleItemRecyclerViewAdapter(BookUtils.book_ITEMS));
        if (findViewById(R.id.book_detail_container) != null) {
            mTwoPane = true;
        }
    }

    /**
     * The ReyclerView for the book list.
     */
    class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter
            <SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<BookUtils.book> mValues;

        SimpleItemRecyclerViewAdapter(List<BookUtils.book> items) {
            mValues = items;
        }

        /**
         * This method inflates the layout for the book list.
         * @param parent ViewGroup into which the new view will be added.
         * @param viewType The view type of the new View.
         * @return
         */
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.book_list_content, parent, false);
            return new ViewHolder(view);
        }

        /**
         * This method implements a listener with setOnClickListener().
         * When the user taps a book title, the code checks if mTwoPane
         * is true, and if so uses a fragment to show the book detail.
         * If mTwoPane is not true, it starts BookDetailActivity
         * using an intent with extra data about which book title was selected.
         *
         * @param holder   ViewHolder
         * @param position Position of the book in the array.
         */
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(String.valueOf(position + 1));
            holder.mContentView.setText(mValues.get(position).book_title);
            holder.mAuthorView.setText(mValues.get(position).book_author);
            holder.miniaturaView.setImageResource(getImageid(mValues.get(position).book_miniature));
           // holder.mContentView.setText(mValues.get(position).book_miniature);
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        int selectedBook = holder.getAdapterPosition();
                        BookDetailsFragment fragment = BookDetailsFragment.newInstance(selectedBook);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.book_detail_container, fragment)
                                .addToBackStack(null)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, BookDetailActivity.class);
                        intent.putExtra(BookUtils.book_ID_KEY, holder.getAdapterPosition());
                        context.startActivity(intent);
                    }
                }
            });
        }

        /**
         * Get the count of book list items.
         * @return
         */
        @Override
        public int getItemCount() {
            return mValues.size();
        }

        /**
         * ViewHolder describes an item view and metadata about its place
         * within the RecyclerView.
         */
        class ViewHolder extends RecyclerView.ViewHolder {
            final View mView;
            final TextView mIdView;
            final TextView mContentView;
            final TextView mAuthorView;
            final ImageView miniaturaView;
            BookUtils.book mItem;

            ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
                mAuthorView=(TextView)view.findViewById(R.id.author) ;
                miniaturaView=(ImageView) view.findViewById(R.id.miniatura);
            }
        }

        public int getImageid(String identifier){
            Resources res= getResources();
            int resourceId = res.getIdentifier(identifier, "drawable", getPackageName() );
            return resourceId;
        }
    }
}
