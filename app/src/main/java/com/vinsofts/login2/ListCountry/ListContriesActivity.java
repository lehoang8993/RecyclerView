package com.vinsofts.login2.ListCountry;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Button;
import android.widget.Toast;

import com.vinsofts.login2.FullScreenActivity;
import com.vinsofts.login2.Inteface.ILoadmore;
import com.vinsofts.login2.Inteface.ISelectView;
import com.vinsofts.login2.Message;
import com.vinsofts.login2.Model.ContriesData;
import com.vinsofts.login2.R;
import com.vinsofts.login2.dialog.CustomDialog;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListContriesActivity extends FullScreenActivity implements ISelectView, ILoadmore,SwipeRefreshLayout.OnRefreshListener{
    public static boolean check = false;
    private ArrayList<ContriesData> list_Country;
    private CountryAdapter countryAdapter;

    @BindView(R.id.rc_listcountries)
    RecyclerView rcListcountries;
    @BindView(R.id.bt_Switch)
    Button btSwitch;
    @BindView(R.id.srl_refesh)
    SwipeRefreshLayout srlRefesh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contries);
        ButterKnife.bind(this);
        list_Country = new ArrayList<>();
        initData();
        initView();
        srlRefesh.setOnRefreshListener(this);
        onDrag();
    }

    // set recyclerview
    private void initView() {
        rcListcountries.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.drawable_divider);
        dividerItemDecoration.setDrawable(drawable);
        if(!check){
            rcListcountries.setLayoutManager(new LinearLayoutManager(this));
        }else {
            rcListcountries.setLayoutManager(new GridLayoutManager(this,2));
        }
        rcListcountries.addItemDecoration(dividerItemDecoration);
        countryAdapter = new CountryAdapter(rcListcountries, this, list_Country);
        rcListcountries.setAdapter(countryAdapter);
    }

    //init data
    private void initData() {
        list_Country.add(new ContriesData(R.drawable.vietnam,
                "Việt Nam", "Vietnam", "Hà Nội", "Hanoi"));
        list_Country.add(new ContriesData(R.drawable.thailand,
                "Thái Lan", "Thailand", "Băng Cốc", "Bangkok"));
        list_Country.add(new ContriesData(R.drawable.lao,
                "Lào", "Laos", "Viên Chăn", "Vienchan"));
        list_Country.add(new ContriesData(R.drawable.campuchia,
                "Campuchia", "Campuchia", "Phômpenh", "Phomphenh"));
        list_Country.add(new ContriesData(R.drawable.vietnam,
                "Việt Nam", "Vietnam", "Hà Nội", "Hanoi"));
        list_Country.add(new ContriesData(R.drawable.lao,
                "Lào", "Laos", "Viên Chăn", "Vienchan"));
        list_Country.add(new ContriesData(R.drawable.vietnam,
                "Việt Nam", "Vietnam", "Hà Nội", "Hanoi"));
        list_Country.add(new ContriesData(R.drawable.lao,
                "Lào", "Laos", "Viên Chăn", "Vienchan"));
        list_Country.add(new ContriesData(R.drawable.vietnam,
                "Việt Nam", "Vietnam", "Hà Nội", "Hanoi"));

    }
    //click on image
    @Override
    public void ClickImage(Drawable drawable) {
        CustomDialog customDialog = new CustomDialog(this, drawable);
        customDialog.DialogFlag();
    }

    //click on Name country
    @Override
    public void ClickNameCountry(String Name) {
        Message.sMessage(ListContriesActivity.this, Name);
    }

    //click on Capital
    @Override
    public void ClickCapital(String Capital) {
        Message.sMessage(ListContriesActivity.this, Capital);
    }

    //click on switch to change lauout
    @OnClick(R.id.bt_Switch)
    public void onViewClicked() {
        check = !check;
        initView();
    }
    private void onDeleteData(){
        list_Country.clear();
        initData();
    }

    //refesh data
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                srlRefesh.setRefreshing(false);
                list_Country.clear();
                initData();
                countryAdapter.notifyDataSetChanged();
                Toast.makeText(ListContriesActivity.this, "Refesh", Toast.LENGTH_SHORT).show();
            }
        }, 1000);
    }

    //drag and drop item
    private void onDrag(){
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder dragged, @NonNull RecyclerView.ViewHolder target) {
                int position_dragged = dragged.getAdapterPosition();
                int position_target = target.getAdapterPosition();
                Collections.swap(list_Country, position_dragged, position_target);
                countryAdapter.notifyItemMoved(position_dragged, position_target);
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                Remove(viewHolder.getAdapterPosition());
            }
        });
        helper.attachToRecyclerView(rcListcountries);
    }

    //load data when scroll
    @Override
    public void onLoadMore() {
        if (list_Country.size() <= 20000) {
            if(list_Country.get(list_Country.size()-1)!=null){
                list_Country.add(null);
                countryAdapter.notifyItemInserted(list_Country.size() - 1);
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    list_Country.remove(list_Country.size() - 1);
                    countryAdapter.notifyItemRemoved(list_Country.size());
                    initData();
                    countryAdapter.notifyDataSetChanged();
                    countryAdapter.setLoaded();
                }
            }, 3000);
        } else {
            Toast.makeText(ListContriesActivity.this, "Load data complete!", Toast.LENGTH_SHORT).show();
        }
    }

    public void Remove(int position){
        list_Country.remove(position);
        countryAdapter.notifyItemRemoved(position);
    }
}

