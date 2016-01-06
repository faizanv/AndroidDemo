package com.ktdid.hellosummit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment is essentially a container to hold views inside an activity.
 * This allows us to have multiple fragments in an activity.
 * Majority of the time, you will still only use one fragment per activity.
 * However, when using recycler view, it is good to have it in a separate fragment.
 * It makes it easier to create more complex screens without messing up your recycler views.
 */
public class SummiteerFragment extends Fragment {

    public SummiteerFragment() {
    }

    // We create the recycler view here, setting it's linear layout manager and adapter
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_summiteer_layout, container, false);

        // Initialize the recycler view
        RecyclerView summiteerList = (RecyclerView) rootView.findViewById(R.id.summiteer_list);
        summiteerList.setHasFixedSize(true);

        // Create the linear layout manager for the recycler view
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        summiteerList.setLayoutManager(llm);

        // Set the adapter to the recycler view
        SummiteerViewAdapter summiteerViewAdapter = new SummiteerViewAdapter(createList(30));
        summiteerList.setAdapter(summiteerViewAdapter);
        summiteerList.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

    // Here we generate a list of Summiteers, providing their name and school
    // I'm listening to Harry Potter soundtrack, so all summiteers are going to Hogwarts. Jealous.
    // You could also load in a list from json, etc. here
    private List<Summiteer> createList(int size) {

        List<Summiteer> result = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            Summiteer summiteerAtI = new Summiteer();
            summiteerAtI.mName = "Summiteer " + i;
            summiteerAtI.mSchool = "Hogwarts Year " + i % 8;
            result.add(summiteerAtI);
        }

        return result;
    }
}
