val indexes: ArrayList<String> = ArrayList<String>().apply {
    add("nyc-open-data")
}
val result: ArrayList<String> = kuzzle.indexController.mDelete(indexes).get()
