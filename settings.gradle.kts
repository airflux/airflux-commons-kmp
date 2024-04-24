rootProject.name = "airflux-commons-kmp"

include(":airflux-commons-text-kmp")
project(":airflux-commons-text-kmp").projectDir = file("./modules/text")

include(":airflux-commons-kotest-assertions-kmp")
project(":airflux-commons-kotest-assertions-kmp").projectDir = file("./modules/kotest/assertions")

include(":airflux-commons-collections-kmp")
project(":airflux-commons-collections-kmp").projectDir = file("./modules/collections")

include(":airflux-commons-types-kmp")
project(":airflux-commons-types-kmp").projectDir = file("./modules/types")

include(":airflux-commons-types-test-kmp")
project(":airflux-commons-types-test-kmp").projectDir = file("./modules/types/test")
