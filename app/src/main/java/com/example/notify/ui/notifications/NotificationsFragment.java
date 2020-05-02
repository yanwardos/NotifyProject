package com.example.notify.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notify.R;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager recyclerViewManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //SETUP MAIN ACTIVITY
        // ViewModel activity
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        // Ambil View Inflater
        View root = inflater.inflate(R.layout.fragment_notifikasi, container, false);
        // Ambil textView
        final TextView textView = root.findViewById(R.id.text_notifications);


        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText("Okey");
            }

        });
        return root;
    }
}
