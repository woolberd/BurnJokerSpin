package bolli.adom.desa.white.repository
import bolli.adom.desa.white.model.CoinModel

class CoinRepository {

    private val list = mutableListOf<CoinModel>()

    fun getListIfData(): MutableList<CoinModel> {
        list.add(CoinModel(100, "0.49$"))
        list.add(CoinModel(300, "0,99$"))
        list.add(CoinModel(500, "1.20$"))
        list.add(CoinModel(1000, "1.99$"))
        return list
    }
}