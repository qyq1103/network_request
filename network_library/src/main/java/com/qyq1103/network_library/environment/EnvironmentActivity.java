package com.qyq1103.network_library.environment;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.qyq1103.network_library.R;


/**
 * @author：静·灵
 * @date: 2020/1/7 9:39
 * 描述：环境切换
 * 功能: 正式环境和测试环境之间的切换
 * 0：正式环境
 * 1：测试环境
 */
public class EnvironmentActivity extends AppCompatActivity {
    public static final String NETWORK_ENVIRONMENT_PREF_KEY = "network_environment_type";
    private static String sCurrentNetworkEnvironment = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, new MyPreferenceFragment())
                .commit();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        //获取当前环境，默认是正式环境
        sCurrentNetworkEnvironment = preferences.getString(EnvironmentActivity.NETWORK_ENVIRONMENT_PREF_KEY, "0");
    }

    /**
     * 环境切换的界面
     */
    public static class MyPreferenceFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            addPreferencesFromResource(R.xml.environment_prefernce);
            findPreference(NETWORK_ENVIRONMENT_PREF_KEY).setOnPreferenceChangeListener(this);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            //环境切换后提示重启APP
            if (!sCurrentNetworkEnvironment.equals(String.valueOf(newValue))) {
                Toast.makeText(getContext(), "环境已经切换，退出页面时将重启app" + newValue, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String newValue = preferences.getString(EnvironmentActivity.NETWORK_ENVIRONMENT_PREF_KEY, "0");
        if (!sCurrentNetworkEnvironment.equals(newValue)) {
            android.os.Process.killProcess(android.os.Process.myPid());
        } else {
            finish();
        }
    }

    /**
     * 环境判断结果
     *
     * @param application application
     * @return true 正式环境，false测试环境
     */
    public static boolean isOfficialEnvironment(Application application) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(application);
        String environment = preferences.getString(EnvironmentActivity.NETWORK_ENVIRONMENT_PREF_KEY, "0");
        return "0".equalsIgnoreCase(environment);
    }
}