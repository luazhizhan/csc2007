package edu.singaporetech.vaccinereg

/**
 * Static data
 */
object Constant {
    val vaccinationCentres = arrayOf(
        "JTVC Ang Mo Kio (Former AMK ITE)",
        "JTVC Bukit Merah (Former SEAB Centre)",
        "JTVC Commonwealth (Former MOE Heritage Centre)",
        "JTVC Jurong East (Former Shuqun Secondary School)",
        "JTVC Jurong West (Former Hong Kah Secondary School)",
        "JTVC Kaki Bukit (Former Bedok North Secondary School)",
        "JTVC Pasir Ris (Former Siglap Secondary School)",
        "JTVC Sengkang (Former Preschool)",
        "JTVC Woodlands (Former Woodlands Bus Interchange)",
        "JTVC Yishun (Former Yishun Bus Interchange)"
    )

    private val addresses = arrayOf(
        "4300 Ang Mo Kio Ave 5 S(569869)",
        "8 Lower Delta Road S(169198)",
        "402 Commonwealth Drive S(149599)",
        "450 Jurong East Street 21 S(609604)",
        "931 Jurong West Street 42 S(649370)",
        "20 Jalan Damai S(419612)",
        "10 Pasir Ris Drive 10 S(519385)",
        "60 Sengkang East Way S(548596)",
        "3A Woodlands Square S(737735)",
        "20A Yishun Central S(768830)"
    )

    fun getAddress(vaccinationCentre: String): String {
        return addresses[vaccinationCentres.indexOf(vaccinationCentre)]
    }

    fun getAddressByIndex(index: Int): String {
        return addresses[index]
    }
}