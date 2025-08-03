package com.cairosquad.evolvefit.viewmodel.register

interface RegisterInteractionListener {
    fun onClickNext()
    fun onClickBack()
    fun onSelectStep(step: Int)
    fun onClickStartNow()
    fun onFemaleCheckedChange(checked: Boolean)
    fun onMaleCheckedChange(checked: Boolean)
    fun onMetricCheckedChange(checked: Boolean)
    fun onImperialCheckedChange(checked: Boolean)
    fun onLossWeightCheckedChange(checked: Boolean)
    fun onGainWeightCheckedChange(checked: Boolean)
    fun onStayInShapeCheckedChange(checked: Boolean)

}