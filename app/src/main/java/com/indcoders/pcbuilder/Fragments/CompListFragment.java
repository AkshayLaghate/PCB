package com.indcoders.pcbuilder.Fragments;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.indcoders.pcbuilder.R;
import com.indcoders.pcbuilder.Utils.DBHelper;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CompListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CompListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String TAG_CPU = "CPUS";
    String TAG_MOTHERBOARD = "Motherboard";

    MenuItem price;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CompListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CompListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CompListFragment newInstance(String param1, String param2) {
        CompListFragment fragment = new CompListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_comp_list, container, false);


        DBHelper dbHelper = new DBHelper(getActivity());
        dbHelper.close();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor c = db.rawQuery("select * from CPUS", null);
        final String[] vals = new String[c.getCount()];
        final String[] names = new String[c.getCount()];
        c.moveToFirst();

        while (!c.isAfterLast()) {
            names[c.getPosition()] = c.getString(1);
            vals[c.getPosition()] = c.getString(2);
            c.moveToNext();
        }

        c.close();

        final ListView lvCompList = (ListView) v.findViewById(R.id.lvCompList);


        lvCompList.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return vals.length;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(final int position, View convertView, final ViewGroup parent) {

                if (convertView == null) {
                    LayoutInflater inflater = (LayoutInflater) getActivity()
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.custom_comp_list, parent, false);

                }
                TextView tvProductName = (TextView) convertView.findViewById(R.id.tvProductName);
                TextView tvProductPrice = (TextView) convertView.findViewById(R.id.tvProductPrice);
                CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox);

                tvProductName.setText(names[position]);
                tvProductPrice.setText(vals[position]);
                cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            for (int i = 0; i < vals.length; i++) {
                                if (i != position) {
                                    parent.getChildAt(i).findViewById(R.id.checkBox).setEnabled(false);
                                }
                            }
                        } else {
                            for (int i = 0; i < vals.length; i++) {
                                parent.getChildAt(i).findViewById(R.id.checkBox).setEnabled(true);
                            }
                        }
                    }
                });
                return convertView;

            }
        });


        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
