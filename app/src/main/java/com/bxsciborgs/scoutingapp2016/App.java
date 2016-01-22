package com.bxsciborgs.scoutingapp2016;

/**
 * Created by subin on 1/21/16.
 */
import android.app.Application; import com.parse.Parse;
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize global stuff for Yourney
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "KBqIB66cUvbVxjCLMQw1ug3AiTdldkjoDKlhpGuo", "EmsYKeBWl79WGbAdhtjWUUYCyJuL7iABao5lbzcM");
    }
}
