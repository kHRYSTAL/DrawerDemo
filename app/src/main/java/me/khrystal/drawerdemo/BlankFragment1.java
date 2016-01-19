package me.khrystal.drawerdemo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment1 extends Fragment {

    @Bind(R.id.text)
    Button text;

    public BlankFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_blank_fragment1, container, false);
        ButterKnife.bind(this,inflate);
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
