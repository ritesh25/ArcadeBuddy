package com.dquid.baytektestapp;

import android.app.Activity;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.dquid.baytektestapp.R;

import com.dquid.baytektestapp.dummy.DummyContent;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class UserFragment extends Fragment implements AbsListView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_POSITION = "position";

    // TODO: Rename and change types of parameters
    private int position;

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private UserAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public static UserFragment newInstance(int position) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public UserFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POSITION);
        }

        // TODO: Change Adapter to display your content
        mAdapter = new UserAdapter(this.getActivity(), new UserModel[] {
                new UserModel("user 1", "https://jamfnation.jamfsoftware.com/img/default-avatars/generic-user.png"),
                new UserModel("user 2", "https://jamfnation.jamfsoftware.com/img/default-avatars/generic-user.png"),
                new UserModel("user 3", "https://jamfnation.jamfsoftware.com/img/default-avatars/generic-user.png"),
                new UserModel("user 4", "https://jamfnation.jamfsoftware.com/img/default-avatars/generic-user.png"),
                new UserModel("user 5", "https://jamfnation.jamfsoftware.com/img/default-avatars/generic-user.png"),
                new UserModel("user 6", "https://jamfnation.jamfsoftware.com/img/default-avatars/generic-user.png"),
                new UserModel("user 7", "https://jamfnation.jamfsoftware.com/img/default-avatars/generic-user.png"),
                new UserModel("user 8", "https://jamfnation.jamfsoftware.com/img/default-avatars/generic-user.png"),
                new UserModel("user 9", "https://jamfnation.jamfsoftware.com/img/default-avatars/generic-user.png")
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            UserModel user  = (UserModel) mAdapter.getItem(position);
            mListener.onFragmentInteraction(BayTekActivity.FragmentType.USERS, user);
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

}
