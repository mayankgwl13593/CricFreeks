package com.mmadapps.firebaseexample.matches;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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
import com.mmadapps.firebaseexample.matchdetails.CricketMatchDetails;
import com.mmadapps.firebaseexample.matches.matchesbean.Datum;
import com.mmadapps.firebaseexample.matches.matchesbean.GetCricketMatches;
import com.mmadapps.firebaseexample.utils.OnResponseListner;
import com.mmadapps.firebaseexample.utils.WebServices;

import java.util.ArrayList;
import java.util.List;

public class CricketMatchFragment extends Fragment implements OnResponseListner<GetCricketMatches> {
    ConnectionDetector mDetector;
    RecyclerView recyclerView;
    CricketMatchesListAdapter cricketMatchesListAdapter;
    List<Datum> cricmatchList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.cricket_matches,container,false);
        init(view);
        mDetector = new ConnectionDetector(getActivity());
        if (mDetector.isConnectingToInternet()) {
            callMatches();


        } else {
            SnackBar.makeText(getActivity(), getString(R.string.please_check_internet), Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    private void init(View view) {
        recyclerView= (RecyclerView) view.findViewById(R.id.vR_matches_csf);

    }

    private void callMatches() {
        WebServices<GetCricketMatches> getCricketMatchesWebServices = new WebServices<>(CricketMatchFragment.this);
        getCricketMatchesWebServices.getMatches(WebServices.PublicDevService, WebServices.ApiType.getMatches, "VNmGckEG1UP5HOBxzIxgNuccdsc2 ");
    }

    @Override
    public void onResponse(GetCricketMatches response, WebServices.ApiType URL, boolean isSucces) {
        if(URL== WebServices.ApiType.getMatches){
            if(isSucces){

                GetCricketMatches getCricketMatches=response;
                if(getCricketMatches!=null){
                    if(getCricketMatches.getCache() && getCricketMatches.getData()!=null){
                        cricmatchList=new ArrayList<>();
                        for(int i=0;i<getCricketMatches.getData().size();i++) Log.e("Match",getCricketMatches.getData().get(i).getTitle());
                        cricmatchList.addAll(getCricketMatches.getData());
                         setMatchesValues();

                    }
                }
                else{
                    SnackBar.makeText(getActivity(),R.string.no_data,SnackBar.LENGTH_SHORT).show();
                }

            }
        }


    }

    private void setMatchesValues() {
        cricketMatchesListAdapter = new CricketMatchesListAdapter(cricmatchList, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(cricketMatchesListAdapter);

    }


    public class CricketMatchesListAdapter extends RecyclerView.Adapter<CricketMatchFragment.CricketMatchesListAdapter.MyViewHolder> {
        List<Datum> resultList;
        Context context;

        public CricketMatchesListAdapter(List<Datum> resultList, Context context) {
            this.resultList = resultList;
            this.context = context;
        }

        @Override
        public CricketMatchFragment.CricketMatchesListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_matches_cric, parent, false);
            return new CricketMatchesListAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(CricketMatchFragment.CricketMatchesListAdapter.MyViewHolder holder, final int position) {

            holder.vT_matchname_cmf.setText(cricmatchList.get(position).getTitle());
            holder.vL_matchlist_cmf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(),CricketMatchDetails.class);
                    intent.putExtra("unique_id",cricmatchList.get(position).getUniqueId().toString());
                    startActivity(intent);
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

            AllerBoldTextview vT_matchname_cmf;
            LinearLayout vL_matchlist_cmf;

            public MyViewHolder(View itemView) {
                super(itemView);

                vL_matchlist_cmf= (LinearLayout) itemView.findViewById(R.id.vL_matchlist_cmf);
                vT_matchname_cmf= (AllerBoldTextview) itemView.findViewById(R.id.vT_matchname_cmf);

            }
        }

    }
}

