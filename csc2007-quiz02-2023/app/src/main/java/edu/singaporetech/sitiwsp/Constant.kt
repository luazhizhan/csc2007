package edu.singaporetech.sitiwsp

object Constant {
    val jobs: List<Job> = listOf(
        Job("Google", 2000),
        Job("GovTech", 1500),
        Job("Grab", 1700),
        Job("Gumi", 1500),
        Job("HP", 1900),
        Job("Huawei", 1600),
        Job("Koei", 1540),
        Job("Meta", 2100),
        Job("Microsoft", 1560),
        Job("MiHoYo", 1900),
        Job("Bandai", 1500),
        Job("Razer", 1560),
        Job("Riot", 1600),
        Job("ST", 1600),
        Job("Tencent", 1500),
        Job("TikTok", 1800),
        Job("Ubisoft", 1540),
        Job("Unity", 1700),
        Job("Virtuos", 1900),
        Job("Zoom", 2100),
        Job("Amazon", 1900),
        Job("Apple", 1700),
        Job("Autodesk", 1500),
        Job("BIGO", 2100),
        Job("Cisco", 1550),
        Job("Continental", 1650),
        Job("Creative", 1670),
        Job("DBS Bank", 1890),
        Job("Dell", 1920),
        Job("Garena", 1500)
    )

    fun getJobByCompanyName(companyName: String): Job {
        return jobs.find { it.company == companyName }!!
    }
}