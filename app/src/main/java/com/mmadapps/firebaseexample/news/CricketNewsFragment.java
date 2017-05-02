package com.mmadapps.firebaseexample.news;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mmadapps.firebaseexample.R;
import com.mmadapps.firebaseexample.customs.ConnectionDetector;
import com.mmadapps.firebaseexample.customs.SnackBar;
import com.mmadapps.firebaseexample.customviews.AllerBoldTextview;
import com.mmadapps.firebaseexample.news.newsbean.Datum;
import com.mmadapps.firebaseexample.news.newsbean.GetCricketNews;
import com.mmadapps.firebaseexample.utils.OnResponseListner;
import com.mmadapps.firebaseexample.utils.WebServices;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mayank.gupta on 4/13/2017.
 */

public class CricketNewsFragment extends Fragment implements OnResponseListner<GetCricketNews> {
    ConnectionDetector mDetector;
    RecyclerView recyclerView;
    CricketNewsListAdapter cricketNewsListAdapter;
    List<com.mmadapps.firebaseexample.news.newsbean.Datum> cricnewsList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, container, false);
        init(view);
        mDetector = new ConnectionDetector(getActivity());
        if (mDetector.isConnectingToInternet()) {
            callNews();

        } else {
            SnackBar.makeText(getActivity(), getString(R.string.please_check_internet), Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    private void init(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.vR_news_cnf);

    }

    private void callNews() {
        WebServices<GetCricketNews> getCricketNewsWebServices = new WebServices<>(CricketNewsFragment.this);
        getCricketNewsWebServices.getNews(WebServices.PublicDevService, WebServices.ApiType.getNews, "VNmGckEG1UP5HOBxzIxgNuccdsc2 ");
    }

    @Override
    public void onResponse(GetCricketNews response, WebServices.ApiType URL, boolean isSucces) {
        if (URL == WebServices.ApiType.getNews) {
            if (isSucces) {

                GetCricketNews getCricketNews = response;
                if (getCricketNews != null) {
                    if (getCricketNews.getCache() && getCricketNews.getData() != null) {
                        cricnewsList = new ArrayList<>();
                        for(int i=0;i<getCricketNews.getData().size();i++)
                        cricnewsList.addAll(getCricketNews.getData());
                        setMatchesValues();

                    }
                } else {
                    SnackBar.makeText(getActivity(), R.string.no_data, SnackBar.LENGTH_SHORT).show();
                }

            }
        }


    }

    private void setMatchesValues() {
        cricketNewsListAdapter = new CricketNewsListAdapter(cricnewsList, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(cricketNewsListAdapter);
    }


        public class CricketNewsListAdapter extends RecyclerView.Adapter<CricketNewsFragment.CricketNewsListAdapter.MyViewHolder> {
            List<com.mmadapps.firebaseexample.news.newsbean.Datum> resultList;
            Context context;

            public CricketNewsListAdapter(List<com.mmadapps.firebaseexample.news.newsbean.Datum> resultList, Context context) {
                this.resultList = resultList;
                this.context = context;
            }

            @Override
            public CricketNewsFragment.CricketNewsListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adpter_news_cric, parent, false);
                return new CricketNewsListAdapter.MyViewHolder(itemView);
            }

            @Override
            public void onBindViewHolder(CricketNewsFragment.CricketNewsListAdapter.MyViewHolder holder, final int position) {
                 holder.vT_news_cmf.setText(cricnewsList.get(position).getTitle());
                holder.vL_newslist_cmf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle args = new Bundle();
                        args.putString("Title", cricnewsList.get(position).getTitle());
                        args.putString("Desc", cricnewsList.get(position).getDescription());
                        ApplySchemeDialogueFragment applySchemeDialogueFragment = new ApplySchemeDialogueFragment();
                        applySchemeDialogueFragment.setArguments(args);
                        applySchemeDialogueFragment.show(getFragmentManager(), "");
                    }
                });
            }
            @Override
            public int getItemCount() {
                if (resultList == null || resultList.size() == 0)
                    return 0;
                return resultList.size();
            }

            public class MyViewHolder extends RecyclerView.ViewHolder {

                AllerBoldTextview vT_news_cmf;
                LinearLayout vL_newslist_cmf;

                public MyViewHolder(View itemView) {
                    super(itemView);

                    vL_newslist_cmf= (LinearLayout) itemView.findViewById(R.id.vL_newslist_cmf);
                    vT_news_cmf= (AllerBoldTextview) itemView.findViewById(R.id.vT_news_cmf);

                }
            }

        }
}

