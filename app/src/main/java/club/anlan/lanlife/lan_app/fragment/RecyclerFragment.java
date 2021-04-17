package club.anlan.lanlife.lan_app.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import club.anlan.lanlife.lan_app.R;
import club.anlan.lanlife.lan_app.adapter.FileAdapter;
import club.anlan.lanlife.lan_app.domain.File;


public class RecyclerFragment extends Fragment {

    private String title;

    public RecyclerFragment() {
    }

    public static RecyclerFragment newInstance(String title) {
        RecyclerFragment fragment = new RecyclerFragment();
        fragment.title = title;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_file, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<File> fileList = new ArrayList<>();
        for(int i=0;i<7;i++) {
            File file = new File();
            file.setName(i+"ddd");
            file.setType(i+"eee");
            fileList.add(file);
        }
        FileAdapter fileAdapter = new FileAdapter(getActivity(), fileList);
        recyclerView.setAdapter(fileAdapter);
        return v;
    }
}