package me.khrystal.drawerdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment2 extends Fragment {

    @Bind(R.id.text)
    Button text;

    public BlankFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new Intent("action_switch"));
            }
        });
    }
}
