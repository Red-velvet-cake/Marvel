package com.red_velvet.marvel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.red_velvet.marvel.databinding.FragmentFirstBinding
import kotlin.math.pow

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnApplyRsa.setOnClickListener {

            val firstNum = binding.etFirstPrime.text.toString()
            val secondNum = binding.etSecondPrime.text.toString()
            val m = binding.etMessage.text.toString()


            if (m.isEmpty() || firstNum.isEmpty() || secondNum.isEmpty()) {
                Snackbar.make(
                    binding.btnApplyRsa,
                    "Values cannot left empty",
                    Snackbar.LENGTH_SHORT
                )
                    .show()
                return@setOnClickListener
            }

            if (!isPrime(firstNum.toInt())) {
                Snackbar.make(
                    binding.btnApplyRsa,
                    "Choose a prime number for first value",
                    Snackbar.LENGTH_SHORT
                )
                    .show()
                return@setOnClickListener
            }

            if (!isPrime(secondNum.toInt())) {
                Snackbar.make(
                    binding.btnApplyRsa,
                    "Choose a prime number for second value",
                    Snackbar.LENGTH_SHORT
                )
                    .show()
                return@setOnClickListener
            }


            val generatedKey = RSA.generateKey(firstNum.toInt(), secondNum.toInt())


            val cipherText = RSA.cipherMessage(
                m.toInt(),
                generatedKey.publicKey.second,
                generatedKey.publicKey.first
            )

            binding.tvCipherTextResult.text = cipherText.toString()

            binding.tvPublicKeyResult.text = generatedKey.publicKey.toString()//(n, e)

            binding.tvPrivateKeyResult.text = RSA.decipherMessage(
                cipherText.toInt(),
                generatedKey.publicKey.first,
                generatedKey.publicKey.second,
                generatedKey.euler
            ).toString()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class RSA {

    companion object {
        fun generateKey(p: Int, q: Int): GeneratedKey {
            val n = p * q

            val euler = (p - 1) * (q - 1)

            val e = chooseCoprime(euler)

            return GeneratedKey(p, q, euler, Pair(n, e))
        }

        fun cipherMessage(m: Int, e: Int, n: Int) = (m.toDouble().pow(e.toDouble())) % n

        fun decipherMessage(c: Int, n: Int, e: Int, euler: Int): Pair<Int, Int> {
            val d = (1 / e.toDouble()) % euler.toDouble()
            val m = c.toDouble().pow(d) % n.toDouble()
            return Pair(n, d.toInt())
        }

    }

}

data class GeneratedKey(
    val p: Int,
    val q: Int,
    val euler: Int,
    val publicKey: Pair<Int, Int>,
)