package ru.android.test.task.rssreader.modules.common;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import ru.android.test.task.rssreader.R;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected Fragment fragment;

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final FragmentManager fragmentManager = getSupportFragmentManager();

        fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (null == fragment) {
            fragment = createFragment();
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
