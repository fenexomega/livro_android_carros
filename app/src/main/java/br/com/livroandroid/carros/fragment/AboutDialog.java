package br.com.livroandroid.carros.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import br.com.livroandroid.carros.R;
import livroandroid.lib.utils.AndroidUtils;


public class AboutDialog extends DialogFragment {

    public static void showAbout(FragmentManager fm)
    {
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag("dialog_about");
        if(prev != null)
        {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        new AboutDialog().show(ft,"dialog_about");
    }

    //NOTE: Don't define the method OnCreateView when using a dialog fragment

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Cria o HTML com o texto do about
        SpannableStringBuilder aboutBody = new SpannableStringBuilder();
        String versionName = AndroidUtils.getVersionName(getActivity());
        aboutBody.append(Html.fromHtml(getString(R.string.about_dialog_text,versionName)));
        // Infla o layout
        TextView view = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.fragment_about_dialog,null);
        view.setText(aboutBody);
        view.setMovementMethod(new LinkMovementMethod());
        //Cria o dialog customizado
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.about_dialog_title)
                .setView(view)
                .setPositiveButton(R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                .create();
    }
}
