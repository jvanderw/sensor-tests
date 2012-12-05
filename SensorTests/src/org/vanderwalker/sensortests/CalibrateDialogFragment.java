package org.vanderwalker.sensortests;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class CalibrateDialogFragment extends DialogFragment implements OnClickListener {

    private static final String TAG = "CalibrateDialogFragment";
    
    private Button calibrateButton;
    
    public interface CalibrateDialogListener {
        void onFinishCalibrateDialog();
    }
    
    public CalibrateDialogFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        
        View view = inflater.inflate(R.layout.fragment_calibrate_dialog, container);
        calibrateButton = (Button) view.findViewById(R.id.buttonCalibrate);
        calibrateButton.setOnClickListener(this);
        getDialog().setTitle(R.string.calibrate_dialog_title);
        
        Log.d(TAG, "onCreateView");
        
        return view;
    }

    public void onClick(View view) {
        Log.d(TAG, "onClick");
        CalibrateDialogListener dl = (CalibrateDialogListener) getActivity();
        dl.onFinishCalibrateDialog();
        this.dismiss();
    }

}
