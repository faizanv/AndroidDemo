package com.ktdid.hellosummit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * The SummiteerViewAdapter takes a list of Summiteers and adds it to a recycler view
 * The recycler view displays a Summiteers name and school
 */
public class SummiteerViewAdapter extends RecyclerView.Adapter<SummiteerViewAdapter.SummiteerViewHolder> {

    private List<Summiteer> mSummiteerList;

    // Create constructor for use in the SummiteerFragment, where we give it the list of Summiteers
    public SummiteerViewAdapter(List<Summiteer> summiteerList) {
        mSummiteerList = summiteerList;
    }

    // Inflate the row layout specific to our Summiteer recycler view
    @Override
    public SummiteerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.summiteer_row_layout, parent, false);

        return new SummiteerViewHolder(itemView);
    }

    // Here is where we get the data from the Summiteer list and put it in our view holder
    @Override
    public void onBindViewHolder(SummiteerViewHolder holder, int position) {
        Summiteer summiteerAtPosition = mSummiteerList.get(position);
        holder.vSummiteerName.setText(summiteerAtPosition.mName);
        holder.vSummiteerSchool.setText(summiteerAtPosition.mSchool);
    }

    // Find the size of the Summiteer list
    @Override
    public int getItemCount() {
        return mSummiteerList.size();
    }

    /**
     * The view holder finds the views in the row layout that we want to modify in the view adapter
     * We find the id for the name and school text view in the row layout xml file, so we can set above in onBindViewHolder
     */
    public class SummiteerViewHolder extends RecyclerView.ViewHolder {

        protected TextView vSummiteerName;
        protected TextView vSummiteerSchool;

        public SummiteerViewHolder(View itemView) {
            super(itemView);

            vSummiteerName = (TextView) itemView.findViewById(R.id.summiteer_name_tv);
            vSummiteerSchool = (TextView) itemView.findViewById(R.id.summiteer_school_tv);
        }
    }
}