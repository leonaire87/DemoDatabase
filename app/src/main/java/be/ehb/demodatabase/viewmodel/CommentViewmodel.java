package be.ehb.demodatabase.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import be.ehb.demodatabase.model.Comment;
import be.ehb.demodatabase.model.CommentDatabase;

public class CommentViewmodel extends AndroidViewModel {

    private final LiveData<List<Comment>> COMMENTS;
    private CommentDatabase database;


    public CommentViewmodel(@NonNull Application application) {
        super(application);
        database = CommentDatabase.getSharedInstance(application);
        COMMENTS = database.getCommentDAO().getAllComments();
    }

    public LiveData<List<Comment>> getCOMMENTS() {
        return COMMENTS;
    }

    public void insertComment(Comment c){
        CommentDatabase.databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                    database.getCommentDAO().insertComment(c);
                }

        });
    }
}

