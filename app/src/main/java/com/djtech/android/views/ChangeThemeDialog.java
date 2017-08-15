package com.djtech.android.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.djtech.android.R;

/**
 * Created by Cyberman on 8/13/2017.
 */

public class ChangeThemeDialog extends DialogFragment {

    ChangeThemeDialogListener listener;

    public interface ChangeThemeDialogListener {
        void onDialogPositiveButtonClicked(DialogFragment dialog);
        void onRadioButtonClicked(int which);
        void onDialogNegativeButtonClicked(DialogFragment dialog);
    }
    public ChangeThemeDialog(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ChangeThemeDialogListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
            throw new ClassCastException(context.toString() + "must implement ChangeThemeDialogListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle bundle) {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.select_theme));
        Bundle bundles =  getArguments();
        int theme = bundles.getInt("ThemeVal");

        builder.setSingleChoiceItems(R.array.theme_options, theme, clickListener );

        builder.setPositiveButton(R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        listener.onDialogPositiveButtonClicked(ChangeThemeDialog.this);
                    }
                });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onDialogNegativeButtonClicked(ChangeThemeDialog.this);
            }
        });
        return builder.create();

    }

    private DialogInterface.OnClickListener clickListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            listener.onRadioButtonClicked(which);
        }
    };

}
