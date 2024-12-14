package com.example.chesslearning.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chesslearning.R;
import com.example.chesslearning.adapter.DebutAdapter;
import com.example.chesslearning.databinding.FragmentLoginBinding;
import com.example.chesslearning.databinding.FragmentMainBinding;
import com.example.chesslearning.enums.DebutType;
import com.example.chesslearning.model.DebutModel;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;
    private ArrayList<DebutModel> list=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentMainBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setAdapter();
    }

    private void setAdapter(){
        list.add(new DebutModel("https://i.ibb.co/fqLZJZ8/spanishopening.jpg","Spanish Opening ",DebutType.EASY,"The Spanish Opening, also known as the Ruy López, starts with 1.e4 e5 2.Nf3 Nc6 3.Bb5. Named after the Spanish priest Ruy López de Segura, it's one of the oldest and most respected chess openings. The main idea is to put pressure on the knight on c6 and prepare for central control with moves like d2-d4. This opening often leads to rich, strategic battles and is favored by many top players."));
        list.add(new DebutModel("https://i.ibb.co/pr1NwkJ/italian-game.png","Italian Defence", DebutType.EASY,"The Italian Defence is a chess opening that begins with the moves 1. e4 e5 2. Nf3 Nc6 3. Bc4. It is one of the oldest and most respected openings in chess, dating back to the 16th century. The key idea behind the Italian Defence is for Black to counterattack and develop solidly while keeping the position flexible. Unlike the Ruy Lopez, which aims for a more strategic buildup, the Italian Defence often leads to open, tactical positions where both sides have chances for rapid development and piece activity. Common continuations for Black include 3... Bc5, the most popular, leading to the Giuoco Piano, or 3... Nf6, leading to the more sharp and tactical Two Knights Defense. The Italian Defence is well-suited for players who enjoy dynamic, open positions and tactical skirmishes."));
        list.add(new DebutModel("https://i.ibb.co/4Rk88XG/london-opening.png","London Opening ",DebutType.MEDIUM,"The London System is a solid and flexible chess opening for White, starting with 1.d4 and typically followed by 2.Nf3 and 3.Bf4. It aims for a strong pawn structure and easy development, often avoiding theoretical battles. The London System is popular for its simplicity and reliability, making it a favorite at all levels of play."));
        list.add(new DebutModel("https://i.ibb.co/sP3HTJT/englishopening.webp","English Opening ",DebutType.MEDIUM,"The English Opening begins with 1.c4 and is known for its flexibility and rich positional play. It often leads to structures similar to those in the Queen's Gambit but avoids many of the mainline theories. The English Opening is a favorite among grandmasters for its potential to transpose into various setups and its emphasis on controlling the center indirectly."));
        list.add(new DebutModel("https://i.ibb.co/NWZWtLY/sicilian-big.jpg","Sicilian Defence", DebutType.MEDIUM,"The Sicilian Defence is a highly popular and strategic chess opening for Black, starting with 1.e4 c5. It aims to counter White's central control and offers asymmetrical positions that often lead to dynamic and complex gameplay. Key variations include the Open Sicilian (2.Nf3 and 3.d4), the Closed Sicilian (2.Nc3), and aggressive lines like the Dragon and Najdorf. This opening is favored by many top players for its rich tactical and strategic opportunities."));
        list.add(new DebutModel("https://i.ibb.co/2kdXyXy/danish-gambit.png","Danish Gambit", DebutType.HARD," The Danish Gambit is an aggressive chess opening for White, starting with 1.e4 e5 2.d4 exd4 3.c3. White sacrifices one or two pawns to gain rapid development and launch an early attack. It's well-suited for players who enjoy taking risks and playing for a quick advantage."));
        list.add(new DebutModel("https://i.ibb.co/Z6FrRf6/carokann.jpg","Caro Kann Opening ", DebutType.HARD,"The Danish Gambit is an aggressive chess opening for White, starting with 1.e4 e5 2.d4 exd4 3.c3. White sacrifices one or two pawns to gain rapid development and launch an early attack. It's well-suited for players who enjoy taking risks and playing for a quick advantage."));
        binding.rvDebuts.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false));
        binding.rvDebuts.setAdapter(new DebutAdapter(list, requireContext()));
    }
}