/*
 * Copyright 2024 Maxim Sambulat.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.airflux.kotest.assertions

//import io.kotest.core.spec.style.FreeSpec
//import kotlin.random.Random
//
//internal class EqualityContractVerifierTest : FreeSpec() {
//
//    init {
//        "The EqualityContractVerifier" - {
//
//            "should verify the equality contract" {
//                String(TEXT_VALUE).shouldComplyWithContractOfEquality(
//                    b = String(TEXT_VALUE),
//                    c = String(TEXT_VALUE),
//                    notEquals = listOf(
//                        String(EMPTY_VALUE),
//                        String(BLANK_VALUE),
//                    )
//                )
//            }
//
//            "should verify the equality contract" {
//                Abc().shouldComplyWithContractOfEquality(
//                    b = Abc(),
//                    c = Abc(),
//                    notEquals = listOf(
//                        Abc(),
//                        Abc(),
//                    )
//                )
//            }
//        }
//    }
//
//    private companion object {
//        private val TEXT_VALUE = "text".toCharArray()
//        private val EMPTY_VALUE = "".toCharArray()
//        private val BLANK_VALUE = "   ".toCharArray()
//    }
//
//    class Abc {
//        private var i = 10
//        override fun hashCode(): Int {
//            i = Random(i).nextInt()
//            return i
//        }
//    }
//}
