package be.ehb.demodatabase.UI;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import be.ehb.demodatabase.R;
import be.ehb.demodatabase.UI.util.CommentAdapter;
import be.ehb.demodatabase.model.Comment;
import be.ehb.demodatabase.viewmodel.CommentViewmodel;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private FragmentActivity myContext;
    private View rootView;
    private EditText userET;
    private EditText commentET;
    private Button btnPost;
    private RecyclerView commentsRV;
    private CommentViewmodel model;
    private View.OnClickListener postCommentListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String user = userET.getText().toString();
            String comment = commentET.getText().toString();
            if (user.isEmpty() || comment.isEmpty()){
                return;
            }
            Comment c = new Comment(user,comment);
            model.insertComment(c);
        }
    };


    public HomeFragment() {
        // Required empty public constructor
    }

    public static  HomeFragment newInstance(){
        return new HomeFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myContext = (FragmentActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        model = new ViewModelProvider(myContext).get(CommentViewmodel.class);

        userET = rootView.findViewById(R.id.et_user);
        commentET = rootView.findViewById(R.id.et_comment);
        btnPost = rootView.findViewById(R.id.btn_post);
        commentsRV = rootView.findViewById(R.id.rv_comments);

        CommentAdapter adapter = new CommentAdapter();
        commentsRV.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(myContext,RecyclerView.VERTICAL, false);
        commentsRV.setLayoutManager(layoutManager);

        model.getCOMMENTS().observe(myContext, new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> comments) {
                adapter.addItems(comments);
                adapter.notifyDataSetChanged();
            }
        });


        btnPost.setOnClickListener(postCommentListener);


        return rootView;
    }

}
