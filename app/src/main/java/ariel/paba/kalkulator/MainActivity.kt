package ariel.paba.kalkulator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    private lateinit var multiplyFragment: MultiplyFragment
    private lateinit var divideFragment: DivideFragment
    private lateinit var addFragment: AddFragment
    private lateinit var subtractFragment: SubtractFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        multiplyFragment = MultiplyFragment()
        divideFragment = DivideFragment()
        addFragment = AddFragment()
        subtractFragment = SubtractFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.multiplyContainer, multiplyFragment)
            .replace(R.id.divideContainer, divideFragment)
            .replace(R.id.addContainer, addFragment)
            .replace(R.id.subtractContainer, subtractFragment)
            .commit()

        val number1 = findViewById<EditText>(R.id.number1)
        val number2 = findViewById<EditText>(R.id.number2)

        findViewById<Button>(R.id.btnMultiply).setOnClickListener {
            calculate(number1, number2, "*")
        }

        findViewById<Button>(R.id.btnDivide).setOnClickListener {
            calculate(number1, number2, "/")
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            calculate(number1, number2, "+")
        }

        findViewById<Button>(R.id.btnSubtract).setOnClickListener {
            calculate(number1, number2, "-")
        }
    }

    private fun calculate(
        number1: EditText,
        number2: EditText,
        operation: String
    ) {
        val first = number1.text.toString().toDoubleOrNull()
        val second = number2.text.toString().toDoubleOrNull()

        if (first == null || second == null) {
            Toast.makeText(this, "Masukkan kedua angka terlebih dahulu", Toast.LENGTH_SHORT).show()
            return
        }

        if (operation == "/" && second == 0.0) {
            Toast.makeText(this, "Tidak bisa membagi dengan nol", Toast.LENGTH_SHORT).show()
            return
        }

        val result = when (operation) {
            "*" -> first * second
            "/" -> first / second
            "+" -> first + second
            "-" -> first - second
            else -> return
        }

        val formattedResult = formatResult(result)
        val history = "${formatResult(first)} $operation ${formatResult(second)} = $formattedResult"

        when (operation) {
            "*" -> HistoryData.multiplyHistory.add(history)
            "/" -> HistoryData.divideHistory.add(history)
            "+" -> HistoryData.addHistory.add(history)
            "-" -> HistoryData.subtractHistory.add(history)
        }

        refreshFragments()
    }

    private fun formatResult(value: Double): String {
        return java.math.BigDecimal.valueOf(value)
            .stripTrailingZeros()
            .toPlainString()
            .replace('.', ',')
    }

    private fun refreshFragments() {
        multiplyFragment.refresh()
        divideFragment.refresh()
        addFragment.refresh()
        subtractFragment.refresh()
    }
}