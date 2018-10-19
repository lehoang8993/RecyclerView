package com.vinsofts.login2.ListCountry;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vinsofts.login2.Inteface.ILoadmore;
import com.vinsofts.login2.Inteface.ISelectView;
import com.vinsofts.login2.Model.ContriesData;
import com.vinsofts.login2.R;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_PROGRESS = 0;
    private static final int TYPE_LIST = 1;
    private static ISelectView selectView;
    private static ILoadmore loadmore;

    private boolean isLoading=false;
    private Context mContext;
    private List<ContriesData> mList_countries;

    public CountryAdapter(RecyclerView recyclerView, final Context mContext, final List<ContriesData> mList_countries) {
        this.mContext = mContext;
        this.mList_countries = mList_countries;
        selectView = (ISelectView) mContext;
        loadmore = (ILoadmore) mContext;

        //check layout
        if(!ListContriesActivity.check) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!isLoading && totalItemCount == (lastVisibleItem + 1)) {
                        if (loadmore != null) {
                                loadmore.onLoadMore();
                            isLoading = true;
                        }
                    }
                }
            });
        }else {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    int totalItemCount = gridLayoutManager.getItemCount();
                    int lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
                    if (!isLoading && totalItemCount == (lastVisibleItem + 1)) {
                        if (loadmore != null) {
                            loadmore.onLoadMore();
                            isLoading = true;
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewtype) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view;
        switch (viewtype){
            case TYPE_LIST:
                view = inflater.inflate(R.layout.item_listcontries,viewGroup, false);
                return new ViewHolder(view);
            case TYPE_PROGRESS:
                view = inflater.inflate(R.layout.item_progessbar,viewGroup, false);
                return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof ViewHolder){
            ContriesData contriesData = mList_countries.get(i);
            ViewHolder holder = (ViewHolder) viewHolder;
            holder.ivCountry.setImageResource(contriesData.getImageContry());
            holder.tvContryVN.setText(contriesData.getNameContryVN());
            holder.tvContryEN.setText(contriesData.getNameContryEN());
            holder.tvCapitalVN.setText(contriesData.getNameCapitalVN());
            holder.tvCapitalEN.setText(contriesData.getNameCapitalEN());
        }else if(viewHolder instanceof LoadingViewHolder){
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) viewHolder;
        }
    }

    @Override
    public int getItemCount() {
        return mList_countries.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(mList_countries.get(position)==null){
            return TYPE_PROGRESS;
        }
        return TYPE_LIST;
    }

    public void setLoaded() {
        isLoading = false;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_Country)
        ImageView ivCountry;
        @BindView(R.id.tv_ContryVN)
        TextView tvContryVN;
        @BindView(R.id.tv_ContryEN)
        TextView tvContryEN;
        @BindView(R.id.tv_CapitalVN)
        TextView tvCapitalVN;
        @BindView(R.id.tv_CapitalEN)
        TextView tvCapitalEN;

        private ViewHolder(@NonNull final View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            ivCountry.setOnClickListener(this);
            tvContryVN.setOnClickListener(this);
            tvCapitalVN.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                switch (v.getId()){
                    case R.id.iv_Country:
                        selectView.ClickImage(ivCountry.getDrawable());
                        break;
                    case R.id.tv_ContryVN:
                        selectView.ClickNameCountry(tvContryVN.getText().toString());
                        break;
                    case R.id.tv_CapitalVN:
                        selectView.ClickCapital(tvCapitalVN.getText().toString());
            }
        }
    }

    public static class LoadingViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.pb_load)
        ProgressBar pbload;
        private LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}