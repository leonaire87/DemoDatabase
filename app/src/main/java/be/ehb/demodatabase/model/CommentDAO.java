package be.ehb.demodatabase.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CommentDAO {

    @Insert
    void insertComment(Comment c);

    @Query("SELECT * FROM Comment")
    LiveData<List<Comment>> getAllComments();
}
