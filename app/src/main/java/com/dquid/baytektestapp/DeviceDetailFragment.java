package com.dquid.baytektestapp;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dquid.bayteklib.BTMachineType;
import com.dquid.baytektestapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DeviceDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeviceDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private DeviceModel device;

    private ImageButton chargeButton;

    public DQBaytekMachine getMyMachine() {
        return myMachine;
    }

    public void setMyMachine(DQBaytekMachine myMachine) {
        this.myMachine = myMachine;
    }

    private DQBaytekMachine myMachine;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DeviceDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeviceDetailFragment newInstance(DeviceModel device, DQBaytekMachine.DQBaytekMachineListenerInterface baytekMachineListenerInterface) {
        DeviceDetailFragment fragment = new DeviceDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        fragment.device = device;

        if(device.getDeviceName().equalsIgnoreCase("Flappy Bird") == Boolean.TRUE) {
            fragment.myMachine = new DQBaytekMachine(BTMachineType.BAYTEK_MACHINE_TYPE_FLAPPY_BIRD, device.getSerial() , baytekMachineListenerInterface);
            fragment.myMachine.connect();

        } else if(device.getDeviceName().equalsIgnoreCase("Piano") == Boolean.TRUE) {
            fragment.myMachine = new DQBaytekMachine(BTMachineType.BAYTEK_MACHINE_TYPE_PIANO_TILES,device.getSerial() ,  baytekMachineListenerInterface);
            fragment.myMachine.connect();
        } else if(device.getDeviceName().equalsIgnoreCase("Emulator") == Boolean.TRUE) {
            fragment.myMachine = new DQBaytekMachine(BTMachineType.BAYTEK_MACHINE_TYPE_FLAPPY_BIRD, device.getSerial(), baytekMachineListenerInterface);
            fragment.myMachine.connect();
        }



        return fragment;
    }

    public DeviceDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_device_detail, container, false);

        chargeButton = (ImageButton) view.findViewById(R.id.charge_button);

        chargeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                mListener.onDeviceCharge(device);

            }

        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(BayTekActivity.FragmentType.DEVICE_DETAIL, uri);
        }
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
}
