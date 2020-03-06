package be.ehb.demodatabase.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(version = 1, entities = {Comment.class}, exportSchema = false)
public abstract class CommentDatabase extends RoomDatabase {

    private static CommentDatabase sharedInstance;

    //Singleton pattern =  één enkele instantie
    public static CommentDatabase getSharedInstance(Context context) {
        if (sharedInstance == null)
            createDatabase(context);

        return sharedInstance;
    }

    private static void createDatabase(Context context) {
        sharedInstance = Room.databaseBuilder(context, CommentDatabase.class, "comment_db").build();
    }
    //verwijst naar alle acties die we willen uitvoeren
    public abstract CommentDAO getCommentDAO();

    //
    public static ExecutorService databaseExecutor = Executors.newFixedThreadPool(4);


}
