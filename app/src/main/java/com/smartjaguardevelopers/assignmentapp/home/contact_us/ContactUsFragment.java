package com.smartjaguardevelopers.assignmentapp.home.contact_us;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.smartjaguardevelopers.assignmentapp.R;

public class ContactUsFragment extends Fragment implements View.OnClickListener
{

    private TextView tvPhoneNumber, tvEmailId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contact_us_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvPhoneNumber = (TextView)getActivity().findViewById(R.id.tvPhoneNumber);
        tvEmailId = (TextView)getActivity().findViewById(R.id.tvEmailId);

        tvPhoneNumber.setOnClickListener(this);
        tvEmailId.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.tvPhoneNumber)
        {
            Intent intentOpenDialPad = new Intent(Intent.ACTION_DIAL);
            intentOpenDialPad.setData(Uri.parse("tel:+919123456789"));
            if (intentOpenDialPad.resolveActivity(getActivity().getPackageManager()) != null)
            {
                Intent chooser;
                chooser = Intent.createChooser(intentOpenDialPad, "Calling... ");
                startActivity(chooser);
            }
            else
            {
                Toast.makeText(getActivity(),"No app found to perform this action ",Toast.LENGTH_LONG);
            }
        }
        else if(view.getId() == R.id.tvEmailId)
        {
            Intent intentOpenEmail = new Intent();
            intentOpenEmail.setAction(Intent.ACTION_SEND);
            intentOpenEmail.setData(Uri.parse("mailto:"));
            intentOpenEmail.putExtra(Intent.EXTRA_EMAIL,"abcde@xyz.com");
            intentOpenEmail.setType("message/rfc822");
            if (intentOpenEmail.resolveActivity(getActivity().getPackageManager()) != null)
            {
                Intent chooser;
                chooser = Intent.createChooser(intentOpenEmail, "Report an issue using Gmail ");
                startActivity(chooser);
            }
            else
            {
                Toast.makeText(getActivity(),"No app found to perform this action ",Toast.LENGTH_LONG);
            }
        }
    }
}
