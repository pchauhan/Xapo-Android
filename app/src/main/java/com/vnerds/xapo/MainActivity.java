package com.vnerds.xapo;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vnerds.xapo.adapters.MyAdapter;
import com.vnerds.xapo.api.RestClient;
import com.vnerds.xapo.app.XapoApp;
import com.vnerds.xapo.objects.Git_Object;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Git_Object.ItemsBean> mDataList;
    private Dialog alertDialog;
    private View child;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mDataList = new ArrayList<>();

        mAdapter = new MyAdapter(mDataList);
        mRecyclerView.setAdapter(mAdapter);

        initDialog();
        callGetTopRepository();

    }
    public void initDialog(){
        child = getLayoutInflater().inflate(R.layout.custom_progress_bar, null);
        ProgressBar progressBar = (ProgressBar) child.findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.WHITE,
                android.graphics.PorterDuff.Mode.SRC_IN);


        alertDialog = new Dialog(new ContextThemeWrapper(this, R.style.alertDialogCustom));
        alertDialog.setCancelable(false);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(child);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
    public void showDialog(){
        alertDialog.show();
    }
    public void dismissDialog(){
        alertDialog.hide();
    }
    public String getCurrentDate(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = calendar.getTime();
        return formatter.format(date);
    }
    public void callGetTopRepository(){
        showDialog();
        String url = RestClient.search+getCurrentDate();
        Call<Git_Object> mCallGetTopRepo = XapoApp.getRestClient().getApplicationServices().getTopRepository(url);
        mCallGetTopRepo.enqueue(new Callback<Git_Object>() {
            @Override
            public void onResponse(Call<Git_Object> call, Response<Git_Object> response) {
                dismissDialog();
                Git_Object git_object = response.body();
                if(git_object!=null){
                    mAdapter.updateAdapter(git_object.getItems());
                }
            }

            @Override
            public void onFailure(Call<Git_Object> call, Throwable t) {

            }
        });

    }

}
