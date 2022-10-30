package jafar.if5a.kamusbaru.ui.home;

import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import jafar.if5a.kamusbaru.activities.DetailActivity;
import jafar.if5a.kamusbaru.adapter.KamusViewAdapter;
import jafar.if5a.kamusbaru.databases.KamusHelper;
import jafar.if5a.kamusbaru.databinding.FragmentHomeBinding;
import jafar.if5a.kamusbaru.models.Kamus;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private KamusViewAdapter kamusViewAdapter;
    private KamusHelper kamusHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        kamusHelper = new KamusHelper(getActivity());
        kamusViewAdapter = new KamusViewAdapter(this::onKamusItemClick);
        binding.rvKamus.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvKamus.setAdapter(kamusViewAdapter);

        getAllData();

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strSearh = binding.etSearch.getText().toString();

                if(TextUtils.isEmpty(strSearh))
                {
                    getAllData();
                }
                else
                {
                    kamusHelper.open();
                    ArrayList<Kamus> kamus = kamusHelper.getAllDataEnglishIndonesiaByTitle(strSearh);
                    kamusHelper.close();
                    kamusViewAdapter.setData(kamus);
                }
                hideKeyboard(getActivity());
            }
        });

        return root;
    }

    private void onKamusItemClick(Kamus kamus, int i) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("EXTRA_KAMUS", kamus);
        startActivity(intent);
    }

    private void getAllData() {
        kamusHelper.open();
        ArrayList<Kamus> kamus = kamusHelper.getAllDataEnglishIndonesia();
        kamusHelper.close();
        kamusViewAdapter.setData(kamus);
    }

    private void hideKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}