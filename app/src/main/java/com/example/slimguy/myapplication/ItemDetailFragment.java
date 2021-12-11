package com.example.slimguy.myapplication;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class ItemDetailFragment extends Fragment implements Animation.AnimationListener {

    public static final String ARG_ITEM = "item";

    String s1[] ={"pic1","pic2","pic3","pic4","pic5"};
    int images [] = {R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,R.drawable.pic5,};
    String fileName;
    // Animations
    Animation animatefade, animatemove, animatebounce,animatezoomo;

    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        animatebounce = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.bounce);
        animatebounce.setAnimationListener(this);

            fileName = this.getArguments().getString("item");

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(fileName);
            }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)  {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        //starting animation
        rootView.startAnimation(animatebounce);

        // Show the image as per selection.
        if (fileName != null) {
            switch (fileName){
                case "pic1":

                    ((ImageView) rootView.findViewById(R.id.item_detail_image)).setImageResource(R.drawable.pic1);
                    break;
                case "pic2":
                    ((ImageView) rootView.findViewById(R.id.item_detail_image)).setImageResource(R.drawable.pic2);
                    break;
                case "pic3":
                    ((ImageView) rootView.findViewById(R.id.item_detail_image)).setImageResource(R.drawable.pic3);
                    break;
                case "pic4":
                    ((ImageView) rootView.findViewById(R.id.item_detail_image)).setImageResource(R.drawable.pic4);
                    break;
                case "pic5":
                    ((ImageView) rootView.findViewById(R.id.item_detail_image)).setImageResource(R.drawable.pic5);
                    break;
                default:
                    ((ImageView) rootView.findViewById(R.id.item_detail_image)).setImageResource(R.drawable.pic1);
            }


        }

        return rootView;
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
