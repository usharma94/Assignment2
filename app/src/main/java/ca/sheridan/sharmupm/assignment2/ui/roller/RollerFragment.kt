package ca.sheridan.sharmupm.assignment2.ui.roller
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ca.sheridan.sharmupm.assignment2.R
import ca.sheridan.sharmupm.assignment2.database.GameScore
import ca.sheridan.sharmupm.assignment2.databinding.FragmentRollerBinding


class RollerFragment : Fragment() {
    private lateinit var binding: FragmentRollerBinding
    private val viewModel: RollerViewModel by viewModels()

    //    add menu to fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = FragmentRollerBinding.inflate(inflater, container, false)

        binding.rollButton.setOnClickListener {
            outputDice()
        }
        viewModel.lastLineFromDB.observe(viewLifecycleOwner){
            if(it.isNotEmpty()) {
                viewModel.lastLineFromDB.observe(viewLifecycleOwner){reset(it)}
            }else{
                showOutput(it)
            }
        }
    return binding.root
    }

    private fun showOutput(scoreID: List<GameScore>) {
        viewModel.reset()

    }
    private  fun reset(list: List<GameScore>){
        if(list.isEmpty()){
            binding.die1Text.text = "_"
            binding.die2Text.text =  "_"
            binding.die3Text.text =  "_"
            binding.sumText.text =  "0"

        }else{
            binding.die1Text.text = list[0].die1.toString()
            binding.die2Text.text = list[0].die2.toString()
            binding.die3Text.text = list[0].die3.toString()
            binding.sumText.text = list[0].total.toString()
        }
    }

//load menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_roller, menu)
    }
//navigate to history fragment
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.action_history ->{
                findNavController().navigate(R.id.action_global_to_history)
                true
            }
            else->super.onOptionsItemSelected(item)
        }
    }

    private fun  outputDice(){
        val die1 =  rollDice()
        val die2 =  rollDice()
        val die3 =  rollDice()
        val sumTotal = sumRoll(die1,die2,die3)

        binding.die1Text.text = die1.toString()
        binding.die2Text.text = die2.toString()
        binding.die3Text.text = die3.toString()
        binding.sumText.text = "TOTAL: $sumTotal"

        val dieInfo = GameScore(0, die1, die2, die3, sumTotal)

        viewModel.send(dieInfo)
    }

    private fun rollDice(): Int {
        return (1..6).random();

    }

    private fun sumRoll(die1:Int, die2:Int, die3:Int): Int {
        val sum = die1 + die2 + die3
        return sum
    }

//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//        binding.die1Text.text = savedInstanceState?.getString("DIE_1")
//        binding.die2Text.text = savedInstanceState?.getString("DIE_2")
//        binding.die3Text.text = savedInstanceState?.getString("DIE_3")
//        binding.sumText.text = savedInstanceState?.getString("SUM")
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putString("GAME_STATE_KEY", gameState)
//        outState.putString("DIE_1", binding.die1Text.text.toString())
//        outState.putString("DIE_2", binding.die2Text.text.toString())
//        outState.putString("DIE_3", binding.die3Text.text.toString())
//        outState.putString("SUM", binding.sumText.text.toString())
//    }

}

