package club.anlan.lanlife.lan_app.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import club.anlan.lanlife.lan_app.R;
import club.anlan.lanlife.lan_app.adapter.FileAdapter;
import club.anlan.lanlife.lan_app.domain.File;
import club.anlan.lanlife.lan_app.domain.Page;
import club.anlan.lanlife.lan_app.domain.ResultMessage;
import club.anlan.lanlife.lan_app.http.Callback;
import club.anlan.lanlife.lan_app.http.Http;
import club.anlan.lanlife.lan_app.http.HttpUrl;


public class FileFragment extends BaseFragment {

    private String title;

    private RecyclerView recyclerView;

    public FileFragment() {
    }

    public static FileFragment newInstance(String title) {
        FileFragment fragment = new FileFragment();
        fragment.title = title;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_file, container, false);
        recyclerView = v.findViewById(R.id.recycleView);
        return v;
    }


    @Override
    public void onViewCreated(@Nullable View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getFileList();
            }
        });
    }

    private void getFileList() {
        Http.config(HttpUrl.GET_FILE, new HashMap<>()).getRequest(new Callback() {
            @Override
            public void onSuccess(ResultMessage result) {
                if(result.isSuccess() && result.getData() != null) {
                    Page<File> filePage = JSONObject.parseObject(JSONObject.toJSONString(result.getData()), Page.class);
                    FileAdapter fileAdapter = new FileAdapter(getActivity(), JSONObject.parseArray(JSONObject.toJSONString(filePage.getRecords()), File.class));
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            recyclerView.setAdapter(fileAdapter);
                        }
                    });
                    Log.e("page", JSONObject.toJSONString(filePage));
                }
            }

            @Override
            public void onFailure(Exception e) {
                showToastSync("网络异常！");
            }
        });
    }
}