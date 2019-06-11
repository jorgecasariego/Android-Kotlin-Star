package me.jorgecasariego.peliculas.fragments


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.fragment_fragment2.*


// Documentación: https://github.com/PhilJay/MPAndroidChart/wiki/Setting-Data
class Fragment2 : Fragment() {

    companion object {
        fun newInstance() = Fragment2()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(me.jorgecasariego.peliculas.R.layout.fragment_fragment2, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBarChart()
        setupLineChartData()
        setUpPieChartData()
    }

    private fun setUpPieChartData() {
        val yVals = ArrayList<PieEntry>()
        yVals.add(PieEntry(30f, "Verde"))
        yVals.add(PieEntry(2f, "Azul"))
        yVals.add(PieEntry(4f, "Rojo"))
        yVals.add(PieEntry(22f, "Amarillo"))
        yVals.add(PieEntry(12.5f, "Magenta"))

        val dataSet = PieDataSet(yVals, "Los colores del arcoiris")
        dataSet.valueTextSize = 0f  // Con esto hacemos que salga el valor en texto dentro de cada porcion
        val colors = java.util.ArrayList<Int>()
        colors.add(Color.GREEN)
        colors.add(Color.BLUE)
        colors.add(Color.RED)
        colors.add(Color.YELLOW)
        colors.add(Color.MAGENTA)

        dataSet.colors = colors
        val data = PieData(dataSet)
        pieChart.data = data
        pieChart.centerTextRadiusPercent = 0f
        // Testear estos valores
        pieChart.isDrawHoleEnabled = true
        pieChart.legend.isEnabled = true
        pieChart.description.isEnabled = true
    }

    private fun setupLineChartData() {
        val yVals = ArrayList<Entry>()
        yVals.add(Entry(0f, 30f, "0"))
        yVals.add(Entry(1f, 2f, "1"))
        yVals.add(Entry(2f, 4f, "2"))
        yVals.add(Entry(3f, 6f, "3"))
        yVals.add(Entry(4f, 8f, "4"))
        yVals.add(Entry(5f, 10f, "5"))
        yVals.add(Entry(6f, 22f, "6"))
        yVals.add(Entry(7f, 12.5f, "7"))
        yVals.add(Entry(8f, 22f, "8"))
        yVals.add(Entry(9f, 32f, "9"))
        yVals.add(Entry(10f, 54f, "10"))
        yVals.add(Entry(11f, 28f, "11"))

        val set1: LineDataSet
        set1 = LineDataSet(yVals, "DataSet 1")

        set1.color = Color.BLUE
        set1.setCircleColor(Color.BLUE)
        set1.lineWidth = 1f
        set1.circleRadius = 3f
        set1.setDrawCircleHole(false)
        set1.valueTextSize = 0f
        set1.setDrawFilled(false)

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(set1)
        val data = LineData(dataSets)

        lineChart.data = data
        lineChart.description.isEnabled = false
        lineChart.legend.isEnabled = false
        lineChart.setPinchZoom(true)

        // Valores opcionales - Verificar como queda el grafico con estos valores y comparar
        lineChart.xAxis.enableGridDashedLine(5f, 5f, 0f)
        lineChart.axisRight.enableGridDashedLine(5f, 5f, 0f)
        lineChart.axisLeft.enableGridDashedLine(5f, 5f, 0f)
        lineChart.xAxis.labelCount = 11
        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
    }

    private fun setupBarChart() {
        val bargroup = ArrayList<BarEntry>()
        bargroup.add(BarEntry(0f, 30f, "0"))
        bargroup.add(BarEntry(1f, 2f, "1"))
        bargroup.add(BarEntry(2f, 4f, "2"))
        bargroup.add(BarEntry(3f, 6f, "3"))
        // caso que no tengamos datos en el 4to lugar
        bargroup.add(BarEntry(5f, 10f, "5"))
        bargroup.add(BarEntry(6f, 22f, "6"))
        bargroup.add(BarEntry(7f, 12.5f, "7"))
        bargroup.add(BarEntry(8f, 22f, "8"))
        bargroup.add(BarEntry(9f, 32f, "9"))
        bargroup.add(BarEntry(10f, 54f, "10"))
        bargroup.add(BarEntry(11f, 28f, "11"))

        // creating dataset for Bar Group
        val barDataSet = BarDataSet(bargroup, "Esto es un barchart de ejemplo")

        barDataSet.color = ContextCompat.getColor(requireContext(), me.jorgecasariego.peliculas.R.color.colorAccent)

        val data = BarData(barDataSet)

        barChart.data = data
        barChart.legend.isEnabled = true

        val descripcion = Description()
        descripcion.text = "Esto es una descripción explicando la utilidad del grafico"

        barChart.description = descripcion
        barChart.description.isEnabled = true

        // Valores opcionales - Verificar como queda el grafico con estos valores y comparar
        barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        barChart.xAxis.labelCount = 11
        barChart.xAxis.enableGridDashedLine(5f, 5f, 0f)
        barChart.axisRight.enableGridDashedLine(5f, 5f, 0f)
        barChart.axisLeft.enableGridDashedLine(5f, 5f, 0f)
        barChart.description.isEnabled = false
        barChart.animateY(1000)
        barChart.legend.isEnabled = false
        barChart.setPinchZoom(true)
        barChart.data.setDrawValues(false)

    }


}
