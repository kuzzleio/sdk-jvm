package io.kuzzle.sdk.coreClasses.json

data class RawJson(val rawJson: String) {
    override fun toString(): String {
        return rawJson
    }
}
