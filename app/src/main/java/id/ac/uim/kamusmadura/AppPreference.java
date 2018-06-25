package id.ac.uim.kamusmadura;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppPreference {

    Context context;
    SharedPreferences prefs;

    //Preferensi aplikasi
    public AppPreference(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    //Method ini akan dijalankan jika user pertama kali menjalankan aplikasi
    public void setFirstRun(Boolean input) {
        SharedPreferences.Editor editor = prefs.edit();
        String key = context.getResources().getString(R.string.app_first_run);
        editor.putBoolean(key, input);
        editor.commit();
    }

    //Method getFirstRuh tidak akan dijalankan ketika user sudah meng-install aplikasi
    public Boolean getFirstRun() {
        String key = context.getResources().getString(R.string.app_first_run);
        return prefs.getBoolean(key, true);
    }
}
